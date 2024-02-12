import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './header.css';
import ChurchLogo from '../../images/sgkc2_transparent_shadow.png';

// Header component
function Header() {

    // State: Languages (Korean/English)
    const [language, setLanguage] = useState('한국어');

    // Function: Toggle between English and Korean
    function toggleText() {
        const newLanguage = language === '한국어' ? 'English' : '한국어';
        setLanguage(newLanguage);
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
            <ul>
                <li className="dropdown">
                    <Link to="/events">Events ▼</Link>
                    <div className="dropdown-content">
                        <Link to="/events">Events</Link>
                        <Link to="/pastEvents">Past Events</Link>
                        <Link to="/photoGallery">Photo Gallery</Link>
                    </div>
                </li>
                <li className="dropdown">
                    <Link to="/upcomingSerms" className="active">Sermons ▼</Link>
                    <div className="dropdown-content">
                        <Link to="/upcomingSerms" className="active">Upcoming</Link>
                        <Link to="/previousSerms">Previous</Link>
                    </div>
                </li>
                <li>
                    <Link to="/services">Services</Link>
                </li>
                <li className="dropdown">
                    <Link to="/aboutUs" className="dropbtn">About Us ▼</Link>
                    <div className="dropdown-content">
                        <Link to="/ourBeliefs">Our Beliefs</Link>
                        <Link to="/ourGoals">Our Goals</Link>
                        <Link to="/dateTime">Date / Time</Link>
                        <Link to="/languages">Language</Link>
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