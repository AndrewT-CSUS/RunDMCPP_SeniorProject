import React, {useContext} from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Auth0Provider } from '@auth0/auth0-react';
import { AdminProvider } from './components/Javascript/adminContext';
import { ThemeProvider, createTheme } from '@mui/material/styles'; 

// Components: Importing all the different pages
import Schedule from './components/schedule';
import AdminAnnouncementsAdd from './components/adminAnnouncementsAdd';
import AdminAnnouncementsEdit from './components/adminAnnouncementsEdit';
import AdminEventAdd from './components/adminEventAdd';
import AdminEventEdit from './components/adminEventEdit';
import AdminSermonAdd from './components/adminSermonAdd';
import AdminSermonEdit from './components/adminSermonEdit';
import Events from './components/events';
import AboutUs from './components/aboutUs';
import PastEvents from './components/pastEvents';
import PhotoGallery from './components/photoGallery';
import PreviousSerms from './components/previousSerms';
import Services from './components/services';
import UpcomingSerms from './components/upcomingSerms';
import LoginPage from './components/loginPage'; // Login Page for OAuth
import VideoPage from './components/VideoPage';

// Components: Importing Header and Footer for every page
import Header from './components/shared/header';
import Footer from './components/shared/footer'; 

// Photo Gallery: Default theme settings
const theme = createTheme({});

// App Component
function App() {
  return (

    /* Auth0Provider: Wraps around app to allow login */
    <Auth0Provider domain={process.env.REACT_APP_AUTH0_DOMAIN} clientId={process.env.REACT_APP_AUTH0_CLIENT_ID} redirectUri={window.location.origin}>
    <ThemeProvider theme={theme}> {/* ThemeProvider: Allows for custom themes for photo gallery */}
    <AdminProvider>               {/* AdminProvider: Wraps around app to allow admin access */}
    <Router>                      {/* Router: Allows linking to other pages*/}
      <Header />                  {/* Header: Navigation bar */}
      <Routes>                    {/* Routes: Links to different pages */}
    // Auth0Provider & AdminProvier wraps the app to give log in privleges.
    <Auth0Provider
    domain={process.env.REACT_APP_AUTH0_DOMAIN}
    clientId={process.env.REACT_APP_AUTH0_CLIENT_ID}
    redirectUri={window.location.origin}>
    <AdminProvider>

    {/* Router allows linking to other pages */}
    <Router>
      <Header />
      <div>
        <Link to="/">Home</Link>
        <Link to="/adminSermonAdd">Admin Sermon Add</Link>
        <Link to="/adminAnnouncementsAdd">Admin Announcements Add</Link>
        <Link to="/adminEventAdd"> Admin Event Add</Link>
        <Link to="/announcements">Announcements</Link>
        <Link to="/events">Events</Link>
        <Link to="/aboutUs">About Us</Link>
        <Link to="/pastEvents">Past Events</Link>
        <Link to="/photoGallery">Photo Gallery</Link>
        <Link to="/previousSerms">Previous Sermons</Link>
        <Link to="/services">Services</Link>
        <Link to="/upcomingSerms">Upcoming Sermons</Link>
        <Link to="/loginpage">Login Page</Link>
      </div>

      {/* Routes are URLS to different pages */}
      <Routes>
        <Route path="/" element={<Schedule />} />
        <Route path="/schedule" element={<Schedule />} />
        <Route path="/adminSermonAdd" element={<AdminSermonAdd />} />
        <Route path="/adminSermonEdit" element={<AdminSermonEdit />} />
        <Route path="/adminEventAdd" element={<AdminEventAdd />} />
        <Route path="/adminEventEdit" element={<AdminEventEdit />} />
        <Route path="/adminAnnouncementsAdd" element={<AdminAnnouncementsAdd />} />
        <Route path="/adminAnnouncementsEdit" element={<AdminAnnouncementsEdit />} />
        <Route path="/events" element={<Events />} />
        <Route path="/aboutUs" element={<AboutUs />} />
        <Route path="/pastEvents" element={<PastEvents />} />
        <Route path="/photoGallery" element={<PhotoGallery />} />
        <Route path="/previousSerms" element={<PreviousSerms />} />
        <Route path="/services" element={<Services />} />
        <Route path="/upcomingSerms" element={<UpcomingSerms />} />
        <Route path="/loginpage" element={<LoginPage />} />
        <Route path="/sermons/:sermonId" element={<VideoPage />} />
      </Routes>
        <Footer />                {/* Footer: Contact information & Links */}
    </Router>
    </AdminProvider>
    </ThemeProvider>
    </Auth0Provider>
  );
}

export default App;
