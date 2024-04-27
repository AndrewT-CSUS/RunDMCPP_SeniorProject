import React, { useEffect, useState } from 'react';
import { searchByName, searchByDate, showDefaults } from './Javascript/sermons.js';
import { useTranslation } from 'react-i18next';
import './sermons.css'; 

const Sermons = () => {
    const { t } = useTranslation();
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {       // Should only showDefaults once on loading the page, but it doesn't seem to. Fix in future sprint
        showDefaults();
    }, []);

    const handleSearchByName = () => {
        searchByName(searchTerm);
    };

    const handleSearchByDate = () => {
        searchByDate(startDate, endDate);
    };

    const handleShowDefaults = () => {
        showDefaults();
    };

    return (
        <main className="main-sermons-container">
            <fieldset id="searchField" className="searchBox">
                <legend>{t('sermonSearch')}</legend>
                {/* Name Search */}
                <div className="search-section">
                    <h3>{t('searchByName')}</h3>
                    <div className="search-input">
                        <textarea
                            rows="1"
                            id="searchBox"
                            name="searchBox"
                            placeholder={t('sermonName')}
                            onChange={(e) => setSearchTerm(e.target.value)}
                        ></textarea>
                    </div>
                    <button type="button" id="searchByNameButton" onClick={handleSearchByName}>
                        {t('searchByName')}
                    </button>
                </div>

                {/* Date Search */}
                <div className="search-section">
                    <h3>{t('searchByDate')}</h3>
                    <div className="date-input">
                        <p>{t('startDate')}</p>
                        <input
                            type="date"
                            id="startDateBox"
                            name="startDateBox"
                            onChange={(e) => setStartDate(e.target.value)}
                        />

                    </div>
                    <div className="date-input">
                        <p>{t('endDate')}</p>
                        <input type="date" id="endDateBox" name="endDateBox" onChange={(e) => setEndDate(e.target.value)} />
                    </div>
                    <button type="button" id="searchByDateButton" onClick={handleSearchByDate}>
                        {t('searchByDate')}
                    </button>
                    <button type="button" id="showDefaultsButton" onClick={handleShowDefaults}>
                        {t('clear')}
                    </button>
                </div>
            </fieldset>
            <br />
            <fieldset id="resultsField" className="resultBox" hidden>
                <legend id="resultsLegend">{t('results')}</legend>
                <h4 id="resultTitle">{t('title')}</h4>
            </fieldset>
        </main>
    );
};

export default Sermons;
