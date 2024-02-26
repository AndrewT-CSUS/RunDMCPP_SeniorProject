import React, { useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import { fetchRecentAnnouncements } from './Javascript/announcementsAdd.js';

const AnnouncementsCarouselComp = () => {
    const [index, setIndex] = useState(0);
    const [announcements, setAnnouncements] = useState([]);
    const { t } = useTranslation();

    useEffect(() => {
        const fetchAnnouncements = async () => {
            try {
                const data = await fetchRecentAnnouncements();
                setAnnouncements(data || []);
            } catch (error) {
                console.error('Error getting announcements:', error);
            }
        };
        fetchAnnouncements();
    }, []);

    const totalAnnouncements = announcements ? announcements.length : 0;

    const previousSlide = () => {
        const newIndex = (index - 1 + totalAnnouncements) % totalAnnouncements;
        setIndex(newIndex);
    };

    const nextAnnouncement = () => {
        const newIndex = (index + 1) % totalAnnouncements;
        setIndex(newIndex);
    };

    const currentAnnouncement = announcements[index] || {title: 'No Announcement', content: 'No content' };

    if(!announcements || totalAnnouncements === 0){
        return (
            <div className="AnnouncementsCarouselContainer" style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
                <p>No announcements</p>
            </div>
        );
    }

    return (
        <div className="AnnouncementsCarouselContainer" style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
            <div className="Announcements" style={{ textAlign: 'center' }}>
                <h2>{currentAnnouncement.title}</h2>
                <p>{currentAnnouncement.description}</p>
            </div>
            <div className="carousel-controls" style={{marginTop: '20px'}}>
                <button onClick={nextAnnouncement}>{t('next')}</button>
                <button onClick={previousSlide}>{t('previous')}</button>
            </div>
        </div>
    );
};

export default AnnouncementsCarouselComp;
