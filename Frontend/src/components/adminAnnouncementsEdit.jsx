import React from 'react';
import {searchByName, createPreview, editAnnouncement, deleteAnnouncementConfirmation} from './Javascript/announcementsEdit.js'
import {useTranslation} from 'react-i18next'

// AdminAnnouncementEdit Component
function AdminAnnouncementsEdit() {
    const { t } = useTranslation();

    return (
        <>
            <main>
                {/* Main content goes here */
                    <div className="mainContent" >
                        <fieldset id="searchField" className="searchBox">
                            <legend>{t('searchAnnouncement')}</legend>
                            <div style={{ display: "flex", alignItems: "center" }}>
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
                            <legend>{t('editAnnouncement')}</legend>
                            <br></br><textarea rows="1" cols="60" id="editNameBox" name="editNameBox" placeholder={t('title')}></textarea>
                            <br></br><textarea rows="4" cols="60" id="editDescriptionBox" name="editDescriptionBox" placeholder={t('description')}></textarea>
                            <br></br><button type="button" onClick={createPreview}>{t('preview')}</button>
                            <button type="button" onClick={deleteAnnouncementConfirmation}>{t('delete')}</button>
                        </fieldset>
                        <fieldset id="previewField" className="previewBox" hidden>
                            <legend>{t('preview')}</legend>
                            <h4 id="resultTitle">{t('title')}</h4>
                            <p id="resultDescription">{t('description')}</p>
                            <br></br><button type="button" onClick={editAnnouncement} style={{ width: "120px" }}>{t('editAnnouncement')}</button>
                        </fieldset>
                    </div>
                }
            </main>
        </>
    );
}

// Export the component as the default export
export default AdminAnnouncementsEdit;
