import React, { useState } from 'react';

// Schedule Component
function Schedule() {
    const [language, setLanguage] = useState('한국어');

    // Function: Toggle between English and Korean
    function toggleText() {
        const newLanguage = language === '한국어' ? 'English' : '한국어';
        setLanguage(newLanguage);
    }

    return (
        <>
            <main>
                {/* Main content goes here*/}
                <h2 id="serviceSchedule">Church Service Schedule</h2>
                {/*Schedule description maybe? */}
                <table border={5}>
                    <tbody>
                        <tr>
                            <th id="serviceScheduleDay">Day</th>
                            <th id="serviceScheduleTime">Time</th>
                        </tr>
                        <tr>
                            <td id="serviceScheduleSunday">Sunday</td>
                            <td>11:00 AM</td>
                        </tr>
                        <tr>
                            <td id="serviceScheduleWednesday">Wednesday</td>
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
