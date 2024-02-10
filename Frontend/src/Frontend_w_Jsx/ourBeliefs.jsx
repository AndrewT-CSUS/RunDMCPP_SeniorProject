import React from 'react';

function OurBeliefs() {
    return (
        <>
            <meta charSet="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <title>ourBeliefs</title>
            <link rel="stylesheet" href="header.css" />
            <header>
                {/*Title*/}
                <h1>
                    Sacramento Glory
                    <br />
                    Korean Church
                </h1>
                {/* Navigation bar */}
                <ul>
                    <li>
                        <a href="announcements">Announcements</a>
                    </li>
                    <li className="dropdown">
                        <a href="javascript:void(0)" className="dropbtn">
                            Events ▼
                        </a>
                        <div className="dropdown-content">
                            <a href="events">Events</a>
                            <a href="pastEvents">Past Events</a>
                            <a href="photoGallery">Photo Gallery</a>
                        </div>
                    </li>
                    <li className="dropdown">
                        <a href="javascript:void(0)" className="dropbtn">
                            Sermons ▼
                        </a>
                        <div className="dropdown-content">
                            <a href="upcomingSerms">Upcoming</a>
                            <a href="previousSerms">Previous</a>
                        </div>
                    </li>
                    <li>
                        <a href="services">Services</a>
                    </li>
                    <li className="dropdown">
                        <a href="javascript:void(0)" className="active dropbtn">
                            About Us ▼
                        </a>
                        <div className="dropdown-content">
                            <a href="ourBeliefs" className="active">
                                Our Beliefs
                            </a>
                            <a href="ourGoals">Our Goals</a>
                            <a href="dateTime">Date / Time</a>
                            <a href="languages">Language</a>
                        </div>
                    </li>
                    <li>
                        <a href="schedule">Home</a>
                    </li>
                </ul>
            </header>
            <main>
                {/* Main content goes here */}
                <p>This is the Our Beliefs page</p>
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

export default OurBeliefs;
