import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Box, Grid, Card, CardMedia, CardContent, Typography, TextField, Button } from '@mui/material';
import { useAuth0 } from '@auth0/auth0-react'; // OAuth

// Require all image paths from the photoAlbums directory
const albumImages = require.context('./photoAlbums', true);
// Get an array of all image paths
const albumImagePaths = albumImages.keys();

// Function to get album title from local storage
const getAlbumTitleFromLocalStorage = (albumId) => {
  const storedAlbums = JSON.parse(localStorage.getItem('albums'));
  if (storedAlbums && storedAlbums.length > 0) {
    const album = storedAlbums.find((album) => album.id === albumId);
    return album ? album.title : `Album ${albumId}`;
  } else {
    return `Album ${albumId}`;
  }
};

// Map the image paths to album data
const albumsData = albumImagePaths.map((imagePath, index) => {
  // Check if the image path contains more than 3 segments (indicating nested directories)
  if (imagePath.split('/').length > 3) {
    return null;
  }
  // Match the album number from the image path
  const match = imagePath.match(/album(\d+)\.(jpg|jpeg|png|webp)/i);
  if (!match) {
    return null;
  }
  // Extract album number and image source
  const albumNumber = match[1];
  const imageSrc = albumImages(imagePath);
  // Return album object
  return {
    id: parseInt(albumNumber), // Use album number as ID
    title: getAlbumTitleFromLocalStorage(parseInt(albumNumber)),
    photos: [{ src: imageSrc, title: `Photo Title ${albumNumber}` }],
  };
}).filter((album) => album !== null);

// Sort albums based on their IDs
albumsData.sort((a, b) => a.id - b.id);

// PhotoGallery component
function PhotoGallery() {
  const { isAuthenticated } = useAuth0();
  const [albums, setAlbums] = useState(albumsData);
  const [editMode, setEditMode] = useState(null);

  // Update local storage whenever albums state changes
  useEffect(() => {
    localStorage.setItem('albums', JSON.stringify(albums));
  }, [albums]); // Depend on albums

  // Regenerate albums when image paths change
  useEffect(() => {
    const updatedAlbums = albums.map((album) => {
      const albumNumber = album.title.match(/Album (\d+)/i)?.[1];
      if (albumNumber) {
        const imagePath = albumImagePaths.find((path) => path.includes(`album${albumNumber}`));
        if (!imagePath) {
          return null; // Album not found in image paths, should be removed
        }
        const newTitle = `Album ${albumNumber}`;
        if (album.title !== newTitle) {
          // Update album title if it has changed
          return { ...album, title: newTitle };
        }
      }
      return album;
    }).filter((album) => album !== null);

    setAlbums(updatedAlbums);
  }, [albumImagePaths]); // Depend only on albumImagePaths for album regeneration

  // Load albums from local storage when component mounts
  useEffect(() => {
    const storedAlbums = JSON.parse(localStorage.getItem('albums'));
    if (storedAlbums && storedAlbums.length > 0) {
      setAlbums(storedAlbums);
    }
  }, []);

  // Function to handle entering edit mode for an album
  const handleEdit = (albumId) => {
    setEditMode(albumId);
  };

  // Function to handle saving changes to an album's title
  const handleSave = (albumId, newTitle) => {
    // Map albums and update the title of the album with matching ID
    const updatedAlbums = albums.map((album) => {
      if (album.id === albumId) {
        return { ...album, title: newTitle };
      }
      return album;
    });
    // Update state with the new title
    setAlbums(updatedAlbums);
    setEditMode(null); // Exit edit mode
    // Store updated albums in local storage
    localStorage.setItem('albums', JSON.stringify(updatedAlbums));
  };

  return (
    <Box sx={{ width: '100%', paddingLeft: '15px' }}>
      <Grid container spacing={3}>
        {albums.map((album) => (
          <Grid item xs={11} sm={6} md={4} lg={3.5} key={album.id}>
            <Card sx={{ height: 'clamp(150px, 80vw, 365px);', transition: 'transform 0.3s', '&:hover': { transform: 'scale(1.05)' } }}>
              <Link to={`/albums/${album.id}?title=${encodeURIComponent(album.title)}`} style={{ textDecoration: 'none', color: 'inherit' }}>
                <CardMedia component="img" image={album.photos[0].src} alt={album.title} sx={{ objectFit: 'cover', height: 300 }} />
              </Link>
              <CardContent sx={{ height: 100 }}>
                {editMode === album.id && isAuthenticated ? (
                  <Box>
                    <TextField
                      fullWidth
                      autoFocus
                      defaultValue={album.title}
                      onBlur={(e) => handleSave(album.id, e.target.value)}
                    />
                    <Button onClick={() => handleSave(album.id, album.title)}>Save</Button>
                  </Box>
                ) : (
                  <Typography
                    variant="h5"
                    component="div"
                    sx={{ cursor: 'pointer' }}
                    onClick={() => isAuthenticated && handleEdit(album.id)}
                  >
                    {album.title}
                  </Typography>
                )}
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
  return (
    <Box sx={{ width: '100%', paddingLeft: '15px' }}>
      <Grid container spacing={3}>
        {albums.map((album) => (
          <Grid item xs={6} sm={4} md={2.95} key={album.id}>
            <Card sx={{ height: '92%', transition: 'transform 0.3s', '&:hover': { transform: 'scale(1.05)' } }}>
              <Link to={`/albums/${album.id}?title=${encodeURIComponent(album.title)}`} style={{ textDecoration: 'none', color: 'inherit' }}>
                <CardMedia component="img" image={album.photos[0].src} alt={album.title} sx={{ objectFit: 'cover', height: 300 }} />
              </Link>
              <CardContent sx={{ height: 100 }}>
                {editMode === album.id && isAuthenticated ? (
                  <Box>
                    <TextField
                      fullWidth
                      autoFocus
                      defaultValue={album.title}
                      onBlur={(e) => handleSave(album.id, e.target.value)}
                    />
                    <Button onClick={() => handleSave(album.id, album.title)}>Save</Button>
                  </Box>
                ) : (
                  <Typography
                    variant="h5"
                    component="div"
                    sx={{ cursor: 'pointer' }}
                    onClick={() => isAuthenticated && handleEdit(album.id)}
                  >
                    {album.title}
                  </Typography>
                )}
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
}

export default PhotoGallery;
