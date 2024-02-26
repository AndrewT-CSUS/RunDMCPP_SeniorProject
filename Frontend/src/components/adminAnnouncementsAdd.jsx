import React from 'react';

import {createPreview, addAnnouncement} from './Javascript/announcementsAdd.js'
import './Javascript/i18n.js'
import {useTranslation} from 'react-i18next'

function AdminAnnouncementsAdd() {
    const { t } = useTranslation();
    return (
        <>
            <meta charSet="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <title>ChurchTemplate</title>
            <link rel="stylesheet" href="header.css" />
            <main>
                {/* Main content goes here */
                    <div>
                        <fieldset className="addAnnounementBox">
                            <legend>{t('announcements')}</legend>
                            <br></br><textarea rows="1" cols="60" id="titleBox" name="titleBox" placeholder={t('title')}></textarea>
                            <br></br><textarea rows="4" cols="60" id="descriptionBox" name="descriptionBox" placeholder={t("description")}></textarea>
                            <br></br><button type="button" id="previewButton" onClick={createPreview} style = {{width:"120px"}}>{t("preview")}</button>
                        </fieldset>
                        <fieldset id="resultsField" className="previewBox" hidden>
                            <legend>{t("preview")}</legend>
                            <h4 id="resultTitle">{t("title")}</h4>
                            <p id="resultDescription">{t("description")}</p>
                            <br></br><button type="button" onClick={addAnnouncement}  style={{width:"120px"}}>{t("addAnnouncement")}</button>
                        </fieldset> 
                    </div>
                }
            </main>
        </>
    );
}

export default AdminAnnouncementsAdd;