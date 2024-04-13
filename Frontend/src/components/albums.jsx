import React, { useState, useEffect } from 'react';
import { useParams, useLocation } from 'react-router-dom';
import { Card, CardMedia, CardContent, Typography, styled } from '@mui/material';

// Function to extract URL parameters
const useQuery = () => {
  return new URLSearchParams(useLocation().search);
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
    photos: collectAlbumImages(albumId), // Use a function to use unique photos on each album
  };
  setAlbum(albumData);
};

const collectAlbumImages = (id) => {
    // Function to get all images in the folder with the correct album ID
    const albumImageBasePath = require.context('./photoAlbums/', true );
    const albumImagePaths = albumImageBasePath.keys();

    const albumImages = albumImagePaths.map((imagePath) => {

        var splits = imagePath.split('/');

        // Check if the image path contains exactly 3 segments ('./', '<album number>', '<image name>' )
        if (splits.length != 3) {
            return null;
        }

        //Filter for images in album
        if(splits[1] != id){
            return null;
        }

        // Extract album number and image source
        const imageSrc = albumImageBasePath(imagePath);
        // Return album object
        return {
            src: imageSrc, title: splits[2]
        };
    }).filter(photo => photo !== null);

    return albumImages;
} 

// Functional component for displaying albums
const Albums = () => {
  const { albumId } = useParams(); // Retrieve albumId from the route parameters
  const query = useQuery(); // Retrieve URL query parameters
  const [album, setAlbum] = useState(null); // State to store the album data
  const albumTitle = query.get('title'); // Extract album title from query parameters

  // Fetch album data when the component mounts or when albumId changes
  useEffect(() => {
    fetchAlbum(albumId, setAlbum);
  }, [albumId]);

  // Rendering the photo gallery
  return (
    <div style={{ marginLeft: '20px' }}> {/* Adjust left margin to move the cards to the right */}
      <h2>{albumTitle}</h2>
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
