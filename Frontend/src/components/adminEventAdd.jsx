import React, {useEffect} from 'react';
import {useAuth0 } from '@auth0/auth0-react';  // OAuth
import {createPreview, addEvent} from './Javascript/eventAdd.js'
import {useTranslation} from 'react-i18next'
import './AdminStyles.css'

var accessToken;

// Define your functional component
function AdminEventAdd() {
    const { t } = useTranslation();
    return (
        <>
            <meta charSet="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <title>adminEventCreate</title>
            <main>
                {/* Main content goes here */}
                <div className="mainContent" >
            <fieldset className="containerStyle addEventBox">
                <legend>{t('events')}</legend>
                <br /><textarea rows="1" cols="60" id="nameBox" name="nameBox" placeholder={t('title')}></textarea>
                <br /> <input type="datetime-local" id="eventDateTime" name={t('eventDate')}/>
                <br /><textarea rows="2" cols="60" id="locationBox" name="locationBox" placeholder={t('eventLocation')}></textarea>
                <br /><textarea rows="4" cols="60" id="descriptionBox" name="descriptionBox" placeholder={t('description')}></textarea>
                <br /><button type="button" id="previewButton" onClick={createPreview} style = {{width: '120 px', fontWeight: 'bold'}}>{t('preview')}</button>
            </fieldset>

            <fieldset id="resultsField" className="previewBox" hidden>
                <legend>{t('events')} {t('preview')}</legend>
                <h4 id="resultTitle">{t('title')}</h4>
                <p id="resultLocation">{t('eventLocation')}</p>
                <p id="resultDescription">{t('description')}</p>
                <p id="resultDateTime">{t('eventDate')}</p>
                <br /><button type="button" onClick={() => addEvent(accessToken)} style = {{width: '120 px', fontWeight:'bold'}}>{t('addEvent')}</button>
            </fieldset>
        </div>
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
        <AdminEventAdd />
    )

};

// Export the component as the default export
export default App;
export {AdminEventAdd};

