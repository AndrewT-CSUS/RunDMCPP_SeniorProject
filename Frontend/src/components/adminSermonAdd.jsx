import React from 'react';
import {createPreview, addSermon} from './Javascript/sermonAdd.js'
import { useTranslation } from 'react-i18next';

// AdminSermonAdd Component
function AdminSermonAdd() {
    const { t } = useTranslation();
    return (
        <>
            <main>
                {/* Main content goes here */
                    <div>
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
                            <br></br><button type="button" onClick={addSermon}  style={{width:"120px"}}>{t('addSermon')}</button>
                        </fieldset> 
                    </div>
                }
            </main>
        </>
    );
}

// Export the component as the default export
export default AdminSermonAdd;
