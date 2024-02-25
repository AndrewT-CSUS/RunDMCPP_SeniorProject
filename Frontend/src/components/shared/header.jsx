import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import './header.css';
import ChurchLogo from '../../images/sgkc2_transparent_shadow.png';
import { useTranslation } from 'react-i18next';
import {Translate} from './translate.jsx'

// Header component
function Header() {
    const { t } = useTranslation();
  
    const location = useLocation(); // State: Current path. Used so we only render Header on certain pages.
  
    // Conditional: Don't render Header if the current path is '/loginpage'
    if (location.pathname === '/loginpage') {
        return null; // Don't render the Header on the login page
    }

    // Render the header
    return (
        <header>
            {/* Church Logo */}
            <img src={ChurchLogo} alt="Sacramento Glory Church Logo" className='sameLine'/>
            {/* Title*/}
            <h1 className="sameLine" id="sgkc">
                {t('sgkc')}
            </h1>
            {/* Eng/Kor Button*/}
            <Translate />
            {/* Navigation bar */}
            <ul className = "navigation-bar">
                {/* Dropdown menu for Events */}
                <li className="dropdown">
                <div className='dropbtn'>{t('eventsDrop')}</div>
                    <div className="dropdown-content">
                        <Link to="/events">{t('events')}</Link>
                        <Link to="/pastEvents">Past Events</Link>
                        <Link to="/photoGallery">{t('photos')}</Link>
                    </div>
                </li>
                {/* Dropdown menu for Sermons */}
                <li className="dropdown">
                <div className='dropbtn'>{t('sermons')}</div>
                    <div className="dropdown-content">
                        <Link to="/upcomingSerms">{t('upcomingSermons')}</Link>
                        <Link to="/previousSerms">{t('previousSermons')}</Link>
                    </div>
                </li>
                {/* (No dropwdown) Link for Services */}
                <li>
                    <Link to="/services">{t('services')}</Link>
                </li>
                {/* Dropdown menu for About Us */}
                <li className="dropdown">
                    <div className='dropbtn'>{t('aboutUs')}</div>
                    <div className="dropdown-content">
                        <Link to="/ourBeliefs">{t('ourBeliefs')}</Link>
                        <Link to="/ourGoals">Our Goals</Link>
                    </div>
                </li>
                <li>
                    <Link to="/">{t('home')}</Link>
                </li>
            </ul>
        </header>
    );
}

export default Header;