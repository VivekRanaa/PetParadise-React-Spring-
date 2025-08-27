import React, {useState, useEffect} from "react";
import {Link, useLocation} from 'react-router-dom';
import "./Navbar.css"
import {FaShoppingCart, FaUser, FaSignOutAlt, FaClipboardList} from 'react-icons/fa';
import {HiX} from 'react-icons/hi';
import DarkMode from "../DarkMode/DarkMode.jsx";
import {GiHamburgerMenu} from "react-icons/gi";
import { useAuth } from '../context/AuthContext';
import { useCart } from '../context/CartContext';

function Navbar() {
    const [showMobileMenu, setShowMobileMenu] = useState(false);
    const [scrolled, setScrolled] = useState(false);
    const [showUserMenu, setShowUserMenu] = useState(false);
    const location = useLocation();
    
    const { user, logout, isAuthenticated } = useAuth();
    const { getCartItemCount } = useCart();

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
        setShowUserMenu(false);
    }, [location]);

    const toggleMobileMenu = (e) => {
        e.preventDefault();
        setShowMobileMenu(!showMobileMenu);
    };

    const handleLogout = () => {
        logout();
        setShowUserMenu(false);
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

                {/* Right Side Actions */}
                <div className="navbar-actions">
                    <ul className="navbar-actions-desktop">
                        <li className="darkmode">
                            <DarkMode />
                        </li>
                        
                        {/* Cart Icon */}
                        {isAuthenticated && (
                            <li className="cart-item">
                                <Link to="/cart" className="cart-link">
                                    <FaShoppingCart className="cart-icon" />
                                    {getCartItemCount() > 0 && (
                                        <span className="cart-count">{getCartItemCount()}</span>
                                    )}
                                </Link>
                            </li>
                        )}

                        {/* Authentication */}
                        {isAuthenticated ? (
                            <li className="user-menu-container">
                                <div 
                                    className="user-menu-trigger"
                                    onClick={() => setShowUserMenu(!showUserMenu)}
                                >
                                    <FaUser className="user-icon" />
                                    <span className="user-name">Hi, {user?.firstName}!</span>
                                </div>
                                {showUserMenu && (
                                    <div className="user-dropdown">
                                        <Link to="/orders" className="dropdown-item">
                                            <FaClipboardList /> Orders
                                        </Link>
                                        <button onClick={handleLogout} className="dropdown-item logout-btn">
                                            <FaSignOutAlt /> Logout
                                        </button>
                                    </div>
                                )}
                            </li>
                        ) : (
                            <>
                                <li>
                                    <Link to="/login" className="auth-link">Login</Link>
                                </li>
                                <li>
                                    <Link to="/register" className="auth-link signup">Sign Up</Link>
                                </li>
                            </>
                        )}
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
                    
                    {/* Mobile Auth Links */}
                    {isAuthenticated ? (
                        <>
                            <li>
                                <Link to="/cart" onClick={() => setShowMobileMenu(false)}>
                                    Cart ({getCartItemCount()})
                                </Link>
                            </li>
                            <li>
                                <Link to="/orders" onClick={() => setShowMobileMenu(false)}>
                                    Orders
                                </Link>
                            </li>
                            <li>
                                <button onClick={handleLogout} className="mobile-logout-btn">
                                    Logout
                                </button>
                            </li>
                        </>
                    ) : (
                        <>
                            <li>
                                <Link to="/login" onClick={() => setShowMobileMenu(false)}>
                                    Login
                                </Link>
                            </li>
                            <li>
                                <Link to="/register" onClick={() => setShowMobileMenu(false)}>
                                    Sign Up
                                </Link>
                            </li>
                        </>
                    )}
                </ul>
                
                {/* Mobile Dark Mode */}
                <div className="mobile-actions">
                    <DarkMode />
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