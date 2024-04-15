import React, { useState } from 'react';
import { useTranslation } from 'react-i18next';
import AnnouncementsCarousalComp from './AnnouncementsCarousalComp';
import MainNavBits from './MainNavBits.jsx';
import './schedule.css'; 
import Image1 from '../images/dayview_church.jpg';
import Image2 from '../images/nightview_church.jpg';

// Schedule Component
function Schedule() {
    const { t } = useTranslation(); 
    return (
        <>
            <main className="schedule-main">
                <AnnouncementsCarousalComp />
                <MainNavBits />
                <div className="schedule-container">
                    {/* Sunday Schedule */}
                    <div className="schedule-box">
                        <div className="schedule-content">
                            <img src={Image1} className='schedule-img1'></img>
                            <p>{t('serviceScheduleSunday')} 11:00 AM</p>
                        </div>
                    </div>
                    {/* Wednesday Schedule */}
                    <div className="schedule-box2">
                        <div className="schedule-content">
                            <img src={Image2} className='schedule-img2'></img>
                            <p>{t('serviceScheduleWednesday')} 7:30 PM</p>
                        </div>
                    </div>
                </div>
                <p></p>
            </main>
        </>
    );
}

export default Schedule;
