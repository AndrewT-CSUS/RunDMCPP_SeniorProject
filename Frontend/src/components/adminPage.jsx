import React from 'react';
import { Link } from 'react-router-dom';
import './adminPage.css';

function AdminPage() {
  return (
    <div className="adminPage">
      <h2>Admin Page</h2>
      <div className="adminButtonContainer">
        {/* Buttons for admin pages */}
        <Link to="/adminAnnouncementsAdd">
          <button className="adminButton">Add Announcement</button>
        </Link>
        <Link to="/adminAnnouncementsEdit">
          <button className="adminButton">Edit Announcements</button>
        </Link>
        <Link to="/adminEventAdd">
          <button className="adminButton">Add Event</button>
        </Link>
        <Link to="/adminEventEdit">
          <button className="adminButton">Edit Events</button>
        </Link>
        <Link to="/adminSermonAdd">
          <button className="adminButton">Add Sermon</button>
        </Link>
        <Link to="/adminSermonEdit">
          <button className="adminButton">Edit Sermons</button>
        </Link>
      </div>
    </div>
  );
}

export default AdminPage;


