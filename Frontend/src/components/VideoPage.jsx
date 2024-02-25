import React from 'react';
import { useParams } from 'react-router-dom';
import {useState, useEffect } from 'react';
import { getSermonById } from './Javascript/previousSerms';
// Define your functional component
function VideoPage({}){
    const { sermonId } = useParams();
    const [sermonData, setSermonData] = useState(null);

    useEffect(() => {
        const fetch = async () => {
            const data = await getSermonById(sermonId);
            setSermonData(data);
        };
        fetch();
    }, [sermonId]);

    if (!sermonData){
        return <div>loading</div>;
    }

    const { youtubeLink, title, description, dateTime } = sermonData;
    const formatDate = new Date(dateTime).toLocaleString();
    const youtubeEmbed = youtubeLink.replace("watch?v=", "embed/");
    
    return (
            <div>
               <iframe width="560" height ="315" src={youtubeEmbed} title="embedded sermon"></iframe>
               <h2>{title}</h2>
               <p>{description}</p>
               <p>Date and Time: {formatDate}</p>
            </div>
    );
}



export default VideoPage;

