import React from "react";
import "../App.css"
import "./Boarding.css"
import {Link} from "react-router-dom";
import {FaHome, FaHeart, FaShieldAlt, FaClock} from "react-icons/fa";

function Boarding() {
    const features = [
        {
            icon: <FaHome />,
            title: "Home-like Environment",
            description: "Your pets stay in a comfortable, loving home environment, not a kennel."
        },
        {
            icon: <FaHeart />,
            title: "Dedicated Care",
            description: "Our pet lovers provide individual attention and care to each furry friend."
        },
        {
            icon: <FaShieldAlt />,
            title: "Safe & Secure",
            description: "Fully secured premises with 24/7 monitoring for your pet's safety."
        },
        {
            icon: <FaClock />,
            title: "Regular Updates",
            description: "Receive photos and updates throughout your pet's stay with us."
        }
    ];

    const galleryImages = [
        {
            src: "/Images/b1.jpeg",
            alt: "Happy dogs playing together in safe boarding facility",
            caption: "Playtime & Socialization"
        },
        {
            src: "/Images/b2.jpeg",
            alt: "Comfortable sleeping and rest areas for boarding pets",
            caption: "Cozy Rest Areas"
        },
        {
            src: "/Images/t2.jpeg",
            alt: "Professional pet care staff providing daily attention",
            caption: "Daily Care Routine"
        }
    ];

    return (
        <div className="boarding-page page-wrapper">
            <div className="boarding-container">
                <div className="boarding-header">
                    <h1>Pet Boarding Services</h1>
                </div>

                <div className="boarding-text">
                    <h2>
                        Yes, often leaving your pooch behind when you are going out on a vacay can be heartbreaking. 
                        But it doesn't have to be a lonely time for them; they can be ecstatic and make new memories 
                        and friends at these boarding shelters in Bangalore! These are places that are run by dog lovers 
                        for the dogs to have an absolute blast when you are away and be returned to you happy and healthy.
                    </h2>
                </div>

                <div className="boarding-features">
                    {features.map((feature, index) => (
                        <div key={index} className="feature-card">
                            <div className="feature-icon" style={{fontSize: '2rem', color: 'var(--primary-color)', marginBottom: 'var(--space-md)'}}>
                                {feature.icon}
                            </div>
                            <h3>{feature.title}</h3>
                            <p>{feature.description}</p>
                        </div>
                    ))}
                </div>

                <div className="boarding-images">
                    <h2>Our Boarding Facilities</h2>
                    <div className="image-gallery">
                        {galleryImages.map((image, index) => (
                            <div key={index} className="image-item">
                                <img src={image.src} alt={image.alt} />
                                <div className="image-overlay">
                                    <h4>{image.caption}</h4>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>

                <div className="cta-section">
                    <h2>Ready to Book Your Pet's Stay?</h2>
                    <p>Give your furry friend the vacation they deserve while you enjoy yours!</p>
                    <Link to="/Details" className="cta-button">
                        Book Now - Fill Details
                    </Link>
                </div>
            </div>
        </div>
    );
}

export default Boarding;