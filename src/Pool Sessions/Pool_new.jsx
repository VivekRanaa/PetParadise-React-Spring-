import React from "react";
import "../App.css";
import "./Pool.css";
import { Link } from "react-router-dom";
import { FaSwimmingPool, FaSun, FaHeart, FaUsers, FaWater, FaStar } from "react-icons/fa";

function Pool() {
    const features = [
        {
            icon: <FaSwimmingPool />,
            title: "Safe Swimming",
            description: "Temperature-controlled pools with professional supervision for maximum safety and comfort."
        },
        {
            icon: <FaSun />,
            title: "Sunshine Fun",
            description: "Outdoor pool areas with plenty of sunlight and shaded rest areas for the perfect balance."
        },
        {
            icon: <FaHeart />,
            title: "Health Benefits",
            description: "Low-impact exercise that's perfect for joint health and overall fitness for all ages."
        },
        {
            icon: <FaUsers />,
            title: "Social Play",
            description: "Group sessions where pets can socialize and play together in a controlled environment."
        },
        {
            icon: <FaWater />,
            title: "Clean Water",
            description: "Regularly sanitized and filtered water systems maintaining the highest hygiene standards."
        },
        {
            icon: <FaStar />,
            title: "Expert Care",
            description: "Trained staff members ensure every pet has a safe and enjoyable swimming experience."
        }
    ];

    return (
        <div className="pool-page page-wrapper">
            <div className="pool-container">
                <div className="pool-header">
                    <h2>Pool Sessions & Aqua Therapy</h2>
                    <p>
                        Our four-legged friends deserve the same enjoyment from splashing around with friends,
                        soaking up the sun and enjoying their time poolside. Professional supervision and 
                        temperature-controlled environments ensure a safe and fun experience for all!
                    </p>
                </div>

                <div className="pool-features">
                    <div className="features-grid">
                        {features.map((feature, index) => (
                            <div key={index} className="feature-card animate-fadeInUp">
                                <span className="feature-icon">{feature.icon}</span>
                                <h3>{feature.title}</h3>
                                <p>{feature.description}</p>
                            </div>
                        ))}
                    </div>
                </div>

                <div className="pool-images">
                    <h3>Our Pool Facilities</h3>
                    <div className="images-grid">
                        <div className="image-card">
                            <div className="image-wrapper">
                                <img src="/Images/p1.jpg" alt="Pool Party Fun" />
                                <div className="image-overlay">
                                    <h4>Pool Party Fun</h4>
                                    <p>Pets enjoying supervised group swimming sessions</p>
                                </div>
                            </div>
                        </div>
                        <div className="image-card">
                            <div className="image-wrapper">
                                <img src="/Images/p2.jpg" alt="Individual Sessions" />
                                <div className="image-overlay">
                                    <h4>Individual Sessions</h4>
                                    <p>One-on-one swimming lessons and therapy sessions</p>
                                </div>
                            </div>
                        </div>
                        <div className="image-card">
                            <div className="image-wrapper">
                                <img src="/Images/poolparty.jpg" alt="Party Time" />
                                <div className="image-overlay">
                                    <h4>Party Time</h4>
                                    <p>Special celebration pool parties with friends</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="pool-cta">
                    <h3>Ready for a Splash?</h3>
                    <p>
                        Book a pool session for your pet today! Our expert staff will ensure they have 
                        a safe, fun, and refreshing experience in our pristine facilities.
                    </p>
                    <Link to="/Details" className="cta-button">
                        <FaSwimmingPool />
                        Book Pool Session
                    </Link>
                </div>
            </div>
        </div>
    );
}

export default Pool;
