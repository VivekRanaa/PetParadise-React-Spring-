import React, { Component } from 'react';
import "./Details.css"

class Details extends Component {
    constructor(props) {
        super(props);
        this.state = {
            fname: '',
            lname:"",
            email: '',
            mobile: '',
            dob: '',
            address: '',
            gender: 'Male',
            maritalStatus: 'Single',
            country: '',
            state: '',
            pincode: '',
            facility: 'Boarding'
        };
    }

    validateForm = () => {
        const { fname,lname, email, mobile, dob, address, country, state, pincode,facility } = this.state;
        const errors = {};
        const invalidFields = [];

        if (!fname) {
            errors.name = 'Name is required';
            invalidFields.push('Name');
        }
        if (!lname) {
            errors.name = 'Name is required';
            invalidFields.push('Name');
        }
        if (!email) {
            errors.email = 'Email is required';
            invalidFields.push('Email');
        }
        if (!mobile) {
            errors.mobile = 'Mobile number is required';
            invalidFields.push('Mobile Number');
        }
        if (!dob) {
            errors.dob = 'Date of Birth is required';
            invalidFields.push('Date of Birth');
        }
        if (!address) {
            errors.address = 'Address is required';
            invalidFields.push('Address');
        }
        if (!country) {
            errors.country = 'Country is required';
            invalidFields.push('Country');
        }
        if (!state) {
            errors.state = 'State is required';
            invalidFields.push('State');
        }
        if (!pincode) {
            errors.pincode = 'Pincode is required';
            invalidFields.push('Pincode');
        }


        this.setState({ errors });

        if (invalidFields.length > 0) {
            alert(`The following fields are invalid: ${invalidFields.join(', ')}`);
        }
        else{
            alert("form submitted")
        }

        return Object.keys(errors).length === 0; // If there are no errors, the form is valid
    }

    handleChange = (e) => {
        const { name, value } = e.target;
       
        this.setState({ [name]: value });
    }

    handleSubmit = (e) => {
        e.preventDefault();

        if (this.validateForm()) {
            // Submit the form if it's valid
            console.log('Form submitted:', this.state);
        } else {
            // Display validation errors
            console.log('Form has errors. Please correct them.');
        }
    }

    render() {
        return (
            <div className="details">
                <h2>Facilities Form</h2>
                <form onSubmit={this.handleSubmit}>
                    <table>
                        <tbody>
                        <tr>
                            <td>First Name:</td>
                            <td>
                                <input
                                    type="text"
                                    name="fname"
                                    value={this.state.fname}
                                    onChange={this.handleChange}
                                />
                            </td>
                            <td>Last Name:</td>
                            <td>
                                <input
                                    type="text"
                                    name="lname"
                                    value={this.state.lname}
                                    onChange={this.handleChange}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>Email ID:</td>
                            <td>
                                <input
                                    type="email"
                                    name="email"
                                    value={this.state.email}
                                    onChange={this.handleChange}
                                />
                            </td>

                            <td>Mobile Number:</td>
                            <td>
                                <input
                                    type="tel"
                                    name="mobile"
                                    value={this.state.mobile}
                                    onChange={this.handleChange}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>Date of Birth:</td>
                            <td>
                                <input
                                    type="date"
                                    name="dob"
                                    value={this.state.dob}
                                    onChange={this.handleChange}
                                />
                            </td>

                            <td>Address:</td>
                            <td>
                  <textarea
                      name="address"
                      value={this.state.address}
                      onChange={this.handleChange}
                  />
                            </td>
                        </tr>
                        <tr>
                            <td>Gender:</td>
                            <td>
                                <select
                                    name="gender"
                                    value={this.state.gender}
                                    onChange={this.handleChange}
                                >
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                    <option value="Other">Other</option>
                                </select>
                            </td>

                            <td>Marital Status:</td>
                            <td>
                                <select
                                    name="maritalStatus"
                                    value={this.state.maritalStatus}
                                    onChange={this.handleChange}
                                >
                                    <option value="Single">Single</option>
                                    <option value="Married">Married</option>
                                    <option value="Divorced">Divorced</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Country:</td>
                            <td>
                                <input
                                    type="text"
                                    name="country"
                                    value={this.state.country}
                                    onChange={this.handleChange}
                                />
                            </td>

                            <td>State:</td>
                            <td>
                                <input
                                    type="text"
                                    name="state"
                                    value={this.state.state}
                                    onChange={this.handleChange}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>Pincode:</td>
                            <td>
                                <input
                                    type="text"
                                    name="pincode"
                                    value={this.state.pincode}
                                    onChange={this.handleChange}
                                />
                            </td>
                            <td>Facilities:</td>
                            <td>
                                <select
                                    name="facility"
                                    value={this.state.facility}
                                    onChange={this.handleChange}
                                >
                                    <option value="Boarding">Boarding</option>
                                    <option value="Pool">Pool Sessions</option>
                                    <option value="Grooming">Grooming</option>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <button className="button-80" role="button">Submit Details</button>

                </form>
            </div>
        );
    }
}



export default Details;