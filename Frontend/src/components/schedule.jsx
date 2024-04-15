import React, { useState } from 'react';
import { useTranslation } from 'react-i18next';
import AnnouncementsCarousalComp from './AnnouncementsCarousalComp';
import MainNavBits from './MainNavBits.jsx';
import './schedule.css'; 

// Schedule Component
function Schedule() {
    const { t } = useTranslation(); 
    return (
        <>
            <main className="schedule-main">
                <AnnouncementsCarousalComp />
                <MainNavBits />
                {/* Main content goes here*/}
                <div className="schedule-container"> {}
                    <h2 className="schedule-heading">{t('serviceSchedule')}</h2> {}
                    <table className="schedule-table"> {}
                        <tbody>
                            <tr>
                                <th id="serviceScheduleDay">{t('serviceScheduleDay')}</th>
                                <th id="serviceScheduleTime">{t('serviceScheduleTime')}</th>
                            </tr>
                            <tr>
                                <td id="serviceScheduleSunday">{t('serviceScheduleSunday')}</td>
                                <td>11:00 AM</td>
                            </tr>
                            <tr>
                                <td id="serviceScheduleWednesday">{t('serviceScheduleWednesday')}</td>
                                <td> 07:30 PM</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <p></p>
            </main>
        </>
    );
}

export default Schedule;


