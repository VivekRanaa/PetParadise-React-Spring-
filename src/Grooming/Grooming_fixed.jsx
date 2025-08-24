import React from "react";
import "../App.css";
import "./Grooming.css";
import { Link } from "react-router-dom";
import { FaCut, FaBath, FaRegGem, FaPaw, FaStar, FaHeart } from "react-icons/fa";

function Groom() {
    const services = [
        {
            icon: <FaBath />,
            title: "Premium Bath & Spa",
            description: "Luxurious bath with high-quality shampoo and conditioner, leaving your pet clean and fresh.",
            price: "Starting at ₹800"
        },
        {
            icon: <FaCut />,
            title: "Professional Styling",
            description: "Full body hair trimming and styling by expert groomers to keep your pet looking their best.",
            price: "Starting at ₹1200"
        },
        {
            icon: <FaRegGem />,
            title: "Complete Grooming",
            description: "Comprehensive package including bath, styling, nail cutting, ear cleaning, and more.",
            price: "Starting at ₹1800"
        }
    ];

    return (
        <div className="grooming-page page-wrapper">
            <div className="grooming-container">
                <div className="grooming-header">
                    <h2>Professional Pet Grooming Services</h2>
                    <p>
                        Pet Paradise is Bangalore's leading pet grooming home service provider. We offer convenient, 
                        flexible and cost-effective grooming services right at your doorstep. Our professional 
                        groomers ensure your furry friends look and feel their absolute best.
                    </p>
                </div>

                <div className="grooming-services">
                    <div className="services-grid">
                        {services.map((service, index) => (
                            <div key={index} className="service-card animate-fadeInUp">
                                <span className="service-icon">{service.icon}</span>
                                <h3>{service.title}</h3>
                                <p>{service.description}</p>
                                <div className="service-price">{service.price}</div>
                            </div>
                        ))}
                    </div>
                </div>

                <div className="grooming-gallery">
                    <h3>Our Grooming Gallery</h3>
                    <div className="gallery-grid">
                        <div className="gallery-item">
                            <div className="gallery-wrapper">
                                <img src="/Images/bath1.jpeg" alt="Professional Bath Service" />
                                <div className="gallery-overlay">
                                    <h4>Premium Bath Service</h4>
                                    <p>Gentle and thorough bathing with premium products</p>
                                </div>
                            </div>
                        </div>
                        <div className="gallery-item">
                            <div className="gallery-wrapper">
                                <img src="/Images/bath2.jpeg" alt="Spa Treatment" />
                                <div className="gallery-overlay">
                                    <h4>Spa Treatment</h4>
                                    <p>Relaxing spa experience for ultimate comfort</p>
                                </div>
                            </div>
                        </div>
                        <div className="gallery-item">
                            <div className="gallery-wrapper">
                                <img src="/Images/groompage.jpg" alt="Professional Styling" />
                                <div className="gallery-overlay">
                                    <h4>Professional Styling</h4>
                                    <p>Expert styling and trimming services</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="grooming-cta">
                    <h3>Ready to Pamper Your Pet?</h3>
                    <p>
                        Book our professional grooming services today and give your pet the royal treatment 
                        they deserve. Our expert groomers will make them look and feel amazing!
                    </p>
                    <Link to="/Details" className="cta-button">
                        <FaCut />
                        Book Grooming Service
                    </Link>
                </div>
            </div>
        </div>
    );
}

export default Groom;
