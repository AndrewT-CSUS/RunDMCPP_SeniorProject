// Libraries: Allows linking to other pages, OAuth login
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'; // Linking to other pages
import { Auth0Provider, useAuth0 } from '@auth0/auth0-react';  // OAuth Admin Login
import { AdminProvider } from './components/Javascript/adminContext'; // Allows for admin privleges

// Pages: Importing all the different pages
import Schedule from './components/schedule';
import AdminAnnouncementsAdd from './components/adminAnnouncementsAdd'
import AdminEventAdd from './components/adminEventAdd'
import AdminSermonAdd from './components/adminSermonAdd';
import AdminSermonEdit from './components/adminSermonEdit';
import Announcements from './components/announcements';
import Events from './components/events';
import AboutUs from './components/aboutUs';
import PastEvents from './components/pastEvents';
import PhotoGallery from './components/photoGallery';
import PreviousSerms from './components/previousSerms';
import Services from './components/services';
import UpcomingSerms from './components/upcomingSerms';
import LoginPage from './components/loginPage'; /* Login Page for OAuth */

/* Header and Footer for every page */
import Header from './components/shared/header';
import Footer from './components/shared/footer';

// App Component
function App() {
  return (
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
        <Route path="/adminAnnouncementsAdd" element={<AdminAnnouncementsAdd />} />
        <Route path="/announcements" element={<Announcements />} />
        <Route path="/events" element={<Events />} />
        <Route path="/aboutUs" element={<AboutUs />} />
        <Route path="/pastEvents" element={<PastEvents />} />
        <Route path="/photoGallery" element={<PhotoGallery />} />
        <Route path="/previousSerms" element={<PreviousSerms />} />
        <Route path="/services" element={<Services />} />
        <Route path="/upcomingSerms" element={<UpcomingSerms />} />
        <Route path="/loginpage" element={<LoginPage />} />
      </Routes>
      <Footer />
    </Router>

    </AdminProvider>
    </Auth0Provider>

  );
}

export default App;
