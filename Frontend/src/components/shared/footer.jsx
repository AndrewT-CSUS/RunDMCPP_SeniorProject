import React from 'react';
import './footer.css';
import FaceBookLogo from '../../images/facebook_logo.png';
import YouTubeLogo from '../../images/youtube_logo.png';

function Footer() {

    return (
        <footer>
        {/* Footer left side: Address */}
            <p className="footer__left">
                Sacramento Glory Korean Church<br></br>
                1820 Bell Street<br></br>
                Sacramento, CA. 95825
            </p>
        {/* Footer right side: Links to email, YouTube, FaceBook */}
        {/* TODO: YouTube & Facebook links & names have placeholders for now */}
            <div className="footer__right">
                <p>Contact Us: <a href="mailto:sacglorychurch@hotmail.com">sacglorychurch@hotmail.com</a></p>
                <a href="https://www.youtube.com/@jun0698" target="_blank" rel="noopener noreferrer">
                    <img src={YouTubeLogo} alt="YouTube Logo"></img>YouTube Channel
                </a>
                <a href="https://www.facebook.com/" target="_blank" rel="noopener noreferrer">
                    <img src={FaceBookLogo} alt="Facebook Logo"></img>Facebook Page
                </a>
            </div>
        </footer>       
    );

}

export default Footer;