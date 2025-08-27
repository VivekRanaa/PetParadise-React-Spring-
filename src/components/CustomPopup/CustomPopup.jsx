import React from 'react';
import { FaCheckCircle, FaExclamationTriangle, FaInfoCircle, FaTimesCircle } from 'react-icons/fa';
import './CustomPopup.css';

const CustomPopup = ({ 
    isOpen, 
    onClose, 
    onConfirm, 
    title, 
    message, 
    type = 'info', // 'success', 'error', 'warning', 'info', 'confirm'
    confirmText = 'OK',
    cancelText = 'Cancel',
    showCancel = false 
}) => {
    if (!isOpen) return null;

    const getIcon = () => {
        switch (type) {
            case 'success':
                return <FaCheckCircle className="popup-icon success" />;
            case 'error':
                return <FaTimesCircle className="popup-icon error" />;
            case 'warning':
                return <FaExclamationTriangle className="popup-icon warning" />;
            case 'confirm':
                return <FaExclamationTriangle className="popup-icon confirm" />;
            default:
                return <FaInfoCircle className="popup-icon info" />;
        }
    };

    const handleConfirm = () => {
        if (onConfirm) {
            onConfirm();
        } else {
            onClose();
        }
    };

    const handleOverlayClick = (e) => {
        if (e.target === e.currentTarget) {
            onClose();
        }
    };

    return (
        <div className="popup-overlay" onClick={handleOverlayClick}>
            <div className={`popup-container ${type}`}>
                <div className="popup-header">
                    {getIcon()}
                    {title && <h3 className="popup-title">{title}</h3>}
                </div>
                
                <div className="popup-content">
                    <p className="popup-message">{message}</p>
                </div>
                
                <div className="popup-actions">
                    {showCancel && (
                        <button 
                            className="popup-btn popup-btn-cancel"
                            onClick={onClose}
                        >
                            {cancelText}
                        </button>
                    )}
                    <button 
                        className="popup-btn popup-btn-confirm"
                        onClick={handleConfirm}
                        autoFocus
                    >
                        {confirmText}
                    </button>
                </div>
            </div>
        </div>
    );
};

export default CustomPopup;
