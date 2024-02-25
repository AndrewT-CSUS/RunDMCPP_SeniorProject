import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './header.css';
import ChurchLogo from '../../images/sgkc2_transparent_shadow.png';
import { useTranslation } from 'react-i18next';
import {Translate} from './translate.jsx'

// Header component
function Header() {
    const { t } = useTranslation();
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
            <ul>
                <li className="dropdown">
                <div className='dropbtn'>{t('eventsDrop')}</div>
                    <div className="dropdown-content">
                        <Link to="/events">{t('events')}</Link>
                        <Link to="/pastEvents">Past Events</Link>
                        <Link to="/photoGallery">{t('photos')}</Link>
                    </div>
                </li>
                <li className="dropdown">
                <div className='dropbtn'>{t('sermons')}</div>
                    <div className="dropdown-content">
                        <Link to="/upcomingSerms">{t('upcomingSermons')}</Link>
                        <Link to="/previousSerms">{t('previousSermons')}</Link>
                    </div>
                </li>
                <li>
                    <Link to="/services">{t('services')}</Link>
                </li>
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