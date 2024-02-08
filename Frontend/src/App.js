//import logo from './logo.svg';
import React from 'react';
import AdminSermonts from './adminSermonAdd.jsx';
import Announcements from './announcements.jsx';
import ChurchTemplate from './ChurchTemplate.jsx';
import DateTime from './dateTime.jsx';
import Events from './events.jsx';
import Default from './default2.jsx';
import Events from './events.jsx';
import Language from './languages.jsx';
import Beliefs from './ourBeliefs.jsx';
import Goals from './ourGoals.jsx';
import PastEvents from './pastEvents.jsx';
import PhotoGallery from './photoGallery.jsx';
import PreviousSerms from './previousSerms.jsx';
import Services from './services.jsx';
import UpcomingSerms from './upcomingSerms.jsx';


import './App.css';

function App() {
  return (
    <div>
      {/* Adding all the Jsx files/pages */}
      <AdminSermonts />
      <Announcements />
      <ChurchTemplate />
      <DateTime />
      <Default />
      <Events />
      <Language />
      <Beliefs />
      <Goals />
      <PastEvents />
      <PhotoGallery />
      <PreviousSerms />
      <Services />
      <UpcomingSerms />

      
    </div>
   
  );
}

export default App;
