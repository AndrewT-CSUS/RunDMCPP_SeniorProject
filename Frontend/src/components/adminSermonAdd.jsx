import React, {useEffect} from "react";
import {useAuth0 } from '@auth0/auth0-react';  // OAuth
import {createPreview, addSermon} from './Javascript/sermonAdd.js'
import { useTranslation } from 'react-i18next';


var accessToken;

//Sermon add page component
function AdminSermonAdd() {
    const { t } = useTranslation();
    const auth0 = useAuth0();
    const isAuthenticated = auth0.isAuthenticated;

    //component to show sermon creation stuff
    function SermonCreationWindow(){
        return (
            <div className = "logged-in-container">
                        <fieldset className="addSermonBox">
                            <legend>Sermon Information</legend>
                            <br></br><textarea rows="1" cols="60" id="nameBox" name="nameBox" placeholder={t('title')}></textarea>
                            <br></br><textarea rows="1" cols="60" id="youtubeLinkBox" name="youtubeLinkBox" placeholder={t('youtubeLink')}></textarea>
                            <br></br> <input type="datetime-local" id="sermonDateTime" name="sermonDateTime"/>
                            <br></br><textarea rows="4" cols="60" id="descriptionBox" name="descriptionBox" placeholder={t('description')}></textarea>
                            <br></br><button type="button" id="previewButton" onClick={createPreview} style = {{width:"120px"}}>{t('preview')}</button>
                        </fieldset>
                <fieldset id="resultsField" className="previewBox" hidden>
                    <legend>{t('preview')}</legend>
                    <h4 id="resultTitle">{t('title')}</h4>
                    <p id="resultDescription">{t('description')}</p>
                    <p id="resultDateTime">{t('sermonDate')}</p>
                    <iframe id="video" width="420" height="315" src=""> </iframe>
                    <br></br><button type="button" onClick={() => addSermon(accessToken)}  style={{width:"120px"}}>{t('addSermon')}</button>
                </fieldset>
            </div>
        );
    }

    //render main page
    return(
        <div className= "sermonAdd">
            {isAuthenticated && (
                <SermonCreationWindow />
            )}
            {!isAuthenticated && (
                <h2>Go log in</h2>
            )}

        </div>
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
        <AdminSermonAdd />
    )

};


export default App;
export { AdminSermonAdd}
