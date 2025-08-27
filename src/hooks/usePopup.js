import { useState } from 'react';

const usePopup = () => {
    const [popup, setPopup] = useState({
        isOpen: false,
        title: '',
        message: '',
        type: 'info',
        onConfirm: null,
        confirmText: 'OK',
        cancelText: 'Cancel',
        showCancel: false
    });

    const showPopup = (options) => {
        setPopup({
            isOpen: true,
            title: options.title || '',
            message: options.message || '',
            type: options.type || 'info',
            onConfirm: options.onConfirm || null,
            confirmText: options.confirmText || 'OK',
            cancelText: options.cancelText || 'Cancel',
            showCancel: options.showCancel || false
        });
    };

    const hidePopup = () => {
        setPopup(prev => ({ ...prev, isOpen: false }));
    };

    // Convenience methods
    const showAlert = (message, title = '', type = 'info') => {
        showPopup({
            title,
            message,
            type,
            showCancel: false
        });
    };

    const showSuccess = (message, title = 'Success') => {
        showAlert(message, title, 'success');
    };

    const showError = (message, title = 'Error') => {
        showAlert(message, title, 'error');
    };

    const showWarning = (message, title = 'Warning') => {
        showAlert(message, title, 'warning');
    };

    const showConfirm = (message, onConfirm, title = 'Confirm', confirmText = 'Yes', cancelText = 'No') => {
        showPopup({
            title,
            message,
            type: 'confirm',
            onConfirm,
            confirmText,
            cancelText,
            showCancel: true
        });
    };

    return {
        popup,
        showPopup,
        hidePopup,
        showAlert,
        showSuccess,
        showError,
        showWarning,
        showConfirm
    };
};

export default usePopup;
