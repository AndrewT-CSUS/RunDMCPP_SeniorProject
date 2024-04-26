import React, {useEffect} from 'react';
import {useAuth0 } from '@auth0/auth0-react';  // OAuth
import {searchByName, createPreview, editEvent, deleteEventConfirmation} from './Javascript/eventEdit.js'
import {useTranslation} from 'react-i18next'
import './AdminStyles.css'

var accessToken;

// AdminEventEdit Component
function AdminEventEdit() {
    const { t } = useTranslation();

    return (
        <>
            <main className="event-edit-main">
                {/* Main content goes here */
                    <div className="mainContent" >
                        <fieldset id="searchField" className="containerStyle searchBox">
                            <legend>{t('editEvent')}</legend>
                            <div>
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
                            <legend>{t('editEvent')}</legend>
                            <br></br><textarea rows="1" cols="60" id="editNameBox" name="editNameBox" placeholder={t('title')}></textarea>
                            <br></br> <input type="datetime-local" id="editEventDateTime" name={t('eventDate')}/>
                            <br></br><textarea rows="4" cols="60" id="editDescriptionBox" name="editDescriptionBox" placeholder={t('description')}></textarea>
                            <br></br><textarea rows="4" cols="60" id="editLocationBox" name="editLocationBox" placeholder={t('eventLocation')}></textarea>
                            <br></br><button type="button" onClick={createPreview}>{t('preview')}</button>
                            <button type="button" onClick={() => deleteEventConfirmation(accessToken)} style={{fontWeight:'bold'}}>{t('delete')}</button>
                        </fieldset>
                        <fieldset id="previewField" className="previewBox" hidden>
                            <legend>{t('preview')}</legend>
                            <h4 id="resultTitle">{t('title')}</h4>
                            <p id="resultDescription">{t('description')}</p>
                            <p id="resultDateTime">{t('eventDate')}</p>
                            <p id="resultLocation">{t('eventLocation')}</p>
                            <br></br><button type="button" onClick={() => editEvent(accessToken)}>{t('editEvent')}</button>
                        </fieldset> 
                    </div>
                }
            </main>
        </>
    );
}

var App = () => {
    const {getAccessTokenSilently } = useAuth0(); //while it would be nice to use getAccessTokenSilently, we can't, as localhost is blocked from that call. Change when on prod?

    useEffect(() => {

        const getUserToken = async () => {
            const domain = process.env.REACT_APP_AUTH0_DOMAIN;

            try {
                accessToken = await getAccessTokenSilently({
                    authorizationParams: {
                        audience: `https://${domain}/api/v2/`,
                    },
                });

            } catch (e) {
                console.log(e.message);
            }
        };

        getUserToken();

    }, [getAccessTokenSilently]);



    return (
        <AdminEventEdit />
    )

};


// Export the component as the default export
export default App;
export {AdminEventEdit};
