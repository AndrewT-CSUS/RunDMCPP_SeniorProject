import React from 'react';

// Define your functional component
function AdminSermonAdd() {
    return (
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
                        <a href="announcements">Announcements</a>
                    </li>
                    <li className="dropdown">
                        <a href="javascript:void(0)" className="dropbtn">
                            Events ▼
                        </a>
                        <div className="dropdown-content">
                            <a href="events">Events</a>
                            <a href="pastEvents">Past Events</a>
                            <a href="photoGallery">Photo Gallery</a>
                        </div>
                    </li>
                    {/* Other navigation items */}
                </ul>
            </header>
            <main>
                {/* Main content goes here */}
                <h1>Create Sermon</h1>
                <div className="mainContent">
                    <fieldset className="addSermonBox">
                        {/* Add your form fields and elements here */}
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
    );
}

// Export the component as the default export
export default AdminSermonAdd;
