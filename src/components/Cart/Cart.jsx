import React from 'react';
import { useCart } from '../../context/CartContext';
import { useAuth } from '../../context/AuthContext';
import { Link, useNavigate } from 'react-router-dom';
import './Cart.css';

const Cart = () => {
    const { cartItems, loading, updateCartItem, removeFromCart, getCartTotal } = useCart();
    const { isAuthenticated } = useAuth();
    const navigate = useNavigate();

    if (!isAuthenticated) {
        return (
            <div className="cart-container">
                <div className="cart-empty">
                    <h2>Please Login</h2>
                    <p>You need to be logged in to view your cart.</p>
                    <Link to="/login" className="login-btn">Login</Link>
                </div>
            </div>
        );
    }

    if (loading) {
        return (
            <div className="cart-container">
                <div className="loading">Loading cart...</div>
            </div>
        );
    }

    if (!cartItems || cartItems.length === 0) {
        return (
            <div className="cart-container">
                <div className="cart-empty">
                    <h2>Your Cart is Empty</h2>
                    <p>Add some services to your cart to get started!</p>
                    <Link to="/" className="continue-shopping-btn">Continue Shopping</Link>
                </div>
            </div>
        );
    }

    const handleQuantityChange = (itemId, newQuantity) => {
        if (newQuantity > 0) {
            updateCartItem(itemId, newQuantity);
        }
    };

    const handleRemoveItem = (itemId) => {
        removeFromCart(itemId);
    };

    const handleCheckout = () => {
        navigate('/checkout');
    };

    return (
        <div className="cart-container">
            <div className="cart-header">
                <h1>Shopping Cart</h1>
                <span className="item-count">{cartItems.length} item(s)</span>
            </div>

            <div className="cart-content">
                <div className="cart-items">
                    {cartItems.map((item) => (
                        <div key={item.id} className="cart-item">
                            <div className="item-details">
                                <h3>{item.serviceName}</h3>
                                <p className="item-description">{item.serviceDescription}</p>
                                <p className="item-price">${item.price}</p>
                            </div>
                            
                            <div className="item-controls">
                                <div className="quantity-controls">
                                    <button 
                                        onClick={() => handleQuantityChange(item.id, item.quantity - 1)}
                                        className="qty-btn"
                                    >
                                        -
                                    </button>
                                    <span className="quantity">{item.quantity}</span>
                                    <button 
                                        onClick={() => handleQuantityChange(item.id, item.quantity + 1)}
                                        className="qty-btn"
                                    >
                                        +
                                    </button>
                                </div>
                                
                                <button 
                                    onClick={() => handleRemoveItem(item.id)}
                                    className="remove-btn"
                                >
                                    Remove
                                </button>
                            </div>
                            
                            <div className="item-total">
                                ${(item.price * item.quantity).toFixed(2)}
                            </div>
                        </div>
                    ))}
                </div>

                <div className="cart-summary">
                    <div className="summary-content">
                        <h3>Order Summary</h3>
                        
                        <div className="summary-line">
                            <span>Subtotal:</span>
                            <span>${getCartTotal().toFixed(2)}</span>
                        </div>
                        
                        <div className="summary-line">
                            <span>Tax:</span>
                            <span>${(getCartTotal() * 0.08).toFixed(2)}</span>
                        </div>
                        
                        <div className="summary-line total">
                            <span>Total:</span>
                            <span>${(getCartTotal() * 1.08).toFixed(2)}</span>
                        </div>
                        
                        <button 
                            onClick={handleCheckout}
                            className="checkout-btn"
                        >
                            Proceed to Checkout
                        </button>
                        
                        <Link to="/" className="continue-shopping">
                            Continue Shopping
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Cart;
