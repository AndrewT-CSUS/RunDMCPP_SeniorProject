import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Box, Grid, Card, CardMedia, CardContent, Typography, TextField, Button } from '@mui/material';
import { useAuth0 } from '@auth0/auth0-react';   // OAuth

// Require all image paths from the photoAlbums directory
const albumImages = require.context('./photoAlbums', true);
// Get an array of all image paths
const albumImagePaths = albumImages.keys();

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
        id: index + 1,
        title: `Album ${albumNumber}`,
        photos: [{ src: imageSrc, title: `Photo Title ${albumNumber}` }]
    };
}).filter(album => album !== null);

function PhotoGallery() {
 /* const { isAuthenticated } = useAuth0();
    const [albums, setAlbums] = useState(albumsData);
    const [editMode, setEditMode] = useState(null); 
 */      // Use this instead of the 3 const below to retrieve the album if deleted, or to retrieve a newly created one.

    const { isAuthenticated } = useAuth0();
    const [albums, setAlbums] = useState(() => {
        // Retrieve albums from local storage
        const storedAlbums = JSON.parse(localStorage.getItem('albums')) || [];

        // Use albumsData only if no albums are present in local storage
        return storedAlbums.length === 0 ? albumsData : storedAlbums;
    });
    const [editMode, setEditMode] = useState(null);

    useEffect(() => {
        // Retrieve albums data from local storage on component mount
        const storedAlbums = JSON.parse(localStorage.getItem('albums')) || [];
        
        // Filter out albums that are already present
        const newAlbums = storedAlbums.filter(storedAlbum => !albums.find(album => album.id === storedAlbum.id));
        
        if (newAlbums.length > 0) {
            // Merge new albums with existing albums
            const updatedAlbums = [...albums, ...newAlbums];
            
            // Update state with merged albums
            setAlbums(updatedAlbums);
            
            // Store updated albums in local storage
            localStorage.setItem('albums', JSON.stringify(updatedAlbums));
        } else {
            // If no new albums, update local storage with the current state
            localStorage.setItem('albums', JSON.stringify(albums));
        }
    }, [albums]);
    
    // Function to handle entering edit mode for an album
    const handleEdit = (albumId) => {
        setEditMode(albumId);
    };

    // Function to handle saving changes to an album's title
    const handleSave = (albumId, newTitle) => {
        // Map albums and update the title of the album with matching ID
        const updatedAlbums = albums.map(album => {
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

    // Function to handle deleting an album
    const handleDelete = (albumId) => {
        // Filter out the album with matching ID
        const updatedAlbums = albums.filter(album => album.id !== albumId);
        // Update state to remove the deleted album
        setAlbums(updatedAlbums);
        // Store updated albums in local storage
        localStorage.setItem('albums', JSON.stringify(updatedAlbums));
    };

    return (
        <Box sx={{ width: '100%', paddingLeft: '15px' }}>
            <Grid container spacing={3}>
                {albums.map(album => (
                <Grid item xs={6} sm={4} md={2.95} key={album.id}>
                    <Card sx={{ height: '92%', transition: 'transform 0.3s', '&:hover': { transform: 'scale(1.05)' } }}>
                        <Link to={`/albums/${album.id}?title=${encodeURIComponent(album.title)}`} style={{ textDecoration: 'none', color: 'inherit' }}>
                            <CardMedia component="img" image={album.photos[0].src} alt={album.title} sx={{ objectFit: 'cover', height: 300 }}/>
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
                            {isAuthenticated && (
                                <Button onClick={() => handleDelete(album.id)}>Delete</Button>
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