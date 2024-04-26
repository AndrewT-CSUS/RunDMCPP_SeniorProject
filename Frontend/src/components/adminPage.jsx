import React from 'react';
import { Link } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import './adminPage.css';

function AdminPage() {
  const { t } = useTranslation();
  return (
    <div className="adminPage">
      <h2>{t('adminPage')}</h2>
      <div className="adminButtonContainer">
        {/* Buttons for admin pages */}
        <Link to="/adminAnnouncementsAdd">
          <button className="adminButton">{t('addAnnouncement')}</button>
        </Link>
        <Link to="/adminAnnouncementsEdit">
          <button className="adminButton">{t('editAnnouncement')}</button>
        </Link>
        <Link to="/adminEventAdd">
          <button className="adminButton">{t('addEvent')}</button>
        </Link>
        <Link to="/adminEventEdit">
          <button className="adminButton">{t('editEvent')}</button>
        </Link>
        <Link to="/adminSermonAdd">
          <button className="adminButton">{t('addSermon')}</button>
        </Link>
        <Link to="/adminSermonEdit">
          <button className="adminButton">{t('editSermon')}</button>
        </Link>
      </div>
    </div>
  );
}

export default AdminPage;


