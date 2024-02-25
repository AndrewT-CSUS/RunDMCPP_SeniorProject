import React, { useState } from 'react';
import { useTranslation } from 'react-i18next';
import AnnouncementsCarousalComp from './AnnouncementsCarousalComp';

// Schedule Component
function Schedule() {
    const { t } = useTranslation(); 
    return (
        <>
            <main>
                <AnnouncementsCarousalComp />
                {/* Main content goes here*/}
                <h2 id="serviceSchedule">{t('serviceSchedule')}</h2>
                {/*Schedule description maybe? */}
                <table border={5}>
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
                <p>This is the Home page</p>
            </main>
        </>
    );
}

export default Schedule;