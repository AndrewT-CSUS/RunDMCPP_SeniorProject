import React from 'react';
import {createPreview, addSermon} from './Javascript/sermonAdd.js'

// AdminSermonAdd Component
function AdminSermonAdd() {
    return (
        <>
            <main>
                {/* Main content goes here */
                    <div>
                        <fieldset className="addSermonBox">
                            <legend>Sermon Information</legend>
                            <br></br><textarea rows="1" cols="60" id="nameBox" name="nameBox" placeholder="Sermon Name"></textarea>
                            <br></br><textarea rows="1" cols="60" id="youtubeLinkBox" name="youtubeLinkBox" placeholder="YouTube Link"></textarea>
                            <br></br> <input type="datetime-local" id="sermonDateTime" name="sermonDateTime"/>
                            <br></br><textarea rows="4" cols="60" id="descriptionBox" name="descriptionBox" placeholder="Sermon Description"></textarea>
                            <br></br><button type="button" id="previewButton" onClick={createPreview} style = {{width:"120px"}}>Preview</button>
                        </fieldset>
                        <fieldset id="resultsField" className="previewBox" hidden>
                            <legend>Sermon Preview</legend>
                            <h4 id="resultTitle">Title</h4>
                            <p id="resultDescription">Desc</p>
                            <p id="resultDateTime">Date and Time</p>
                            <iframe id="video" width="420" height="315" src=""> </iframe>
                            <br></br><button type="button" onClick={addSermon}  style={{width:"120px"}}>Add Sermon</button>
                        </fieldset> 
                    </div>
                }
            </main>
        </>
    );
}

// Export the component as the default export
export default AdminSermonAdd;
