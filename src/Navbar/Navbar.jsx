import React, {useState} from "react";
import {Link} from 'react-router-dom';
import "./Navbar.css"
import {FaFacebookSquare,FaInstagramSquare,FaYoutubeSquare} from 'react-icons/fa';
import {MdDarkMode} from 'react-icons/md'
import DarkMode from "../DarkMode/DarkMode.jsx";
import {GiHamburgerMenu} from "react-icons/gi";



function navbar () {
    const [showMediaIcons,setShowMediaIcons]=useState(false)
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
                    <div className={showMediaIcons ? "mobile-menu-link":"menu-link"}>
                        <ul>
                            <li>
                              <Link to="/">Home</Link>
                            </li>
                            <li>
                                <Link to="/Boarding">Boarding</Link>
                            </li>
                            <li>
                                <Link to="/PoolSessions">Pool Sessions</Link>
                            </li>
                            <li>
                                <Link to="/">Grooming</Link>
                            </li>
                            <li>
                                <Link to="/">Pet Shop</Link>
                            </li>
                        </ul>
                    </div>
                    {/*social media*/}
                    <div className="social-media">
                            <ul className="social-media-desktop">
                                <li className="darkmode"><DarkMode/> </li>
                                <li><FaInstagramSquare className="insta"   color="#DD2A7B"/> </li>
                                <li><FaFacebookSquare className="fb"  color="BLUE"/> </li>
                                <li><FaYoutubeSquare className="yt"  color="red"/> </li>
                            </ul>
                        <div className="hamburger-menu">
                            <Link to="/" onClick={() => setShowMediaIcons(!showMediaIcons)}>
                                <GiHamburgerMenu />
                            </Link>
                        </div>
                    </div>

                </nav>
        </>
    )
}

export default navbar