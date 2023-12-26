import React, { useState } from 'react';
import "./Details.css";
import axios from "axios";

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

    const validateForm = () => {
        const { first_Name, last_Name, email, mobile_No, country, state, pincode ,facilities} = formData;
        const newErrors = {};
        const invalidFields = [];

        if (!first_Name) {
            newErrors.first_Name = 'First Name is required';
            invalidFields.push('First Name');
        }
        if (!last_Name) {
            newErrors.last_Name = 'Last Name is required';
            invalidFields.push('Last Name');
        }
        if (!email) {
            newErrors.email = 'Email is required';
            invalidFields.push('Email');
        }
        if (!mobile_No) {
            newErrors.mobile_No = 'Mobile number is required';
            invalidFields.push('Mobile Number');
        }
        if (!country) {
            newErrors.country = 'Country is required';
            invalidFields.push('Country');
        }
        if (!state) {
            newErrors.state = 'State is required';
            invalidFields.push('State');
        }
        if (!pincode) {
            newErrors.pincode = 'Pincode is required';
            invalidFields.push('Pincode');
        }

        setErrors(newErrors);

        if (invalidFields.length > 0) {
            alert(`The following fields are invalid: ${invalidFields.join(', ')}`);
        } else {
            alert("Form submitted");
        }

        return Object.keys(newErrors).length === 0;
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (validateForm()) {
            try {
                await axios.post("http://localhost:8080/api/v1/Forms", formData);
                console.log('Form submitted:', formData);
            } catch (error) {
                console.error('Error submitting form:', error);
            }
        } else {
            console.log('Form has errors. Please correct them.');
        }
    };

    return (
        <div className="details">
            <h2>Facilities Form</h2>
            <form onSubmit={handleSubmit}>
                <table>
                    <tbody>
                    <tr>
                        <td>First Name:</td>
                        <td>
                            <input
                                type="text"
                                name="first_Name"
                                value={formData.first_Name}
                                onChange={handleChange}
                            />
                            {errors.first_Name && <span className="error">{errors.first_Name}</span>}
                        </td>
                        <td>Last Name:</td>
                        <td>
                            <input
                                type="text"
                                name="last_Name"
                                value={formData.last_Name}
                                onChange={handleChange}
                            />
                            {errors.last_Name && <span className="error">{errors.last_Name}</span>}
                        </td>
                    </tr>
                    <tr>
                        <td>Email ID:</td>
                        <td>
                            <input
                                type="email"
                                name="email"
                                value={formData.email}
                                onChange={handleChange}
                            />
                        </td>

                        <td>Mobile Number:</td>
                        <td>
                            <input
                                type="text"
                                name="mobile_No"
                                value={formData.mobile_No}
                                onChange={handleChange}
                            />
                        </td>
                    </tr>

                    <tr>
                        <td>Country:</td>
                        <td>
                            <input
                                type="text"
                                name="country"
                                value={formData.country}
                                onChange={handleChange}
                            />
                        </td>

                        <td>State:</td>
                        <td>
                            <input
                                type="text"
                                name="state"
                                value={formData.state}
                                onChange={handleChange}
                            />
                        </td>
                    </tr>
                    <tr>
                        <td>Pincode:</td>
                        <td>
                            <input
                                type="text"
                                name="pincode"
                                value={formData.pincode}
                                onChange={handleChange}
                            />
                        </td>
                        <td>Facilities:</td>
                        <td>
                            <select
                                name="facilities"
                                value={formData.facilities}
                                onChange={handleChange}
                            >
                                <option value="Boarding">Boarding</option>
                                <option value="Pool">Pool Sessions</option>
                                <option value="Grooming">Grooming</option>
                            </select>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <button className="button-80" role="button" type="submit">Submit Details</button>
            </form>
        </div>
    );
};

export default Details;
