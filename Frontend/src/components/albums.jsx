import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom'; // Lets us grab stuff from the URL
import { Button, Card, CardMedia, CardContent, Typography, styled, Box } from '@mui/material'; // Some fancy UI components
import { useAdmin } from './Javascript/adminContext.js'; // Custom hook for admin stuff
import { uploadImage } from './Javascript/imageApiService.js'; // Function to call our server
import { useAuth0 } from '@auth0/auth0-react'; // For authentication magic

// Custom styling for our cards
const StyledCard = styled(Card)({
  width: '20%',
  aspectRatio: '1/1',
  margin: 'left',
  marginRight: '20px',
  marginBottom: '20px',
  boxShadow: '0 4px 8px 0 rgba(0,0,0,0.2)',
  transition: '0.3s',
  '&:hover': {
    transform: 'scale(1.05)',
    boxShadow: '0 14px 20px 0 rgba(0,0,0,0.2)',
  },
});

// The main component for displaying albums
const Albums = () => {
  const { albumId } = useParams(); // Get the album ID from the URL
  const [album, setAlbum] = useState({ photos: [] }); // State for our album
  const { isAdmin } = useAdmin(); // Check if user is an admin
  const { getAccessTokenWithPopup } = useAuth0(); // Auth0 magic
  const [accessToken, setAccessToken] = useState(''); // State for our access token

  // Effect to get user token when component mounts or getAccessTokenWithPopup changes
  useEffect(() => {
    const getUserToken = async () => {
        try {
            const token = await getAccessTokenWithPopup({
                authorizationParams: {
                    audience: `https://${process.env.REACT_APP_AUTH0_DOMAIN}/api/v2/`,
                },
            });
            setAccessToken(token); // Save the token
        } catch (e) {
            console.log('Error getting access token:', e.message);
        }
    };

    getUserToken();
  }, [getAccessTokenWithPopup]);

  // Effect to fetch photos when accessToken changes
  useEffect(() => {
    const fetchPhotos = async () => {
      if (!accessToken) {
        console.log('Access token is not available yet.');
        return;
      }

      try {
        const response = await fetch('http://localhost:8080/images', {
          headers: {
            'Authorization': `Bearer ${accessToken}`,
          },
        });
        if (!response.ok) throw new Error('Network response was not ok');
        const imageUrls = await response.json(); // Parse the JSON response

        // Update the album state with the new photos
        setAlbum(prevAlbum => ({
          ...prevAlbum,
          photos: imageUrls.map(url => ({ src: url, title: 'Photo from Cloudinary' })),
        }));
      } catch (error) {
        console.error('Error fetching photos:', error);
      }
    };

    fetchPhotos();
  }, [accessToken]);

  // Handles the file upload
  const handleUpload = async (event) => {
    const file = event.target.files[0]; // Get the file from the event
    if (!file) return; // If no file, do nothing

    if (!accessToken) {
      console.log('Access token is not available yet.');
      return;
    }

    try {
      const response = await uploadImage(file, accessToken); // Upload the image
      console.log('Upload successful:', response);
      const imageUrl = response.secure_url; // Get the URL of the uploaded image

      // Update the album state with the new photo
      setAlbum(prevAlbum => ({
          ...prevAlbum,
          photos: [
              ...prevAlbum.photos,
              { src: imageUrl, title: 'Newly Uploaded Photo' }
          ]
      }));
    } catch (error) {
      console.error('Upload failed:', error);
    }
  };

  // The render part where we show stuff
  return (
    <div>
      <h2>{album.title}</h2>
      {isAdmin && ( // If the user is an admin, show the upload button
        <Box mb={2}>
          <Button variant="contained" component="label">
            Upload Photo
            <input type="file" hidden onChange={handleUpload} aria-label="Upload Photo" />
          </Button>
        </Box>
      )}
      <div style={{ display: 'flex', flexWrap: 'wrap' }}>
        {album.photos.map((photo, index) => ( // Display all photos in the album
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

export default Albums; // Don't forget to export so we can use it elsewhere
