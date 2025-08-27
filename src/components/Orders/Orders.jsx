import React, { useState, useEffect } from 'react';
import { useAuth } from '../../context/AuthContext';
import { Link } from 'react-router-dom';
import './Orders.css';

const Orders = () => {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    const { user, token, isAuthenticated } = useAuth();

    const API_BASE_URL = 'http://localhost:9090/api';

    useEffect(() => {
        if (isAuthenticated) {
            fetchOrders();
        } else {
            setLoading(false);
        }
    }, [isAuthenticated]);

    const fetchOrders = async () => {
        try {
            const response = await fetch(`${API_BASE_URL}/orders`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                const ordersData = await response.json();
                setOrders(ordersData);
            } else {
                setError('Failed to fetch orders');
            }
        } catch (error) {
            setError('Network error. Please try again.');
        } finally {
            setLoading(false);
        }
    };

    const getStatusColor = (status) => {
        switch (status.toLowerCase()) {
            case 'pending':
                return '#ffc107';
            case 'confirmed':
                return '#17a2b8';
            case 'in_progress':
                return '#fd7e14';
            case 'completed':
                return '#28a745';
            case 'cancelled':
                return '#dc3545';
            default:
                return '#6c757d';
        }
    };

    const formatStatus = (status) => {
        return status.replace('_', ' ').toUpperCase();
    };

    if (!isAuthenticated) {
        return (
            <div className="orders-container">
                <div className="orders-empty">
                    <h2>Please Login</h2>
                    <p>You need to be logged in to view your order history.</p>
                    <Link to="/login" className="login-btn">Login</Link>
                </div>
            </div>
        );
    }

    if (loading) {
        return (
            <div className="orders-container">
                <div className="loading">Loading your orders...</div>
            </div>
        );
    }

    if (error) {
        return (
            <div className="orders-container">
                <div className="error-message">{error}</div>
                <button onClick={fetchOrders} className="retry-btn">Retry</button>
            </div>
        );
    }

    return (
        <div className="orders-container">
            <div className="orders-header">
                <h1>Your Orders</h1>
                <p>Track and manage your pet service bookings</p>
            </div>

            {orders.length === 0 ? (
                <div className="orders-empty">
                    <h2>No Orders Yet</h2>
                    <p>You haven't placed any orders yet. Start exploring our services!</p>
                    <Link to="/" className="continue-shopping-btn">Browse Services</Link>
                </div>
            ) : (
                <div className="orders-list">
                    {orders.map((order) => (
                        <div key={order.id} className="order-card">
                            <div className="order-header">
                                <div className="order-info">
                                    <h3>Order #{order.id}</h3>
                                    <p className="order-date">
                                        Placed on {new Date(order.orderDate).toLocaleDateString()}
                                    </p>
                                </div>
                                <div className="order-status-price">
                                    <span 
                                        className="order-status"
                                        style={{ backgroundColor: getStatusColor(order.status) }}
                                    >
                                        {formatStatus(order.status)}
                                    </span>
                                    <span className="order-total">
                                        ${order.totalAmount.toFixed(2)}
                                    </span>
                                </div>
                            </div>

                            {order.orderItems && order.orderItems.length > 0 && (
                                <div className="order-items">
                                    <h4>Services:</h4>
                                    <ul>
                                        {order.orderItems.map((item, index) => (
                                            <li key={index}>
                                                {item.serviceName} x {item.quantity}
                                                <span>${(item.price * item.quantity).toFixed(2)}</span>
                                            </li>
                                        ))}
                                    </ul>
                                </div>
                            )}

                            {order.shippingAddress && (
                                <div className="order-address">
                                    <h4>Service Location:</h4>
                                    <p>{order.shippingAddress}</p>
                                </div>
                            )}

                            <div className="order-actions">
                                <button 
                                    onClick={() => fetchOrders()}
                                    className="refresh-btn"
                                >
                                    Refresh Status
                                </button>
                                {order.status === 'PENDING' && (
                                    <button className="cancel-btn">
                                        Cancel Order
                                    </button>
                                )}
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default Orders;
