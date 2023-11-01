import React from 'react';
import "../App.css"
import "./HeroSection.css"
import {FaPaw} from "react-icons/fa";
function HeroSection() {
    return (
        <>
            <div className="hero-container">
                   {/* <video src="../../public/dog1%20(online-video-cutter.com).mp4" autoPlay loop muted />*/}
                <h1><FaPaw style={{marginRight : "30px"}}/>A paradise for your four-legged BFF <FaPaw  style={{marginLeft : "30px"}} /></h1>
                <h3> Your pet’s care is our priority – be it on a short weekend getaway or a long vacation.
                    We provide the best home pet boarding in Bangalore for cats and dogs with the utmost care and safety while you relax, unwind, recharge.
                    You don’t have to look for a cat/dog care centre or a doggy day-care either. Let our devoted pet boarders treat your fur-babies with the love and care they deserve.
                    We provide constant updates of all the woofs and meows, keeping a track of their activities.</h3>
            </div>
            <div className="Hero-images">
                <div className="images">
                    <ul>
                        <li id="li1"> <img src="../../public/Images/pool.jpg" alt="Image Not Loading" />   </li>
                        <li id="li2">  <img src="../../public/Images/bath.jpg" alt="Image Not Loading" />  </li>
                        <li id="li3">  <img src="../../public/Images/groom.jpg" alt="Image Not Loading" />    </li>
                    </ul></div>
            </div>


        </>
    )
}
export default HeroSection