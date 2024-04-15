import React, {useEffect} from 'react';
import {useAuth0 } from '@auth0/auth0-react';  // OAuth
import {searchByName, createPreview, editAnnouncement, deleteAnnouncementConfirmation} from './Javascript/announcementsEdit.js'
import {useTranslation} from 'react-i18next'
import './AdminStyles.css'

var accessToken;

// AdminAnnouncementEdit Component
function AdminAnnouncementsEdit() {
    const { t } = useTranslation();
    return (
        <>
            <main className="announcements-main">
                {
                    <div className="mainContent" >
                        <fieldset id="searchField" className="containerStyle searchBox">
                            <legend>{t('searchAnnouncement')}</legend>
                            <div style={{ display: "flex", alignItems: "center" }}>
                                <textarea rows="1" id="nameBox" name="nameBox" placeholder={t('title')}></textarea><br></br>
                                <button type="button" id="previewButton" onClick={searchByName} style={{fontWeight:'bold'}}>{t('search')}</button>
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
                            <button type="button" onClick={() => deleteAnnouncementConfirmation(accessToken)} style={{fontWeight:'bold'}}>{t('delete')}</button>
                        </fieldset>
                        <fieldset id="previewField" className="previewBox" hidden>
                            <legend>{t('preview')}</legend>
                            <h4 id="resultTitle">{t('title')}</h4>
                            <p id="resultDescription">{t('description')}</p>
                            <br></br><button type="button" onClick={() => editAnnouncement(accessToken)} style={{ width: "120px" , fontWeight:'bold'}}>{t('editAnnouncement')}</button>
                        </fieldset>
                    </div>
                }
            </main>
        </>
    );
}

var App = () => {
    const {getAccessTokenWithPopup } = useAuth0(); //while it would be nice to use getAccessTokenSilently, we can't, as localhost is blocked from that call. Change when on prod?

    useEffect(() => {

        const getUserToken = async () => {
            const domain = process.env.REACT_APP_AUTH0_DOMAIN;

            try {
                accessToken = await getAccessTokenWithPopup({
                    authorizationParams: {
                        audience: `https://${domain}/api/v2/`,
                    },
                });

            } catch (e) {
                console.log(e.message);
            }
        };

        getUserToken();

    }, [getAccessTokenWithPopup]);



    return (
        <AdminAnnouncementsEdit />
    )

};


// Export the component as the default export
export default App;
export {AdminAnnouncementsEdit};
