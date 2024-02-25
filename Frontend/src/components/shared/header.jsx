import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import './header.css';
import ChurchLogo from '../../images/sgkc2_transparent_shadow.png';

// Header component
function Header() {

    const location = useLocation(); // State: Current path. Used so we only render Header on certain pages.
    const [language, setLanguage] = useState('한국어'); // State: Languages (Korean/English)

    // Function: Toggle between English and Korean
    function toggleText() {
        const newLanguage = language === '한국어' ? 'English' : '한국어';
        setLanguage(newLanguage);
    }

    // Conditional: Don't render Header if the current path is '/loginpage'
    if (location.pathname === '/loginpage') {
        return null; // Don't render the Header on the login page
    }

    // Render the header
    return (
        <header>
            {/* Church Logo */}
            <img src={ChurchLogo} alt="Sacramento Glory Church Logo"/>
            {/* Title*/}
            <h1 className="sameLine" id="sgkc">
                Sacramento Glory Korean Church
            </h1>
            {/* Eng/Kor Button*/}
            <button className="sameLine" onClick={() => toggleText(this)}>
                한국어
            </button>
            {/* Navigation bar */}
            <ul className = "navigation-bar">
                {/* Dropdown menu for Events */}
                <li className="dropdown">
                    <Link to="/events">Events ▼</Link>
                    <div className="dropdown-content">
                        <Link to="/events">Events</Link>
                        <Link to="/pastEvents">Past Events</Link>
                        <Link to="/photoGallery">Photo Gallery</Link>
                    </div>
                </li>
                {/* Dropdown menu for Sermons */}
                <li className="dropdown">
                    <Link to="/upcomingSerms" className="active">Sermons ▼</Link>
                    <div className="dropdown-content">
                        <Link to="/upcomingSerms" className="active">Upcoming</Link>
                        <Link to="/previousSerms">Previous</Link>
                    </div>
                </li>
                {/* (No dropwdown) Link for Services */}
                <li>
                    <Link to="/services">Services</Link>
                </li>
                {/* Dropdown menu for About Us */}
                <li className="dropdown">
                    <Link to="/aboutUs" className="dropbtn">About Us ▼</Link>
                    <div className="dropdown-content">
                        <Link to="/ourBeliefs">Our Beliefs</Link>
                        <Link to="/ourGoals">Our Goals</Link>
                    </div>
                </li>
                <li>
                    <Link to="/">Home</Link>
                </li>
            </ul>
        </header>
    );
}

export default Header;