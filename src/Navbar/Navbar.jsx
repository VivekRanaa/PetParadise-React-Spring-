import React, {useState, useEffect} from "react";
import {Link, useLocation} from 'react-router-dom';
import "./Navbar.css"
import {FaFacebookSquare,FaInstagramSquare,FaYoutubeSquare} from 'react-icons/fa';
import {HiX} from 'react-icons/hi';
import DarkMode from "../DarkMode/DarkMode.jsx";
import {GiHamburgerMenu} from "react-icons/gi";

function Navbar() {
    const [showMobileMenu, setShowMobileMenu] = useState(false);
    const [scrolled, setScrolled] = useState(false);
    const location = useLocation();

    // Handle scroll effect for navbar
    useEffect(() => {
        const handleScroll = () => {
            const isScrolled = window.scrollY > 10;
            setScrolled(isScrolled);
        };

        window.addEventListener('scroll', handleScroll);
        return () => window.removeEventListener('scroll', handleScroll);
    }, []);

    // Close mobile menu when route changes
    useEffect(() => {
        setShowMobileMenu(false);
    }, [location]);

    const toggleMobileMenu = (e) => {
        e.preventDefault();
        setShowMobileMenu(!showMobileMenu);
    };

    const navLinks = [
        { path: "/", label: "Home" },
        { path: "/Boarding", label: "Boarding" },
        { path: "/PoolSessions", label: "Pool Sessions" },
        { path: "/Grooming", label: "Grooming" },
        { path: "/PetShop", label: "Pet Shop" },
    ];

    return (
        <>
            <nav className={`main-nav ${scrolled ? 'scrolled' : ''}`}>
                <div className="logo">
                    <Link to="/">
                        <h2>
                            <span>P</span>ET<span>P</span>ARADISE
                        </h2>
                    </Link>
                </div>

                {/* Desktop Navigation */}
                <div className="menu-link">
                    <ul>
                        {navLinks.map((link) => (
                            <li key={link.path}>
                                <Link 
                                    to={link.path}
                                    className={location.pathname === link.path ? 'active' : ''}
                                >
                                    {link.label}
                                </Link>
                            </li>
                        ))}
                    </ul>
                </div>

                {/* Social Media & Dark Mode */}
                <div className="social-media">
                    <ul className="social-media-desktop">
                        <li className="darkmode">
                            <DarkMode />
                        </li>
                        <li>
                            <a href="https://instagram.com" target="_blank" rel="noopener noreferrer">
                                <FaInstagramSquare className="insta" />
                            </a>
                        </li>
                        <li>
                            <a href="https://facebook.com" target="_blank" rel="noopener noreferrer">
                                <FaFacebookSquare className="fb" />
                            </a>
                        </li>
                        <li>
                            <a href="https://youtube.com" target="_blank" rel="noopener noreferrer">
                                <FaYoutubeSquare className="yt" />
                            </a>
                        </li>
                    </ul>

                    {/* Mobile Hamburger Menu */}
                    <div className="hamburger-menu" onClick={toggleMobileMenu}>
                        {showMobileMenu ? <HiX /> : <GiHamburgerMenu />}
                    </div>
                </div>
            </nav>

            {/* Mobile Navigation Menu */}
            <div className={`mobile-menu-link ${showMobileMenu ? 'active' : ''}`}>
                <ul>
                    {navLinks.map((link) => (
                        <li key={link.path}>
                            <Link 
                                to={link.path}
                                className={location.pathname === link.path ? 'active' : ''}
                                onClick={() => setShowMobileMenu(false)}
                            >
                                {link.label}
                            </Link>
                        </li>
                    ))}
                </ul>
                
                {/* Mobile Social Media */}
                <div className="mobile-social">
                    <DarkMode />
                    <div className="mobile-social-icons">
                        <a href="https://instagram.com" target="_blank" rel="noopener noreferrer">
                            <FaInstagramSquare className="insta" />
                        </a>
                        <a href="https://facebook.com" target="_blank" rel="noopener noreferrer">
                            <FaFacebookSquare className="fb" />
                        </a>
                        <a href="https://youtube.com" target="_blank" rel="noopener noreferrer">
                            <FaYoutubeSquare className="yt" />
                        </a>
                    </div>
                </div>
            </div>

            {/* Mobile Menu Overlay */}
            {showMobileMenu && (
                <div 
                    className="mobile-menu-overlay"
                    onClick={() => setShowMobileMenu(false)}
                />
            )}
        </>
    );
}

export default Navbar;