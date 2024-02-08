<>
  <meta charSet="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>homePage</title>
  <link rel="stylesheet" href="header.css" />
  <header>
    {/*Church Logo */}
    <img src="imageWeb/sgkc4.png" width="225px" height="60px" />
    {/*Title*/}
    <h1 className="sameLine" id="sgkc">
      Sacramento Glory Korean Church
    </h1>
    {/*Eng/Kor Button*/}
    <button className="sameLine" onclick="toggleText(this)">
      한국어
    </button>
    {/* Navigation bar */}
    <ul>
      <li>
        <a href="announcements.html" id="announcements">
          Announcements
        </a>
      </li>
      <li className="dropdown">
        <a href="javascript:void(0)" className="dropbtn" id="eventsDrop">
          Events ▼
        </a>
        <div className="dropdown-content">
          <a href="events.html" id="eventsPage">
            Events
          </a>
          <a href="pastEvents.html" id="past events">
            Past Events
          </a>
          <a href="photoGallery.html" id="photoGallery">
            Photo Gallery
          </a>
        </div>
      </li>
      <li className="dropdown">
        <a href="javascript:void(0)" className="dropbtn" id="sermons">
          Sermons ▼
        </a>
        <div className="dropdown-content">
          <a href="upcomingSerms.html" id="upcomingSermons">
            Upcoming
          </a>
          <a href="previousSerms.html" id="previousSermons">
            Previous
          </a>
        </div>
      </li>
      <li>
        <a href="services.html" id="services">
          Services
        </a>
      </li>
      <li className="dropdown">
        <a href="javascript:void(0)" className="dropbtn" id="aboutUs">
          About Us ▼
        </a>
        <div className="dropdown-content">
          <a href="ourBeliefs.html" id="ourBeliefs">
            Our Beliefs
          </a>
          <a href="ourGoals.html" id="ourGoals">
            Our Goals
          </a>
          <a href="dateTime.html" id="dateTime">
            Date / Time
          </a>
          <a href="languages.html" id="languages">
            Language
          </a>
        </div>
      </li>
      <li>
        <a className="active" href="default.html" id="home">
          Home
        </a>
      </li>
    </ul>
  </header>
  <main>
    {/* Main content goes here*/}
    <h2 id="serviceSchedule">Church Service Schedule</h2>
    {/*Schedule description maybe? */}
    <table border={5}>
      <tbody>
        <tr>
          <th id="serviceScheduleDay">Day</th>
          <th id="serviceScheduleTime">Time</th>
        </tr>
        <tr>
          <td id="serviceScheduleSunday">Sunday</td>
          <td>11:00 AM</td>
        </tr>
        <tr>
          <td id="serviceScheduleWednesday">Wednesday</td>
          <td> 07:30 PM</td>
        </tr>
      </tbody>
    </table>
    <p>This is the Home page</p>
  </main>
  <footer>
    {/* Footer content goes here */}
    <p className="sameLine" style={{ fontSize: 12 }}>
      Sacramento Glory Korean Church
      <br />
      1820 Bell Street
      <br />
      Sacramento, CA. 95825
    </p>
    <p className="sameLine" style={{ fontSize: 12, float: "right" }}>
      Contact Us:{" "}
      <a href="mailto: sacglorychurch@hotmail.com">
        sacglorychurch@hotmail.com
      </a>
    </p>
  </footer>
</>
