import React, { useState } from 'react';
import { useCart } from '../../context/CartContext';
import { useAuth } from '../../context/AuthContext';
import { useNavigate } from 'react-router-dom';
import './Checkout.css';

const Checkout = () => {
    const { cartItems, getCartTotal, clearCart } = useCart();
    const { user, token } = useAuth();
    const navigate = useNavigate();
    
    const [formData, setFormData] = useState({
        address: '',
        city: '',
        state: '',
        zipCode: '',
        cardNumber: '',
        expiryDate: '',
        cvv: '',
        cardholderName: ''
    });
    
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const API_BASE_URL = 'http://localhost:9090/api';

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError('');

        try {
            const orderData = {
                totalAmount: getCartTotal() * 1.08, // Including tax
                status: 'PENDING',
                shippingAddress: `${formData.address}, ${formData.city}, ${formData.state} ${formData.zipCode}`,
                // In a real app, you would process payment securely
                paymentInfo: {
                    cardholderName: formData.cardholderName,
                    lastFourDigits: formData.cardNumber.slice(-4)
                }
            };

            const response = await fetch(`${API_BASE_URL}/orders`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(orderData)
            });

            if (response.ok) {
                const order = await response.json();
                await clearCart(); // Clear the cart after successful order
                navigate('/order-confirmation', { state: { order } });
            } else {
                const errorData = await response.json();
                setError(errorData.message || 'Failed to place order');
            }
        } catch (error) {
            setError('Network error. Please try again.');
        } finally {
            setLoading(false);
        }
    };

    if (!cartItems || cartItems.length === 0) {
        return (
            <div className="checkout-container">
                <div className="checkout-empty">
                    <h2>Your cart is empty</h2>
                    <p>Add some items to your cart before checking out.</p>
                    <button onClick={() => navigate('/')} className="continue-shopping-btn">
                        Continue Shopping
                    </button>
                </div>
            </div>
        );
    }

    const subtotal = getCartTotal();
    const tax = subtotal * 0.08;
    const total = subtotal + tax;

    return (
        <div className="checkout-container">
            <div className="checkout-content">
                <div className="checkout-form">
                    <h1>Checkout</h1>
                    
                    {error && <div className="error-message">{error}</div>}
                    
                    <form onSubmit={handleSubmit}>
                        <div className="form-section">
                            <h3>Shipping Information</h3>
                            
                            <div className="form-group">
                                <label htmlFor="address">Address</label>
                                <input
                                    type="text"
                                    id="address"
                                    name="address"
                                    value={formData.address}
                                    onChange={handleChange}
                                    required
                                    placeholder="123 Main Street"
                                />
                            </div>
                            
                            <div className="form-row">
                                <div className="form-group">
                                    <label htmlFor="city">City</label>
                                    <input
                                        type="text"
                                        id="city"
                                        name="city"
                                        value={formData.city}
                                        onChange={handleChange}
                                        required
                                        placeholder="City"
                                    />
                                </div>
                                
                                <div className="form-group">
                                    <label htmlFor="state">State</label>
                                    <input
                                        type="text"
                                        id="state"
                                        name="state"
                                        value={formData.state}
                                        onChange={handleChange}
                                        required
                                        placeholder="State"
                                    />
                                </div>
                                
                                <div className="form-group">
                                    <label htmlFor="zipCode">ZIP Code</label>
                                    <input
                                        type="text"
                                        id="zipCode"
                                        name="zipCode"
                                        value={formData.zipCode}
                                        onChange={handleChange}
                                        required
                                        placeholder="12345"
                                    />
                                </div>
                            </div>
                        </div>

                        <div className="form-section">
                            <h3>Payment Information</h3>
                            
                            <div className="form-group">
                                <label htmlFor="cardholderName">Cardholder Name</label>
                                <input
                                    type="text"
                                    id="cardholderName"
                                    name="cardholderName"
                                    value={formData.cardholderName}
                                    onChange={handleChange}
                                    required
                                    placeholder="John Doe"
                                />
                            </div>
                            
                            <div className="form-group">
                                <label htmlFor="cardNumber">Card Number</label>
                                <input
                                    type="text"
                                    id="cardNumber"
                                    name="cardNumber"
                                    value={formData.cardNumber}
                                    onChange={handleChange}
                                    required
                                    placeholder="1234 5678 9012 3456"
                                    maxLength="16"
                                />
                            </div>
                            
                            <div className="form-row">
                                <div className="form-group">
                                    <label htmlFor="expiryDate">Expiry Date</label>
                                    <input
                                        type="text"
                                        id="expiryDate"
                                        name="expiryDate"
                                        value={formData.expiryDate}
                                        onChange={handleChange}
                                        required
                                        placeholder="MM/YY"
                                        maxLength="5"
                                    />
                                </div>
                                
                                <div className="form-group">
                                    <label htmlFor="cvv">CVV</label>
                                    <input
                                        type="text"
                                        id="cvv"
                                        name="cvv"
                                        value={formData.cvv}
                                        onChange={handleChange}
                                        required
                                        placeholder="123"
                                        maxLength="3"
                                    />
                                </div>
                            </div>
                        </div>

                        <button 
                            type="submit" 
                            className="place-order-btn"
                            disabled={loading}
                        >
                            {loading ? 'Processing...' : `Place Order - $${total.toFixed(2)}`}
                        </button>
                    </form>
                </div>

                <div className="order-summary">
                    <h3>Order Summary</h3>
                    
                    <div className="order-items">
                        {cartItems.map((item) => (
                            <div key={item.id} className="order-item">
                                <div className="item-info">
                                    <h4>{item.serviceName}</h4>
                                    <span className="item-qty">Qty: {item.quantity}</span>
                                </div>
                                <span className="item-total">
                                    ${(item.price * item.quantity).toFixed(2)}
                                </span>
                            </div>
                        ))}
                    </div>
                    
                    <div className="order-totals">
                        <div className="total-line">
                            <span>Subtotal:</span>
                            <span>${subtotal.toFixed(2)}</span>
                        </div>
                        <div className="total-line">
                            <span>Tax:</span>
                            <span>${tax.toFixed(2)}</span>
                        </div>
                        <div className="total-line final-total">
                            <span>Total:</span>
                            <span>${total.toFixed(2)}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Checkout;
