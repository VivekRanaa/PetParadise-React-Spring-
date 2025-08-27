import React, { createContext, useContext, useState, useEffect } from 'react';
import { useAuth } from './AuthContext';

const CartContext = createContext();

export const useCart = () => {
    const context = useContext(CartContext);
    if (!context) {
        throw new Error('useCart must be used within a CartProvider');
    }
    return context;
};

export const CartProvider = ({ children }) => {
    const [cart, setCart] = useState(null);
    const [cartItems, setCartItems] = useState([]);
    const [loading, setLoading] = useState(false);
    const { user, token } = useAuth();

    const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:9090/api';

    // Fetch cart when user logs in
    useEffect(() => {
        if (user && token) {
            fetchCart();
        } else {
            setCart(null);
            setCartItems([]);
        }
    }, [user, token]);

    const fetchCart = async () => {
        if (!token) return;
        
        setLoading(true);
        try {
            const response = await fetch(`${API_BASE_URL}/cart`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                const cartData = await response.json();
                setCart(cartData);
                setCartItems(cartData.items || []);
            }
        } catch (error) {
            console.error('Error fetching cart:', error);
        } finally {
            setLoading(false);
        }
    };

    const addToCart = async (serviceId, quantity = 1) => {
        if (!token) {
            return { success: false, error: 'Please login to add items to cart' };
        }

        try {
            const response = await fetch(`${API_BASE_URL}/cart/add`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    serviceId,
                    quantity
                })
            });

            if (response.ok) {
                await fetchCart(); // Refresh cart
                return { success: true };
            } else {
                return { success: false, error: 'Failed to add item to cart' };
            }
        } catch (error) {
            return { success: false, error: 'Network error' };
        }
    };

    const updateCartItem = async (itemId, quantity) => {
        if (!token) return;

        try {
            const response = await fetch(`${API_BASE_URL}/cart/update/${itemId}`, {
                method: 'PUT',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ quantity })
            });

            if (response.ok) {
                await fetchCart(); // Refresh cart
                return { success: true };
            }
        } catch (error) {
            console.error('Error updating cart item:', error);
        }
    };

    const removeFromCart = async (itemId) => {
        if (!token) return;

        try {
            const response = await fetch(`${API_BASE_URL}/cart/remove/${itemId}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if (response.ok) {
                await fetchCart(); // Refresh cart
                return { success: true };
            }
        } catch (error) {
            console.error('Error removing cart item:', error);
        }
    };

    const clearCart = async () => {
        if (!token) return;

        try {
            const response = await fetch(`${API_BASE_URL}/cart/clear`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if (response.ok) {
                setCart(null);
                setCartItems([]);
                return { success: true };
            }
        } catch (error) {
            console.error('Error clearing cart:', error);
        }
    };

    const getCartTotal = () => {
        return cartItems.reduce((total, item) => total + (item.price * item.quantity), 0);
    };

    const getCartItemCount = () => {
        return cartItems.reduce((total, item) => total + item.quantity, 0);
    };

    const value = {
        cart,
        cartItems,
        loading,
        addToCart,
        updateCartItem,
        removeFromCart,
        clearCart,
        fetchCart,
        getCartTotal,
        getCartItemCount
    };

    return (
        <CartContext.Provider value={value}>
            {children}
        </CartContext.Provider>
    );
};
