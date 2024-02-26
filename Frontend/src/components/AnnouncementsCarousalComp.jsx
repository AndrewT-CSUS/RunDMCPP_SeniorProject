import React, { useState, useEffect } from 'react';
import './AnnouncementsCarousalComp.css';

const AnnouncementsCarousel = () => {
    const [index, setIndex] = useState(0);
    const announcements = [
        { title: 'First Announcement 📢', content: 'Do this first thing with us!' },
        { title: 'Second Announcement 📢', content: 'Also this second thing!' },
        { title: 'Third Announcement 📢', content: 'Finally, this third thing you should do with us!' }
    ];

    const previousSlide = () => {
        setIndex((prevIndex) => (prevIndex === 0 ? announcements.length - 1 : prevIndex - 1));
    };

    const nextSlide = () => {
        setIndex((prevIndex) => (prevIndex === announcements.length - 1 ? 0 : prevIndex + 1));
    };

    useEffect(() => {
        const interval = setInterval(() => {
            nextSlide();
        }, 5000); // Change slide every 5 seconds

        return () => clearInterval(interval);
    }, [index]); // Restart interval when index changes

    return (
        <div className="carouselContainer">
            <div className="announcementCarousal" style={{ transform: `translateX(-${index * 105}%)` }}>
                {announcements.map((announcement, idx) => (
                    <div key={idx} className="announcement">
                        <div className="announcementContent">
                            <h2>{announcement.title}</h2>
                            <p>{announcement.content}</p>
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

export default AnnouncementsCarousel;

