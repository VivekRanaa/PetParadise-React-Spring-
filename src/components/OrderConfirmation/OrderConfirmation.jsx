import React from 'react';
import { useLocation, Link } from 'react-router-dom';
import './OrderConfirmation.css';

const OrderConfirmation = () => {
    const location = useLocation();
    const order = location.state?.order;

    if (!order) {
        return (
            <div className="confirmation-container">
                <div className="confirmation-error">
                    <h2>Order not found</h2>
                    <p>We couldn't find your order details.</p>
                    <Link to="/" className="home-btn">Return to Home</Link>
                </div>
            </div>
        );
    }

    return (
        <div className="confirmation-container">
            <div className="confirmation-content">
                <div className="success-icon">
                    <svg width="80" height="80" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                        <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path>
                        <polyline points="22,4 12,14.01 9,11.01"></polyline>
                    </svg>
                </div>
                
                <h1>Order Confirmed!</h1>
                <p className="confirmation-message">
                    Thank you for your order. Your pet services have been booked successfully.
                </p>
                
                <div className="order-details">
                    <div className="order-info">
                        <h3>Order Information</h3>
                        <div className="info-row">
                            <span>Order ID:</span>
                            <span className="order-id">#{order.id}</span>
                        </div>
                        <div className="info-row">
                            <span>Date:</span>
                            <span>{new Date(order.orderDate).toLocaleDateString()}</span>
                        </div>
                        <div className="info-row">
                            <span>Status:</span>
                            <span className="status">{order.status}</span>
                        </div>
                        <div className="info-row">
                            <span>Total Amount:</span>
                            <span className="total">${order.totalAmount.toFixed(2)}</span>
                        </div>
                    </div>
                    
                    {order.shippingAddress && (
                        <div className="shipping-info">
                            <h3>Service Location</h3>
                            <p>{order.shippingAddress}</p>
                        </div>
                    )}
                </div>
                
                <div className="next-steps">
                    <h3>What's Next?</h3>
                    <ul>
                        <li>You'll receive an email confirmation shortly</li>
                        <li>Our team will contact you to schedule your services</li>
                        <li>You can track your order status in your account</li>
                    </ul>
                </div>
                
                <div className="confirmation-actions">
                    <Link to="/orders" className="view-orders-btn">
                        View All Orders
                    </Link>
                    <Link to="/" className="continue-shopping-btn">
                        Continue Shopping
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default OrderConfirmation;
