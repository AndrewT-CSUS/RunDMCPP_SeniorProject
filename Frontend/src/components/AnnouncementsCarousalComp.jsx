import React, { useState } from 'react';

const AnnouncementsCarouselComp = () => {
    const [index, setIndex] = useState(0);
    const length = 3;

    const Announcements = [
        { title: 'Church Announcement 1', content: 'Come do the first thing with us!' },
        { title: 'Church Announcement 2', content: 'Come do the second thing with us!' },
        { title: 'Church Announcement 3', content: 'Come do the third thing with us!' }
    ];

    const previousSlide = () => {
        const newIndex = index - 1;
        setIndex(newIndex < 0 ? length - 1 : newIndex);
    };

    const nextAnnouncement = () => {
        const newIndex = index + 1;
        setIndex(newIndex >= length ? 0 : newIndex);
    };

    return (
        <div className="AnnouncementsCarouselContainer" style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
            <div className="Announcements" style={{ textAlign: 'center' }}>
                <h2>{Announcements[index].title}</h2>
                <p>{Announcements[index].content}</p>
            </div>
            <div className="carousel-controls" style={{marginTop: '20px'}}>
                <button onClick={nextAnnouncement}>Next</button>
                <button onClick={previousSlide}>Previous</button>
            </div>
        </div>
    );
};

export default AnnouncementsCarouselComp;
