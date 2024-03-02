import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Card, CardMedia, CardContent, Typography, styled } from '@mui/material';

// Just guessing on how to fetch photos. You can ignore this
const fetchAlbumData = async (albumId) => {
  const response = await fetch(`http://localhost:8080/api/albums/${albumId}`);
  const data = await response.json();
  return data;
};

// Component: Card component for each individual card. Has a photo & caption.
const StyledCard = styled(Card)( {
  width: '20%',
  aspectRatio: '1/1', // Square aspect ratio
  margin: 'left', // Aligns the cards to the left
  marginRight: '20px', // Adjust the right margin for offset
  marginBottom: '20px', // Adjust the bottom margin for spacing
  boxShadow: '0 4px 8px 0 rgba(0,0,0,0.2)',
  transition: '0.3s', // Smooth transition for hover effects
  '&:hover': {
    transform: 'scale(1.05)', // Slightly larger size on hover
    boxShadow: '0 14px 20px 0 rgba(0,0,0,0.2)', // Enhanced shadow on hover
  },
});

// Function to fetch album data
const fetchAlbum = async (albumId, setAlbum) => {
  const albumData = {
    title: `Album ${albumId}`,
    photos: albumPhotos(albumId), // Use a function to use unique photos on each album
  };
  setAlbum(albumData);
};

// Function to specify unique photo URLs for each album
const albumPhotos = (id) => {
  const albumPhotos = {
    '1': [
      { src: 'https://www.holyspiritspeaks.org/wp-content/uploads/2018/04/The-Sermon-on-the-Mount.jpg', title: 'Photo Title 1' },
      { src: 'https://knowhy.bookofmormoncentral.org/sites/default/files/knowhy-img/sermon.jpg', title: 'Photo Title 2' },
      // ... add more photos as needed for Album 1
    ],
    '2': [
      { src: 'https://knowhy.bookofmormoncentral.org/sites/default/files/knowhy-img/2016/10/extra/sermon/sermon-mount-400.jpg', title: 'Photo Title 1' },
      { src: 'https://central-baptist-church.org.uk/wp-content/uploads/2017/08/xIMG_7022-2000x1200.jpg.pagespeed.ic.wRJSiQsGB4.webp', title: 'Photo Title 2' },
      // ... add more photos as needed for Album 2
    ],
    '3': [
      { src: 'https://getordained.org/assets/getordained/images/thumbs/sermon-preach-871fd0.jpg', title: 'Photo Title 1' },
      { src: 'https://redeeminggod.com/wp-content/uploads/2011/09/preaching.jpg', title: 'Photo Title 2' },
      // ... add more photos as needed for Album 3
    ]
    // ... add more albums if needed
  };

  return albumPhotos[id]; // Return the specified photos for the given album ID
};

// Functional component for displaying albums
const Albums = () => {
  const { albumId } = useParams(); // Retrieve albumId from the route parameters
  const [album, setAlbum] = useState(null); // State to store the album data

  // Fetch album data when the component mounts or when albumId changes
  useEffect(() => {
    fetchAlbum(albumId, setAlbum);
  }, [albumId]);

  // Rendering the photo gallery
  return (
    <div>
      <h2>{album?.title}</h2>
      <div style={{ display: 'flex', flexWrap: 'wrap' }}>
        {album?.photos.map((photo, index) => (
          <StyledCard key={index}>
            <CardMedia component="img" image={photo.src} alt={photo.title} sx={{ objectFit: 'cover', width: '100%', height: '100%', aspectRatio: '1/1' }}/>
            <CardContent>
              <Typography gutterBottom variant="h5" component="div" sx={{ height: '12px', display: 'flex', alignItems: 'center', textAlign: 'center' }}>
                {photo.title}
              </Typography>
            </CardContent>
          </StyledCard>
        ))}
      </div>
    </div>
  );
};

export default Albums;
