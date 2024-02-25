import React from 'react';
import {createPreview, addEvent} from './Javascript/eventAdd.js'
import {useTranslation} from 'react-i18next'

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
            <fieldset className="addEventBox">
                <legend>{t('events')}</legend>
                <br /><textarea rows="1" cols="60" id="nameBox" name="nameBox" placeholder={t('title')}></textarea>
                <br /> <input type="datetime-local" id="eventDateTime" name={t('eventDate')}/> 
                <br /><textarea rows="2" cols="60" id="locationBox" name="locationBox" placeholder={t('eventLocation')}></textarea>
                <br /><textarea rows="4" cols="60" id="descriptionBox" name="descriptionBox" placeholder={t('description')}></textarea>
                <br /><button type="button" id="previewButton" onClick={createPreview} style = {{width: '120 px'}}>{t('preview')}</button>
            </fieldset>

            <fieldset id="resultsField" className="previewBox" hidden>
                <legend>{t('events')} {t('preview')}</legend>
                <h4 id="resultTitle">{t('title')}</h4>
                <p id="resultLocation">{t('eventLocation')}</p>
                <p id="resultDescription">{t('description')}</p>
                <p id="resultDateTime">{t('eventDate')}</p>
                <br /><button type="button" onClick={addEvent} style = {{width: '120 px'}}>{t('addEvent')}</button>
            </fieldset>
        </div>
            </main>
        </>
    );
}

// Export the component as the default export
export default AdminEventAdd;
