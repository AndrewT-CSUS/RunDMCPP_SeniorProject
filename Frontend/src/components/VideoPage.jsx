import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getSermonById } from './Javascript/sermons';
import './VideoPage.css';

function VideoPage() {
    const { sermonId } = useParams();
    const [sermonData, setSermonData] = useState(null);

    useEffect(() => {
        const fetchSermonData = async () => {
            const data = await getSermonById(sermonId);
            setSermonData(data);
        };
        fetchSermonData();
    }, [sermonId]);

    if (!sermonData) {
        return <div className="loading-message">Loading...</div>;
    }

    const { youtubeLink, title, description, dateTime } = sermonData;
    const formatDate = new Date(dateTime).toLocaleString();
    const youtubeEmbed = youtubeLink.replace("watch?v=", "embed/");

    return (
        <div className="main-container">
            <div className="video-container">
                <iframe className="video-frame" src={youtubeEmbed} title="Embedded Sermon"></iframe>
                <div className="video-content">
                    <h2>{title}</h2>
                    <p>{description}</p>
                    <p className="date-time">Date and Time: {formatDate}</p>
                </div>
            </div>
        </div>
    );
}

export default VideoPage;
