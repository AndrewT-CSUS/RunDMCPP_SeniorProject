import React, { useState } from 'react';
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
        {
            src: 'https://static.wixstatic.com/media/0bf59f_59b71ef1de9b0888cba20efa73d4e027.png/v1/fill/w_552,h_384,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/0bf59f_59b71ef1de9b0888cba20efa73d4e027.png', 
            title: 'Photo Title' 
        },
        {
            src: 'https://cdn1.outreachmagazine.com/wp-content/uploads/2018/09/18_0919_LEADERSHIP_10-Questions-to-Ask-About-Your-Church-Staff-Meetings_OLDSize.jpg',
            title: 'Photo Title' 
        },
        {
            src: 'https://via.placeholder.com/500',
            title: 'Photo Title' 
        },
        {
            src: 'https://via.placeholder.com/500',
            title: 'Photo Title' 
        },
        {
            src: 'https://via.placeholder.com/500',
            title: 'Photo Title' 
        },
        {
            src: 'https://via.placeholder.com/500',
            title: 'Photo Title' 
        },
        {
            src: 'https://via.placeholder.com/500',
            title: 'Photo Title' 
        },
        ],
    },
    {
        id: 2,
        title: 'Album 2',
        photos: [
        { 
            src: 'https://via.placeholder.com/500', 
            title: 'Photo Title' 
        },
        { 
            src: 'https://via.placeholder.com/500', 
            title: 'Photo Title'  
        },
        ],
    },
    {
        id: 3,
        title: 'Album 3',
        photos: [
        { 
            src: 'https://via.placeholder.com/500', 
            title: 'Photo Title' 
        },
        { 
            src: 'https://via.placeholder.com/500', 
            title: 'Photo Title' 
        },
        ],
    },
];

// Component: Card component for each individual card. Has a photo & caption.
const StyledCard = styled(Card)({
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

// Component: TabPanel renders the content of the selected tab
function TabPanel(props) {
    // Destructuring props to get the children, value, and index
    const { children, value, index, ...other } = props;

    // Rendering the content of the selected tab
    return (
        <div
        role="tabpanel"
        hidden={value !== index} // Only display if this panel's index matches the selected tab
        id={`simple-tabpanel-${index}`}
        aria-labelledby={`simple-tab-${index}`}
        {...other}
        >
        {value === index && (
            // Box component for padding around the content
            <Box sx={{ p: 5 }}>
            {children} {/* Render the children passed to TabPanel, which are the album's photos*/}
            </Box>
        )}
        </div>
    );
}

// Component: Renders the photo gallery page
function PhotoGallery() {
    // State: Keeps track of the currently selected tab
    const [selectedTab, setSelectedTab] = useState(0); 

    // Function: Handles changing tabs
    const handleChange = (event, newValue) => {
        setSelectedTab(newValue);
    };

    // Rendering the photo gallery
    return (
        <Box sx={{ width: '100%' }}> {/* Tabs component to display a tab for each album */}
        <Tabs value={selectedTab} onChange={handleChange} variant="scrollable" scrollButtons="auto">
            {albums.map((album, index) => (
                <Tab label={album.title} key={album.id} /> // Creating a tab for each album with its title
            ))}
        </Tabs>
        {albums.map((album, index) => (
            <TabPanel value={selectedTab} index={index} key={album.id}> {/* TabPanel to display the photos of the selected album */}
            <Grid container spacing={3}> {/* Grid to display the photos in a responsive layout */}
                {album.photos.map((photo, index) => (
                <Grid item xs={6} sm={4} md={3}key={index}> 
                    <StyledCard>
                        <CardMedia component="img" image={photo.src} alt={photo.title} sx={{ objectFit: 'cover', width: '100%', height: 'auto', }}/>
                        <CardContent>
                            <Typography gutterBottom variant="h5" component="div" sx={{ height: '12px', display: 'flex', alignItems: 'center', textAlign: 'center', }}>
                            {photo.title}
                            </Typography>
                        </CardContent>
                    </StyledCard>
                </Grid>
                ))}
            </Grid>
            </TabPanel>
        ))}
        </Box>
    );
}



export default PhotoGallery;
