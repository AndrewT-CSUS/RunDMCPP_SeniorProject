import React from 'react';
import {createPreview, addEvent} from './Javascript/eventAdd.js'

// Define your functional component
function AdminEventAdd() {
    return (
        <>
            <meta charSet="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <title>adminEventCreate</title>
            <main>
                {/* Main content goes here */}
                <h1>Create Event</h1>
                <div className="mainContent" >
            <fieldset className="addEventBox">
                <legend>Event Information</legend>
                <br /><textarea rows="1" cols="60" id="nameBox" name="nameBox" placeholder="Event Name"></textarea>
                <br /> <input type="datetime-local" id="eventDateTime" name="eventDateTime"/> 
                <br /><textarea rows="2" cols="60" id="locationBox" name="locationBox" placeholder="Event Location"></textarea>
                <br /><textarea rows="4" cols="60" id="descriptionBox" name="descriptionBox" placeholder="Event Description"></textarea>
                <br /><button type="button" id="previewButton" onClick={createPreview} style = {{width: '120 px'}}>Preview</button>
            </fieldset>

            <fieldset id="resultsField" className="previewBox" hidden>
                <legend>Event Preview</legend>
                <h4 id="resultTitle">Title</h4>
                <p id="resultLocation">Location</p>
                <p id="resultDescription">Description</p>
                <p id="resultDateTime">Date and Time</p>
                <br /><button type="button" onClick={addEvent} style = {{width: '120 px'}}>Add Event</button>
            </fieldset>
        </div>
            </main>
        </>
    );
}

// Export the component as the default export
export default AdminEventAdd;
