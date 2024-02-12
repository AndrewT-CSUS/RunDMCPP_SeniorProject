import React from 'react';
import {searchByName, createPreview, editSermon, deleteSermonConfirmation} from './Javascript/sermonEdit.js'

// AdminSermonEdit Component
function AdminSermonEdit() {
    return (
        <>
            <main>
                {/* Main content goes here */
                    <div class="mainContent" >
                        <fieldset id="searchField" className="searchBox">
                            <legend>Sermon Edit Search</legend>
                            <div style={{display: "flex", alignItems: "center"}}>
                                <textarea rows="1" id="nameBox" name="nameBox" placeholder="Sermon Name"></textarea><br></br>
                                <button type="button" id="previewButton" onClick={searchByName}>Search</button>
                            </div>
                        </fieldset>
                        <br></br>
                        <fieldset id="resultsField" className="resultBox" hidden>
                            <legend>Search Result</legend>
                            <h4 id="resultTitle">Title</h4>
                        </fieldset>
                        <fieldset id="editField" className="editBox" hidden>
                            <legend>Edit Sermon</legend>
                            <br></br><textarea rows="1" cols="60" id="editNameBox" name="editNameBox" placeholder="Sermon Name"></textarea>
                            <br></br><textarea rows="1" cols="60" id="editYoutubeLinkBox" name="editYoutubeLinkBox" placeholder="YouTube Link"></textarea>
                            <br></br> <input type="datetime-local" id="editSermonDateTime" name="editSermonDateTime"/>
                            <br></br><textarea rows="4" cols="60" id="editDescriptionBox" name="editDescriptionBox" placeholder="Sermon Description"></textarea>
                            <br></br><iframe id="editYoutubeEmbed" width="420" height="315" src=""> </iframe>
                            <br></br><button type="button" onClick={createPreview}>Preview</button>
                            <br></br><button type="button" onClick={deleteSermonConfirmation}>Delete</button>
                        </fieldset>
                        <fieldset id="previewField" className="previewBox" hidden>
                            <legend>Sermon Preview</legend>
                            <h4 id="resultTitle">Title</h4>
                            <p id="resultDescription">Desc</p>
                            <p id="resultDateTime">Date and Time</p>
                            <iframe id="video" width="420" height="315" src=""> </iframe>
                            <br></br><button type="button" onClick={editSermon}  style={{width:"120px"}}>Edit Sermon</button>
                        </fieldset> 
                    </div>
                }
            </main>
        </>
    );
}

// Export the component as the default export
export default AdminSermonEdit;
