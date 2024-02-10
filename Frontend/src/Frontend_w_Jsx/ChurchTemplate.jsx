import React, { useState } from 'react';
import './header.css';

function Schedule() {
    const [language, setLanguage] = useState('한국어');

    function toggleText() {
        const newLanguage = language === '한국어' ? 'English' : '한국어';
        setLanguage(newLanguage);
    }

    return (
        <>
            <meta charSet="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <title>homePage</title>
            <link rel="stylesheet" href="header.css" />
            <header>
                {/*Church Logo */}
                <img src="imageWeb/sgkc4.png" width="225px" height="60px" />
                {/*Title*/}
                <h1 className="sameLine" id="sgkc">
                    Sacramento Glory Korean Church
                </h1>
                {/*Eng/Kor Button*/}
                <button className="sameLine" onClick={() => toggleText(this)}>
                    한국어
                </button>
                {/* Navigation bar */}
                <ul>
                    <li>
                        <a href="announcements" id="announcements">
                            Announcements
                        </a>
                    </li>
                    <li className="dropdown">
                        <a href="javascript:void(0)" className="dropbtn" id="eventsDrop">
                            Events ▼
                        </a>
                        <div className="dropdown-content">
                            <a href="events" id="eventsPage">
                                Events
                            </a>
                            <a href="pastEvents" id="past events">
                                Past Events
                            </a>
                            <a href="photoGallery" id="photoGallery">
                                Photo Gallery
                            </a>
                        </div>
                    </li>
                    {/* Other navigation items */}
                </ul>
            </header>
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
            <footer>
                {/* Footer content goes here */}
                <p className="sameLine" style={{ fontSize: 12 }}>
                    Sacramento Glory Korean Church
                    <br />
                    1820 Bell Street
                    <br />
                    Sacramento, CA. 95825
                </p>
                <p className="sameLine" style={{ fontSize: 12, float: "right" }}>
                    Contact Us:{" "}
                    <a href="mailto: sacglorychurch@hotmail.com">
                        sacglorychurch@hotmail.com
                    </a>
                </p>
            </footer>
        </>
    );
}

export default Schedule;
