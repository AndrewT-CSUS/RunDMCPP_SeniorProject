import React from 'react';
import {searchByName, searchByDate} from './Javascript/previousSerms.js'

// Define your functional component
function PreviousSerms() {
    return (
        <>
            <main>
                <fieldset id="searchField" className="searchBox" >
                    <legend>Sermon Search</legend>
                    {/* Name Search */}
                    <h3>Name Search</h3>
                    <div style={{display: "flex", alignItems: "center"}}>
                        <textarea rows="1" id="searchBox" name="searchBox" placeholder="Sermon Name"></textarea><br></br>
                    </div>
                    <div><br /> </div>
                    <div style={{display: "flex", flexWrap: "wrap",  alignItems: "left", justifyContent: "left"}}>
                        <button type="button" id="searchByNameButton" onClick={searchByName}>Search By Name</button>
                    </div>
                    <div><br /> </div>
                    {/* Date Search */}
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
        </>
    );
}

export default PreviousSerms;

