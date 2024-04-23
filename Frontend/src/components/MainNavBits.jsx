import React from 'react';
import { Link } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import './MainNavBits.css'; 

function MainNavBits() {
    const { t } = useTranslation(); 
    return (
        <div className="main-container">
            <div className="container-group">
                <Link to="/events" className="container">
                    {t('events')}
                </Link>
                <Link to="/photoGallery" className="container">
                    {t('photos')}
                </Link>
                <Link to="/sermons" className="container">
                    {t('sermons')}
                </Link>
                <Link to="/aboutUs" className="container">
                    {t('aboutUs')}
                </Link>
            </div>
        </div>
    );
}

export default MainNavBits;
