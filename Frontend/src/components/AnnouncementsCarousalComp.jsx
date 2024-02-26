import React, { useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import { fetchRecentAnnouncements } from './Javascript/announcementsAdd.js';
import './AnnouncementsCarousalComp.css';

const AnnouncementsCarouselComp = () => {
    const [index, setIndex] = useState(0);
    const [announcements, setAnnouncements] = useState([]);
    const { t } = useTranslation();    

    const fetchAnnouncements = async () => {
        try {
            const data = await fetchRecentAnnouncements();
            setAnnouncements(data || []);
        } catch (error) {
            console.error('Error getting announcements:', error);
        }
    };

    fetchAnnouncements();

    useEffect(() => {
        const interval = setInterval(() => {
            nextSlide();
        }, 5000); // Change slide every 5 seconds

        return () => clearInterval(interval);
    }, [index]); // Restart interval when index changes

    const totalAnnouncements = announcements ? announcements.length : 0;
    
    const previousSlide = () => {
        setIndex((prevIndex) => (prevIndex === 0 ? announcements.length - 1 : prevIndex - 1));
    };

    const nextSlide = () => {
        setIndex((prevIndex) => (prevIndex === announcements.length - 1 ? 0 : prevIndex + 1));
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
        <div className="carouselContainer">
            <div className="announcementCarousal" style={{ transform: `translateX(-${index * 105}%)` }}>
                {announcements.map((announcement, idx) => (
                    <div key={idx} className="announcement">
                        <div className="announcementContent">
                            <h2>{announcement.title}</h2>
                            <p>{announcement.description}</p>
                        </div>
                    </div>
                ))}
            </div>
            <div className="carousalControls">
                <button onClick={nextSlide}>Next</button>
            </div>
            <div style={{display:'flex', justifyContent:'flex-start'}} className="carousalControls">
                <button onClick={previousSlide}>Previous</button>
            </div>
        </div>
    );
};

export default AnnouncementsCarouselComp;

