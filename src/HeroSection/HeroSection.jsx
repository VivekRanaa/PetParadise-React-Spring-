import React from 'react';
import "../App.css";
import "./HeroSection.css";
import { FaPaw } from "react-icons/fa";
function HeroSection() {
    return (
        <div className="hero-section page-wrapper">
            <div className="hero-container">
                   {/* <video src="../../public/dog1%20(online-video-cutter.com).mp4" autoPlay loop muted />*/}
                <h1 className="hero-title">
                    <FaPaw className="paw-icon paw-left" />
                    A paradise for your four-legged BFF
                    <FaPaw className="paw-icon paw-right" />
                </h1>
                <h3> Your pet’s care is our priority – be it on a short weekend getaway or a long vacation.
                    We provide the best home pet boarding in Bangalore for cats and dogs with the utmost care and safety while you relax, unwind, recharge.
                    You don’t have to look for a cat/dog care centre or a doggy day-care either. Let our devoted pet boarders treat your fur-babies with the love and care they deserve.
                    We provide constant updates of all the woofs and meows, keeping a track of their activities.</h3>
            </div>
            <div className="Hero-images">
                <div className="images">
                    <ul>
                        <li id="li1"> <img src="/Images/pool.jpg" alt="Professional Pet Pool Therapy Session" />   </li>
                        <li id="li2">  <img src="/Images/bath.jpg" alt="Premium Pet Bathing and Spa Services" />  </li>
                        <li id="li3">  <img src="/Images/groom.jpg" alt="Expert Pet Grooming and Styling" />    </li>
                    </ul></div>
            </div>


        </div>
    )
}
export default HeroSection