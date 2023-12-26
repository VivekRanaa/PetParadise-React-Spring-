import React, { Component } from 'react';
import "./Details.css"
import axios from "axios";

class Details extends Component {


    constructor(props) {
        super(props);
        this.state = {
            first_Name: '',
            last_Name:"",
            email: '',
            mobile_No: '',
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
            axios.post("http://localhost:8080/api/v1/Forms", {
                "country": this.state.country,
                "email": this.state.email,
                "facilities": this.state.facility,
                "first_Name": this.state.first_Name,
                "last_Name": this.state.last_Name,
                "mobile_No": this.state.mobile_No,
                "pincode": this.state.pincode,
                "state": this.state.state
            })
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
                                    value={this.state.first_Name}
                                    onChange={this.handleChange}
                                />
                            </td>
                            <td>Last Name:</td>
                            <td>
                                <input
                                    type="text"
                                    name="lname"
                                    value={this.state.last_Name}
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
                                    value={this.state.mobile_No}
                                    onChange={this.handleChange}
                                />
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