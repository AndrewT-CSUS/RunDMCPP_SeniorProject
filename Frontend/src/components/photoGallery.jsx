import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Tabs, Tab, Box, Grid, Card, CardMedia, CardContent, Typography, styled } from '@mui/material';

// todo Sample data for albums, each with an ID, title, and array of photos
const albums = [
    {
        id: 1,
        title: 'Album 1',
        photos: [
            { 
                src: 'https://www.churchinteriors.com/wp-content/uploads/2020/01/Featured-Wilkesboro-Baptist-Church-Remodel.jpg', 
                title: 'Photo Title' 
            },
        ],
    },
    {
        id: 2,
        title: 'Album 2',
        photos: [
            { 
                src: 'https://static.wixstatic.com/media/0bf59f_59b71ef1de9b0888cba20efa73d4e027.png/v1/fill/w_552,h_384,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/0bf59f_59b71ef1de9b0888cba20efa73d4e027.png', 
                title: 'Photo Title' 
            },
        ],
    },
    {
        id: 3,
        title: 'Album 3',
        photos: [
            { 
                src: 'https://cdn1.outreachmagazine.com/wp-content/uploads/2018/09/18_0919_LEADERSHIP_10-Questions-to-Ask-About-Your-Church-Staff-Meetings_OLDSize.jpg', 
                title: 'Photo Title' 
            },
        ],
    },
];

// Component: Card component for each individual card. Has a photo & caption.
const StyledCard = styled(Card)( {
    width: '100%',
    aspectRatio: '1/1', // Square aspect ratio
    margin: 'auto', // Centers the card
    boxShadow: '0 4px 8px 0 rgba(0,0,0,0.2)', 
    transition: '0.3s', // Smooth transition for hover effects
    '&:hover': {
        transform: 'scale(1.05)', // Slightly larger size on hover
        boxShadow: '0 14px 20px 0 rgba(0,0,0,0.2)', // Enhanced shadow on hover
    },
});

// Component: Renders the photo gallery page
function PhotoGallery() {
    return (
        <Box sx={{ width: '100%' }}> 
            <Grid container spacing={3}>
                {albums?.map((photo, index) => (
                <Grid item xs={6} sm={4} md={3} key={index}> {/* Grid to display the photos in a responsive layout */}
                    <StyledCard>
                        {/* Link to navigate to the individual album page */}
                        <Link to={`/albums/${photo.id}`} style={{ textDecoration: 'none', color: 'inherit' }}>
                            {/* Display the first photo as the album cover */}
                            <CardMedia component="img" image={photo.photos[0].src} alt={photo.title} sx={{ objectFit: 'cover', width: '100%', height: 'auto' }}/>
                            <CardContent>
                                <Typography gutterBottom variant="h5" component="div" sx={{ height: '12px', display: 'flex', alignItems: 'center', textAlign: 'center' }}>
                                {photo.title}
                                </Typography>
                            </CardContent>
                        </Link>
                    </StyledCard>
                </Grid>
                ))}
            </Grid>
        </Box>
    );
}

export default PhotoGallery;
