import React from 'react';
import {searchByName, createPreview, editEvent, deleteEventConfirmation} from './Javascript/eventEdit.js'

// AdminEventEdit Component
function AdminEventEdit() {
    return (
        <>
            <main>
                {/* Main content goes here */
                    <div className="mainContent" >
                        <fieldset id="searchField" className="searchBox">
                            <legend>Event Edit Search</legend>
                            <div>
                                <textarea rows="1" id="nameBox" name="nameBox" placeholder="Event Name"></textarea><br></br>
                                <button type="button" id="previewButton" onClick={searchByName}>Search</button>
                            </div>
                        </fieldset>
                        <br></br>
                        <fieldset id="resultsField" className="resultBox" hidden>
                            <legend>Search Result</legend>
                            <h4 id="resultTitle">Title</h4>
                        </fieldset>
                        <fieldset id="editField" className="editBox" hidden>
                            <legend>Edit Event</legend>
                            <br></br><textarea rows="1" cols="60" id="editNameBox" name="editNameBox" placeholder="Event Name"></textarea>
                            <br></br> <input type="datetime-local" id="editEventDateTime" name="editEventDateTime"/>
                            <br></br><textarea rows="4" cols="60" id="editDescriptionBox" name="editDescriptionBox" placeholder="Event Description"></textarea>
                            <br></br><textarea rows="4" cols="60" id="editLocationBox" name="editLocationBox" placeholder="Event Location"></textarea>
                            <br></br><button type="button" onClick={createPreview}>Preview</button>
                            <br></br><button type="button" onClick={deleteEventConfirmation}>Delete</button>
                        </fieldset>
                        <fieldset id="previewField" className="previewBox" hidden>
                            <legend>Event Preview</legend>
                            <h4 id="resultTitle">Title</h4>
                            <p id="resultDescription">Desc</p>
                            <p id="resultDateTime">Date and Time</p>
                            <p id="resultLocation">Location</p>
                            <br></br><button type="button" onClick={editEvent}>Edit Event</button>
                        </fieldset> 
                    </div>
                }
            </main>
        </>
    );
}

// Export the component as the default export
export default AdminEventEdit;
