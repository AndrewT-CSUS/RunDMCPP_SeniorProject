import React from 'react';
import { useLocation } from 'react-router-dom';
import './footer.css';
import FaceBookLogo from '../../images/facebook_logo.png';
import YouTubeLogo from '../../images/youtube_logo.png';
import { useTranslation } from 'react-i18next';
import {Translate} from './translate.jsx'

function Footer() {
    const { t } = useTranslation(); // State: Translation
    const location = useLocation(); // State: Current path. Used so we only render Header on certain pages.
    const stickyFooterPaths = ['/adminPage', '/adminAnnouncementsAdd', '/adminAnnouncementsEdit', '/adminEventAdd', '/adminEventEdit', '/adminSermonAdd', '/adminSermonEdit'];

    // Conditional: Don't render Footer if the current path is '/loginpage'
    if (location.pathname === '/loginpage') {
        return null; // Don't render the Footer on the login page
    }

    return (
        <footer className={stickyFooterPaths.includes(location.pathname) ? 'sticky-footer' : ''}>
            {/* Footer left side: Address */}
            <p className="footer__left">
                <b className="footer-title">{t('sgkc')}</b><br></br>
                1820 Bell Street<br></br>
                Sacramento, CA. 95825
            </p>
            {/* Footer right side: Links to email, YouTube, FaceBook */}
            <div className="footer__right">
                <p>{t('contactUs')}<a href="mailto:sacglorychurch@hotmail.com">sacglorychurch@hotmail.com</a></p>
                <div className="container-link-and-logo">
                    <img src={YouTubeLogo} alt="YouTube Logo"></img>
                    <a href="https://www.youtube.com/@jun0698" target="_blank" rel="noopener noreferrer">{t('youtubeChannel')}</a>
                </div>

                <div className="container-link-and-logo">
                    <img src={FaceBookLogo} alt="Facebook Logo"></img>
                    <a href="https://www.facebook.com/sacglorychurch?mibextid=ZbWKwL" target="_blank" rel="noopener noreferrer">{t('facebook')}</a>
                </div>
            </div>
        </footer>
    );
}

export default Footer;
