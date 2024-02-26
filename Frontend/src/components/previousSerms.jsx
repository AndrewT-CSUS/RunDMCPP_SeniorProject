import React from 'react';
import {searchByName, searchByDate} from './Javascript/previousSerms.js'
import { useTranslation } from 'react-i18next';

// Define your functional component
function PreviousSerms() {
    const { t } = useTranslation();

    return (
        <>
            <main>
                <fieldset id="searchField" className="searchBox" >
                    <legend>{t('sermonSearch')}</legend>
                    {/* Name Search */}
                    <h3>{t('searchByName')}</h3>
                    <div style={{display: "flex", alignItems: "center"}}>
                        <textarea rows="1" id="searchBox" name="searchBox" placeholder={t('sermonName')}></textarea><br></br>
                    </div>
                    <div><br /> </div>
                    <div style={{display: "flex", flexWrap: "wrap",  alignItems: "left", justifyContent: "left"}}>
                        <button type="button" id="searchByNameButton" onClick={searchByName}>{t('searchByName')}</button>
                    </div>
                    <div><br /> </div>
                    {/* Date Search */}
                    <h3>{t('searchByDate')}</h3>
                    <div>
                        <p style={{margin: "0"}}>Start Date</p>
                        <input type="date" id="startDateBox" name="startDateBox"/>
                    </div>
                    <div>
                        <br/>
                        <p style={{margin: "0"}}>End Date</p>
                        <input type="date" id="endDateBox" name="endDateBox"/>
                    </div>
                    <div><br /> </div>
                    <div style={{display: "flex", flexWrap: "wrap",  alignItems: "left", justifyContent: "left"}}>
                        <button type="button" id="searchByDateButton" onClick={searchByDate} style={{ align: "left"}}>{t('searchByDate')}</button>
                    </div>
                </fieldset>
                <br></br>
                <fieldset id="resultsField" className="resultBox" hidden>
                    <legend id="resultsLegend">Search Result</legend>
                    <h4 id="resultTitle">{t('title')}</h4>
                </fieldset>
            </main>
        </>
    );
}

export default PreviousSerms;

