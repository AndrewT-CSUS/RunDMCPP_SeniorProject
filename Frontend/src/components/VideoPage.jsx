import React from 'react';

// Define your functional component
function VideoPage({url, title, description, dateTime}){
    return (
            <div>
               <iframe width="560" height ="315" src={url} title="embedded sermon"></iframe>
               <h2>{title}</h2>
               <p>{description}</p>
               <p>Date and Time: {dateTime}</p>
            </div>
    );
}

export default VideoPage;

