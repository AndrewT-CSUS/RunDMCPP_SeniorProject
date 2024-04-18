import React, { useState, useEffect } from 'react';
import { useParams, useLocation } from 'react-router-dom';
import { Card, CardMedia, CardContent, Typography, styled } from '@mui/material';

// Just guessing on how to fetch photos. You can ignore this
const fetchAlbumData = async (albumId) => {
  const response = await fetch(`https://sacglorychurch.org:8080/api/albums/${albumId}`);
  const data = await response.json();
  return data;
};

// Function to extract URL parameters
const useQuery = () => {
  return new URLSearchParams(useLocation().search);
};

// CSS styles for the close button
const closeButtonStyle = {
  position: 'absolute', // Fix button position relative to its container
  top: '2%', // Position button 2% from the top of its container
  right: '2%', // Position button 2% from the right of its container
  transform: 'translate(50%, -50%)', // Center button precisely
  backgroundColor: 'rgba(0, 0, 0, 0.5)', // Semi-transparent black background
  color: '#fff', // White color for text
  border: 'none', // No border
  fontSize: '24px', // Font size of the button text
  padding: '5px 10px', // Padding around the button text
  borderRadius: '50%', // Rounded shape for the button
  cursor: 'pointer', // Cursor style on hover
};

// CSS styles for the overlay
const overlayStyle = {
  position: 'fixed', // Fix overlay position relative to the viewport
  top: 0, // Position overlay at the top of the viewport
  left: 0, // Position overlay at the left of the viewport
  width: '100%', // Ensure overlay covers the entire width of the viewport
  height: '100%', // Ensure overlay covers the entire height of the viewport
  backgroundColor: 'rgba(0, 0, 0, 0.8)', // Semi-transparent black background
  zIndex: 9999, // Ensure overlay appears above other content
  display: 'flex', // Use flexbox for centering content
  justifyContent: 'center', // Center content horizontally
  alignItems: 'center', // Center content vertically
};

// Component: Card component for each individual card. Has a photo & caption.
const StyledCard = styled(Card)( {
  width: '20%', // Set card width to 20% of its containing element (container width)
  aspectRatio: '1/1', // Square aspect ratio
  margin: 'left', // Aligns the cards to the left (NOTE: 'left' is not a valid CSS margin value, consider using 'marginLeft')
  marginRight: '20px', // Adjust the right margin for offset
  marginBottom: '20px', // Adjust the bottom margin for spacing
  boxShadow: '0 4px 8px 0 rgba(0,0,0,0.2)',
  transition: '0.3s', // Smooth transition for hover effects
  cursor: 'pointer', // Add this line to change cursor to pointer
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

  // Function to display photo in fullscreen when clicked
  const handleFullScreen = (src, alt) => {
    // Create overlay element
    const overlay = document.createElement('div');
    Object.assign(overlay.style, overlayStyle); // Apply overlay styles

    // Create container for image and close button
    const container = document.createElement('div');
    container.style.position = 'relative'; // Position the container relative to the overlay

    // Create image element
    const zoomedImage = new Image();
    zoomedImage.src = src;
    zoomedImage.alt = alt;

    // Set up event listener for when the image loads
    zoomedImage.onload = () => {
      // Create close button
      const closeButton = document.createElement('button');
      closeButton.textContent = 'X';
      Object.assign(closeButton.style, closeButtonStyle); // Apply close button styles
      closeButton.addEventListener('click', () => {
        document.body.removeChild(overlay);
      });

      // Set image dimensions to fill the entire screen
      zoomedImage.style.width = '80vw';
      zoomedImage.style.height = '80vh';
      zoomedImage.style.objectFit = 'contain'; // Keep image aspect ratio

      // Add close button and image to container
      container.appendChild(closeButton);
      container.appendChild(zoomedImage);

      // Add container to overlay
      overlay.appendChild(container);

      // Add overlay to body
      document.body.appendChild(overlay);
    };

    // Add event listener to remove overlay when clicked outside the image
    overlay.addEventListener('click', (event) => {
      if (event.target === overlay) {
        document.body.removeChild(overlay);
      }
    });

    // Fetch image
    zoomedImage.src = src;
  };

  // Rendering the photo gallery
  return (
    <div style={{ marginLeft: '20px' }}>
      <h2>{albumTitle}</h2>
      <div style={{ display: 'flex', flexWrap: 'wrap' }}> {/* Adjust left margin to move the cards to the right */}
        {album?.photos.map((photo, index) => (
          <StyledCard key={index} onClick={() => handleFullScreen(photo.src, photo.title)}>
            <CardMedia
              component="img"
              image={photo.src}
              alt={photo.title}
              sx={{ objectFit: 'cover', width: '100%', height: '100%', aspectRatio: '1/1' }}
            />
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