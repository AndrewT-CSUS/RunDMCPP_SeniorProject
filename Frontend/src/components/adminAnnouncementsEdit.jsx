import React from 'react';
import {searchByName, createPreview, editAnnouncement, deleteAnnouncementConfirmation} from './Javascript/announcementsEdit.js'

// AdminAnnouncementEdit Component
function AdminAnnouncementsEdit() {
    return (
        <>
            <main>
                {/* Main content goes here */
                    <div className="mainContent" >
                        <fieldset id="searchField" className="searchBox">
                            <legend>Announcement Edit Search</legend>
                            <div style={{ display: "flex", alignItems: "center" }}>
                                <textarea rows="1" id="nameBox" name="nameBox" placeholder="Announcement Name"></textarea><br></br>
                                <button type="button" id="previewButton" onClick={searchByName}>Search</button>
                            </div>
                        </fieldset>
                        <br></br>
                        <fieldset id="resultsField" className="resultBox" hidden>
                            <legend>Search Result</legend>
                            <h4 id="resultTitle">Title</h4>
                        </fieldset>
                        <fieldset id="editField" className="editBox" hidden>
                            <legend>Edit Announcement</legend>
                            <br></br><textarea rows="1" cols="60" id="editNameBox" name="editNameBox" placeholder="Announcement Name"></textarea>
                            <br></br><textarea rows="4" cols="60" id="editDescriptionBox" name="editDescriptionBox" placeholder="Announcement Description"></textarea>
                            <br></br><button type="button" onClick={createPreview}>Preview</button>
                            <button type="button" onClick={deleteAnnouncementConfirmation}>Delete</button>
                        </fieldset>
                        <fieldset id="previewField" className="previewBox" hidden>
                            <legend>Announcement Preview</legend>
                            <h4 id="resultTitle">Title</h4>
                            <p id="resultDescription">Desc</p>
                            <br></br><button type="button" onClick={editAnnouncement} style={{ width: "120px" }}>Edit Announcement</button>
                        </fieldset>
                    </div>
                }
            </main>
        </>
    );
}

// Export the component as the default export
export default AdminAnnouncementsEdit;
