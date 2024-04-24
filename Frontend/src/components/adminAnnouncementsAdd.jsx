import React, {useEffect} from 'react';
import {useAuth0 } from '@auth0/auth0-react';  // OAuth
import {createPreview, addAnnouncement} from './Javascript/announcementsAdd.js'
import './Javascript/i18n.js'
import {useTranslation} from 'react-i18next'
import './AdminStyles.css'

var accessToken;

function AdminAnnouncementsAdd() {
    const { t } = useTranslation();
    return (
        <>
            <meta charSet="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <title>ChurchTemplate</title>
            <link rel="stylesheet" href="header.css" />
            <main>
                {
                    <div className="announcementsBox">
                        <fieldset className="containerStyle addAnnounementBox">
                            <legend>{t('announcements')}</legend>
                            <br></br><textarea rows="1" cols="60" id="titleBox" name="titleBox" placeholder={t('title')}></textarea>
                            <br></br><textarea rows="4" cols="60" id="descriptionBox" name="descriptionBox" placeholder={t("description")}></textarea>
                            <br></br><button type="button" id="previewButton" onClick={createPreview} style = {{width:"120px", fontWeight:'bold'}}>{t("preview")}</button>
                        </fieldset>
                        <fieldset id="resultsField" className="previewBox" hidden>
                            <legend>{t("preview")}</legend>
                            <h4 id="resultTitle">{t("title")}</h4>
                            <p id="resultDescription">{t("description")}</p>
                            <br></br><button type="button" onClick={() => addAnnouncement(accessToken)}  style={{width:"120px", fontWeight:'bold'}}>{t("addAnnouncement")}</button>
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
        <AdminAnnouncementsAdd />
    )

};

// Export the component as the default export
export default App;
export {AdminAnnouncementsAdd};

