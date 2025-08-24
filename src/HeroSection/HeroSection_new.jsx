import React from 'react';
import "../App.css";
import "./HeroSection.css";
import { FaPaw } from "react-icons/fa";

function HeroSection() {
    return (
        <div className="hero-section page-wrapper">
            <div className="hero-container">
                <div className="hero-content animate-fadeInUp">
                    <h1 className="hero-title">
                        <FaPaw className="paw-icon paw-left" />
                        A paradise for your four-legged BFF
                        <FaPaw className="paw-icon paw-right" />
                    </h1>
                    <p className="hero-description">
                        Your pet's care is our priority – be it on a short weekend getaway or a long vacation.
                        We provide the best home pet boarding for cats and dogs with the utmost care and safety while you relax, unwind, recharge.
                        You don't have to look for a cat/dog care centre or a doggy day-care either. Let our devoted pet boarders treat your fur-babies with the love and care they deserve.
                        We provide constant updates of all the woofs and meows, keeping a track of their activities.
                    </p>
                </div>
            </div>
            
            <div className="hero-images animate-fadeInUp">
                <div className="images-container">
                    <div className="image-card">
                        <div className="image-wrapper">
                            <img src="/Images/pool.jpg" alt="Pet Pool Sessions" />
                            <div className="image-overlay">
                                <h3>Pool Sessions</h3>
                                <p>Fun swimming activities for your pets</p>
                            </div>
                        </div>
                    </div>
                    
                    <div className="image-card">
                        <div className="image-wrapper">
                            <img src="/Images/bath.jpg" alt="Pet Bathing Services" />
                            <div className="image-overlay">
                                <h3>Spa & Bath</h3>
                                <p>Relaxing bath and spa treatments</p>
                            </div>
                        </div>
                    </div>
                    
                    <div className="image-card">
                        <div className="image-wrapper">
                            <img src="/Images/groom.jpg" alt="Pet Grooming Services" />
                            <div className="image-overlay">
                                <h3>Professional Grooming</h3>
                                <p>Expert grooming for all breeds</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default HeroSection;
