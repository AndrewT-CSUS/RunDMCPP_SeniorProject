import React from 'react';
import {searchByName, createPreview, editSermon, deleteSermonConfirmation} from './Javascript/sermonEdit.js'
import { useTranslation } from 'react-i18next';

// AdminSermonEdit Component
function AdminSermonEdit() {
    const { t } = useTranslation();
    return (
        <>
            <main>
                {/* Main content goes here */
                    <div class="mainContent" >
                        <fieldset id="searchField" className="searchBox">
                            <legend>{t('sermonSearch')}</legend>
                            <div style={{display: "flex", alignItems: "center"}}>
                                <textarea rows="1" id="nameBox" name="nameBox" placeholder={t('title')}></textarea><br></br>
                                <button type="button" id="previewButton" onClick={searchByName}>{t('search')}</button>
                            </div>
                        </fieldset>
                        <br></br>
                        <fieldset id="resultsField" className="resultBox" hidden>
                            <legend>Search Result</legend>
                            <h4 id="resultTitle">{t('title')}</h4>
                        </fieldset>
                        <fieldset id="editField" className="editBox" hidden>
                            <legend>{t('editSermon')}</legend>
                            <br></br><textarea rows="1" cols="60" id="editNameBox" name="editNameBox" placeholder={t('title')}></textarea>
                            <br></br><textarea rows="1" cols="60" id="editYoutubeLinkBox" name="editYoutubeLinkBox" placeholder={t('youtubeLink')}></textarea>
                            <br></br><input type="datetime-local" id="editSermonDateTime" name="editSermonDateTime"/>
                            <br></br><textarea rows="4" cols="60" id="editDescriptionBox" name="editDescriptionBox" placeholder={t('description')}></textarea>
                            <br></br><iframe id="editYoutubeEmbed" width="420" height="315" src=""></iframe>
                            <br></br><button type="button" onClick={createPreview}>{t('preview')}</button>
                            <button type="button" onClick={deleteSermonConfirmation}>{t('delete')}</button>
                        </fieldset>
                        <fieldset id="previewField" className="previewBox" hidden>
                            <legend>{t('preview')}</legend>
                            <h4 id="resultTitle">{t('title')}</h4>
                            <p id="resultDescription">{t('description')}</p>
                            <p id="resultDateTime">{t('sermonDate')}</p>
                            <iframe id="video" width="420" height="315" src=""> </iframe>
                            <br></br><button type="button" onClick={editSermon}  style={{width:"120px"}}>{t('editSermon')}</button>
                        </fieldset> 
                    </div>
                }
            </main>
        </>
    );
}

// Export the component as the default export
export default AdminSermonEdit;
