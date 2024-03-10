import React, { useEffect, useState } from 'react';
import { getEvents } from './Javascript/fetchEvent';
/*import styles from './Events.css'; */

function Events() {
  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

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

  return (
    <main>
      <h1>Events</h1>
      {loading ? (
        <p>Loading...</p>
      ) : error ? (
        <p>{error}</p>
      ) : events.length === 0 ? (
        <p>No upcoming events</p>
      ) : (
        <div className="eventsList">
          {events.map((event, index) => (
            <div key={index} className="event">
              <h2>{event.name}</h2>
              <p>Date & Time: {new Date(event.dateTime).toLocaleString()}</p>
              <p>Location: {event.eventLocation}</p>
              <p>Description: {event.eventDescription}</p>
            </div>
          ))}
        </div>
      )}
    </main>
  );
}

export default Events;
