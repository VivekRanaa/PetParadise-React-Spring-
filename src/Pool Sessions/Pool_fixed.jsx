import React from "react";
import "../App.css";
import "./Pool.css";
import { Link } from "react-router-dom";
import { FaSwimmingPool } from "react-icons/fa";

function Pool() {
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
