import React from 'react';
import {searchByName, searchByDate} from './Javascript/previousSerms.js'

// Define your functional component
function PreviousSerms() {
    return (
        <>
            <meta charSet="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <title>previousSerms</title>
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
                        <a href="javascript:void(0)" className="active dropbtn">
                            Sermons ▼
                        </a>
                        <div className="dropdown-content">
                            <a href="upcomingSerms">Upcoming</a>
                            <a href="previousSerms" className="active">
                                Previous
                            </a>
                        </div>
                    </li>
                    <li>
                        <a href="services">Services</a>
                    </li>
                    <li className="dropdown">
                        <a href="javascript:void(0)" className="dropbtn">
                            About Us ▼
                        </a>
                        <div className="dropdown-content">
                            <a href="ourBeliefs">Our Beliefs</a>
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
                <fieldset id="searchField" className="searchBox" >
                    <legend>Sermon Search</legend>
                    <h3>Name Search</h3>
                    <div style={{display: "flex", alignItems: "center"}}>
                        <textarea rows="1" id="searchBox" name="searchBox" placeholder="Sermon Name"></textarea><br></br>
                    </div>
                    <div><br /> </div>
                    <div style={{display: "flex", flexWrap: "wrap",  alignItems: "left", justifyContent: "left"}}>
                        <button type="button" id="searchByNameButton" onClick={searchByName}>Search By Name</button>
                    </div>
                    <div><br /> </div>
                    <h3>Date Search</h3>
                    <div>
                        <p style={{margin: "0"}}>Start Date</p>
                        <input type="date" id="startDateBox" name="startDateBox"/>
                    </div>
                    <div>
                        <br/>
                        <p style={{margin: "0"}}>End Date</p>
                        <input type="date" id="endDateBox" name="endDateBox"/>
                    </div>
                    <div><br /> </div>
                    <div style={{display: "flex", flexWrap: "wrap",  alignItems: "left", justifyContent: "left"}}>
                        <button type="button" id="searchByDateButton" onClick={searchByDate} style={{ align: "left"}}>Search By Date</button>
                    </div>
                </fieldset>
                <br></br>
                <fieldset id="resultsField" className="resultBox" hidden>
                    <legend id="resultsLegend">Search Result</legend>
                    <h4 id="resultTitle">Title</h4>
                </fieldset>
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

// Export the component as the default export
export default PreviousSerms;

