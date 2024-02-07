<>

  <meta charSet="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>adminSermonCreate</title>
  <link rel="stylesheet" href="header.css" />
  <header>
    {/*Title*/}
    <h1>
      Sacramento Glory
      <br />
      Korean Church
    </h1>
    {/* Navigation bar */}
    <ul>
      <li>
        <a href="announcements.html">Announcements</a>
      </li>
      <li className="dropdown">
        <a href="javascript:void(0)" className="dropbtn">
          Events ▼
        </a>
        <div className="dropdown-content">
          <a href="events.html">Events</a>
          <a href="pastEvents.html">Past Events</a>
          <a href="photoGallery.html">Photo Gallery</a>
        </div>
      </li>
      <li className="dropdown">
        <a href="javascript:void(0)" className="dropbtn">
          Sermons ▼
        </a>
        <div className="dropdown-content">
          <a href="upcomingSerms.html">Upcoming</a>
          <a href="previousSerms.html">Previous</a>
        </div>
      </li>
      <li>
        <a href="services.html">Services</a>
      </li>
      <li className="dropdown">
        <a href="javascript:void(0)" className="dropbtn">
          About Us ▼
        </a>
        <div className="dropdown-content">
          <a href="ourBeliefs.html">Our Beliefs</a>
          <a href="ourGoals.html">Our Goals</a>
          <a href="dateTime.html">Date / Time</a>
          <a href="languages.html">Language</a>
        </div>
      </li>
      <li>
        <a className="active" href="default.html">
          Home
        </a>
      </li>
    </ul>
  </header>
  <main>
    {/* Main content goes here */}
    <h1>Create Sermon</h1>
    <div className="mainContent">
      <fieldset className="addSermonBox">
        <legend>Sermon Information</legend>
        <br />
        <textarea
          rows={1}
          cols={60}
          id="nameBox"
          name="nameBox"
          placeholder="Sermon Name"
          defaultValue={""}
        />
        <br />
        <br />
        <textarea
          rows={1}
          cols={60}
          id="youtubeLinkBox"
          name="youtubeLinkBox"
          placeholder="YouTube Link"
          defaultValue={""}
        />
        <br />
        <br />{" "}
        <input
          type="datetime-local"
          id="sermonDateTime"
          name="sermonDateTime"
        />{" "}
        <br />
        <br />
        <textarea
          rows={4}
          cols={60}
          id="descriptionBox"
          name="descriptionBox"
          placeholder="Sermon Description"
          defaultValue={""}
        />
        <br />
        <br />
        <button
          type="button"
          id="previewButton"
          onclick="createPreview()"
          style={{ width: 120 }}
        >
          Preview
        </button>
        <br />
      </fieldset>
      <br />
      <br />
      <fieldset id="resultsField" className="previewBox" hidden="">
        <legend>Sermon Preview</legend>
        <h4 id="resultTitle">Title</h4>
        <p id="resultDescription">Desc</p>
        <p id="resultDateTime">Date and Time</p>
        <iframe
          id="video"
          width={420}
          height={315}
          src="https://www.youtube.com/watch?v=aZYfWdkaIVs"
        >
          {" "}
        </iframe>
        <br />
        <button type="button" onclick="addSermon()" style={{ width: 120 }}>
          Add Sermon
        </button>
        <br />
      </fieldset>
    </div>
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
