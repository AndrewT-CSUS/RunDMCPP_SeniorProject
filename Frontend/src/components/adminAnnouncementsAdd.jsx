import React from 'react';

import {createPreview, addAnnouncement} from './Javascript/announcementsAdd.js'

function AdminAnnouncementsAdd() {
    return (
        <>
            <meta charSet="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <title>ChurchTemplate</title>
            <link rel="stylesheet" href="header.css" />
            <main>
                {/* Main content goes here */
                    <div>
                        <fieldset className="addAnnounementBox">
                            <legend>Announement Information</legend>
                            <br></br><textarea rows="1" cols="60" id="titleBox" name="titleBox" placeholder="Announement title"></textarea>
                            <br></br><textarea rows="4" cols="60" id="descriptionBox" name="descriptionBox" placeholder="Announement Description"></textarea>
                            <br></br><button type="button" id="previewButton" onClick={createPreview} style = {{width:"120px"}}>Preview</button>
                        </fieldset>
                        <fieldset id="resultsField" className="previewBox" hidden>
                            <legend>Announement Preview</legend>
                            <h4 id="resultTitle">Title</h4>
                            <p id="resultDescription">Desc</p>
                            <br></br><button type="button" onClick={addAnnouncement}  style={{width:"120px"}}>Add Announement</button>
                        </fieldset> 
                    </div>
                }
            </main>
        </>
    );
}

export default AdminAnnouncementsAdd;