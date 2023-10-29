import React from "react";
import "./Navbar.css"
import {FaFacebookSquare,FaInstagramSquare,FaYoutubeSquare} from 'react-icons/fa';
import {MdDarkMode} from 'react-icons/md'



function navbar () {
    return (
        <>
                <nav className="main-nav">
                    {/*logo*/}
                    <div className="logo">
                        <h2>
                             <span>P</span>ET
                             <span>P</span>ARADISE
                        </h2>
                    </div>
                    {/*links*/}
                    <div className="menu-links">
                        <ul>
                            <li>
                              <a href="#">Home</a>
                            </li>
                            <li>
                                <a href="#">Boarding</a>
                            </li>
                            <li>
                                <a href="#">Pool Sessions</a>
                            </li>
                            <li>
                                <a href="#">Grooming</a>
                            </li>
                            <li>
                                <a href="#">Pet Shop</a>
                            </li>
                        </ul>
                    </div>
                    {/*social media*/}
                    <div className="social-media">
                            <ul className="social-media-desktop">
                                <li><MdDarkMode className="dark" /> </li>
                                <li><FaInstagramSquare className="insta"   color="#DD2A7B"/> </li>
                                <li><FaFacebookSquare className="fb"  color="BLUE"/> </li>
                                <li><FaYoutubeSquare className="yt"  color="red"/> </li>
                            </ul>
                    </div>
                </nav>
        </>
    )
}

export default navbar