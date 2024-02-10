import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Schedule from './Frontend_w_Jsx/schedule';
import AdminSermonAdd from './Frontend_w_Jsx/adminSermonAdd';
import Announcements from './Frontend_w_Jsx/announcements';
import Events from './Frontend_w_Jsx/events';
import Languages from './Frontend_w_Jsx/languages';
import OurBeliefs from './Frontend_w_Jsx/ourBeliefs';
import OurGoals from './Frontend_w_Jsx/ourGoals';
import PastEvents from './Frontend_w_Jsx/pastEvents';
import PhotoGallery from './Frontend_w_Jsx/photoGallery';
import PreviousSerms from './Frontend_w_Jsx/previousSerms';
import Services from './Frontend_w_Jsx/services';
import UpcomingSerms from './Frontend_w_Jsx/upcomingSerms';

function App() {
  return (
    <Router>
      <div>
        <Link to="/">Home</Link>
        <Link to="/adminSermonAdd">Admin Sermon Add</Link>
        <Link to="/announcements">Announcements</Link>
        <Link to="/events">Events</Link>
        <Link to="/languages">Languages</Link>
        <Link to="/ourBeliefs">Our Beliefs</Link>
        <Link to="/ourGoals">Our Goals</Link>
        <Link to="/pastEvents">Past Events</Link>
        <Link to="/photoGallery">Photo Gallery</Link>
        <Link to="/previousSerms">Previous Sermons</Link>
        <Link to="/services">Services</Link>
        <Link to="/upcomingSerms">Upcoming Sermons</Link>
        {/* Add links for other pages */}
      </div>

      <Routes>
        <Route path="/" element={<Schedule />} />
        <Route path="/adminSermonAdd" element={<AdminSermonAdd />} />
        <Route path="/announcements" element={<Announcements />} />
        <Route path="/events" element={<Events />} />
        <Route path="/languages" element={<Languages />} />
        <Route path="/ourBeliefs" element={<OurBeliefs />} />
        <Route path="/ourGoals" element={<OurGoals />} />
        <Route path="/pastEvents" element={<PastEvents />} />
        <Route path="/photoGallery" element={<PhotoGallery />} />
        <Route path="/previousSerms" element={<PreviousSerms />} />
        <Route path="/services" element={<Services />} />
        <Route path="/upcomingSerms" element={<UpcomingSerms />} />
        {/* Add routes for other pages */}
      </Routes>
    </Router>
  );
}

export default App;
