import React, { useState } from 'react';
import "./Details.css";
import axios from "axios";
import {FaUser, FaEnvelope, FaPhone, FaMapMarkerAlt, FaHome, FaSwimmingPool, FaCut, FaStore} from 'react-icons/fa';

const Details = () => {
    const [formData, setFormData] = useState({
        first_Name: '',
        last_Name: '',
        email: '',
        mobile_No: '',
        country: '',
        state: '',
        pincode: '',
        facilities: 'Boarding'
    });

    const [errors, setErrors] = useState({});
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [submitStatus, setSubmitStatus] = useState(null);

    const facilities = [
        { value: 'Boarding', label: 'Pet Boarding', icon: <FaHome /> },
        { value: 'Pool', label: 'Pool Sessions', icon: <FaSwimmingPool /> },
        { value: 'Grooming', label: 'Pet Grooming', icon: <FaCut /> },
        { value: 'PetShop', label: 'Pet Shop', icon: <FaStore /> }
    ];

    const validateForm = () => {
        const { first_Name, last_Name, email, mobile_No, country, state, pincode } = formData;
        const newErrors = {};

        if (!first_Name.trim()) {
            newErrors.first_Name = 'First Name is required';
        }
        if (!last_Name.trim()) {
            newErrors.last_Name = 'Last Name is required';
        }
        if (!email.trim()) {
            newErrors.email = 'Email is required';
        } else if (!/\S+@\S+\.\S+/.test(email)) {
            newErrors.email = 'Email is invalid';
        }
        if (!mobile_No.trim()) {
            newErrors.mobile_No = 'Mobile number is required';
        } else if (!/^\d{10}$/.test(mobile_No.replace(/\D/g, ''))) {
            newErrors.mobile_No = 'Mobile number must be 10 digits';
        }
        if (!country.trim()) {
            newErrors.country = 'Country is required';
        }
        if (!state.trim()) {
            newErrors.state = 'State is required';
        }
        if (!pincode.trim()) {
            newErrors.pincode = 'Pincode is required';
        } else if (!/^\d{6}$/.test(pincode)) {
            newErrors.pincode = 'Pincode must be 6 digits';
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
        
        // Clear error when user starts typing
        if (errors[name]) {
            setErrors(prev => ({ ...prev, [name]: '' }));
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        if (!validateForm()) {
            setSubmitStatus('error');
            return;
        }

        setIsSubmitting(true);
        setSubmitStatus(null);

        try {
            await axios.post("http://localhost:8080/api/v1/Forms", formData);
            setSubmitStatus('success');
            setFormData({
                first_Name: '',
                last_Name: '',
                email: '',
                mobile_No: '',
                country: '',
                state: '',
                pincode: '',
                facilities: 'Boarding'
            });
        } catch (error) {
            console.error('Error submitting form:', error);
            setSubmitStatus('error');
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="details-page page-wrapper">
            <div className="details-container">
                <div className="details-header">
                    <h2>Book Our Services</h2>
                    <p>Fill out the form below to book your pet's care service</p>
                </div>

                <div className="form-container">
                    {submitStatus === 'success' && (
                        <div className="success-message">
                            🎉 Form submitted successfully! We'll contact you soon.
                        </div>
                    )}

                    {submitStatus === 'error' && (
                        <div className="error-message">
                            ❌ Please check the form for errors and try again.
                        </div>
                    )}

                    <form onSubmit={handleSubmit}>
                        <div className="form-grid">
                            <div className="form-group">
                                <label className="form-label">
                                    <FaUser style={{marginRight: '8px'}} />
                                    First Name *
                                </label>
                                <input
                                    type="text"
                                    name="first_Name"
                                    value={formData.first_Name}
                                    onChange={handleChange}
                                    className="form-input"
                                    placeholder="Enter your first name"
                                />
                                {errors.first_Name && <span className="error">{errors.first_Name}</span>}
                            </div>

                            <div className="form-group">
                                <label className="form-label">
                                    <FaUser style={{marginRight: '8px'}} />
                                    Last Name *
                                </label>
                                <input
                                    type="text"
                                    name="last_Name"
                                    value={formData.last_Name}
                                    onChange={handleChange}
                                    className="form-input"
                                    placeholder="Enter your last name"
                                />
                                {errors.last_Name && <span className="error">{errors.last_Name}</span>}
                            </div>

                            <div className="form-group">
                                <label className="form-label">
                                    <FaEnvelope style={{marginRight: '8px'}} />
                                    Email Address *
                                </label>
                                <input
                                    type="email"
                                    name="email"
                                    value={formData.email}
                                    onChange={handleChange}
                                    className="form-input"
                                    placeholder="Enter your email address"
                                />
                                {errors.email && <span className="error">{errors.email}</span>}
                            </div>

                            <div className="form-group">
                                <label className="form-label">
                                    <FaPhone style={{marginRight: '8px'}} />
                                    Mobile Number *
                                </label>
                                <input
                                    type="tel"
                                    name="mobile_No"
                                    value={formData.mobile_No}
                                    onChange={handleChange}
                                    className="form-input"
                                    placeholder="Enter your mobile number"
                                />
                                {errors.mobile_No && <span className="error">{errors.mobile_No}</span>}
                            </div>

                            <div className="form-group">
                                <label className="form-label">
                                    <FaMapMarkerAlt style={{marginRight: '8px'}} />
                                    Country *
                                </label>
                                <input
                                    type="text"
                                    name="country"
                                    value={formData.country}
                                    onChange={handleChange}
                                    className="form-input"
                                    placeholder="Enter your country"
                                />
                                {errors.country && <span className="error">{errors.country}</span>}
                            </div>

                            <div className="form-group">
                                <label className="form-label">
                                    <FaMapMarkerAlt style={{marginRight: '8px'}} />
                                    State *
                                </label>
                                <input
                                    type="text"
                                    name="state"
                                    value={formData.state}
                                    onChange={handleChange}
                                    className="form-input"
                                    placeholder="Enter your state"
                                />
                                {errors.state && <span className="error">{errors.state}</span>}
                            </div>

                            <div className="form-group">
                                <label className="form-label">
                                    <FaMapMarkerAlt style={{marginRight: '8px'}} />
                                    Pincode *
                                </label>
                                <input
                                    type="text"
                                    name="pincode"
                                    value={formData.pincode}
                                    onChange={handleChange}
                                    className="form-input"
                                    placeholder="Enter your pincode"
                                />
                                {errors.pincode && <span className="error">{errors.pincode}</span>}
                            </div>
                        </div>

                        <div className="facilities-section">
                            <label className="form-label">Select Service *</label>
                            <div className="facilities-grid">
                                {facilities.map((facility) => (
                                    <label 
                                        key={facility.value}
                                        className={`facility-option ${formData.facilities === facility.value ? 'selected' : ''}`}
                                    >
                                        <input
                                            type="radio"
                                            name="facilities"
                                            value={facility.value}
                                            checked={formData.facilities === facility.value}
                                            onChange={handleChange}
                                        />
                                        <span style={{marginRight: '8px'}}>{facility.icon}</span>
                                        {facility.label}
                                    </label>
                                ))}
                            </div>
                        </div>

                        <div className="submit-section">
                            <button 
                                type="submit" 
                                className="submit-button"
                                disabled={isSubmitting}
                            >
                                {isSubmitting && <span className="loading-spinner"></span>}
                                {isSubmitting ? 'Submitting...' : 'Submit Details'}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Details;
