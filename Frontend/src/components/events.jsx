import React, { useEffect, useState } from 'react';
import { getEvents } from './Javascript/fetchEvent';
import { useTranslation } from 'react-i18next';
/*import styles from './Events.css'; */
import './Events.css';

function Events() {
  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const { t } = useTranslation();

  useEffect(() => {
    fetchEvents();
  }, []);

  const fetchEvents = async () => {
    try {
      const eventData = await getEvents(); 
      // Sort events by dateTime from the soonest to the furthest
      eventData.sort((a, b) => new Date(a.dateTime) - new Date(b.dateTime));

      setEvents(eventData);
    } catch (error) {
      console.error('Error fetching events:', error);
      setError('Error: Failed to fetch events -_-');
    } finally {
      setLoading(false);
    }
  };

  const handleEventClick = (index) => {
    const updatedEvents = [...events];
    updatedEvents[index].expanded = !updatedEvents[index].expanded;
    setEvents(updatedEvents);
  };

  return (
    <main>
      <h1>{t('events')}</h1>
      {loading ? (
        <p>Loading...</p>
      ) : error ? (
        <p>{error}</p>
      ) : events.length === 0 ? (
        <p>No upcoming events</p>
      ) : (
        <div className="eventsList">
          {events.map((event, index) => (
            <div key={index} className="event" onClick={() => handleEventClick(index)}>
              <h2>{event.name}</h2>
              <p>Date & Time: {new Date(event.dateTime).toLocaleString()}</p>
              <p>Location: {event.eventLocation}</p>
              <p>Description: {event.expanded ? event.eventDescription : `${event.eventDescription.slice(0, 100)}...`}</p>
              {event.expanded && <button>See Less</button>}
            </div>
          ))}
        </div>
      )}
    </main>
  );
}

export default Events;
