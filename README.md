<h1 align="center">
  <img src="./Readme Resources/header.png" alt="Sacramento Glory Korean Church" width=100%>
</h1>

## ⭐ Status
<b>Development is complete!</b> 

Our project is completed. We're incredibly proud of our project, and we hope that it will serve our client well.

## 📝 Project Description

<b><i>Sacramento Glory Korean Church</i></b> is a local church that holds Presbyterian sermons for roughly 50 Korean speakers. 

Prior to contacting us, they did not have a website, and were struggling to get information out to their community as well as attracting new members. Our client asked for a website to assist with these issues, as well as offer resources to their members that they were unable to before.

Our team put in our utmost effort to fulfill our client's requirements. This would include...

- A beautiful, easy-to-navigate, modern, responsive website that is accessible to all members of the church.
- A page where churchgoers can watch recordings of previous sermons.
- A widget on the home page where they can view announcements and upcoming events.
- A photo gallery with multiple photo albums & the ability to freely upload photos. 
- A page where potential new members can learn more about their church and their beliefs to decide if it is the right community for them. 
- A server and database pair that enables the storage of dynamic content that can be served to the church members.
   

## 📡 Technologies
   - HTML / CSS
   - JavaScript
   - React
   - Java
   - Java Spring
   - AWS
   - DynamoDB
   - Docker

## ⌚ Feature Timeline 

 - Late January - Early February 2024
   - Admin User Authentication
   - Photo Gallery
 - Mid-February 2024
   - Church Events and Announcements on the Home Page
   - Admin Sermon, Events, and Announcement Editor
   - Color Scheme Updates
 - Late February 2024 - Early March 2024
   - Expanded Admin Features
   - Automatic Event and Announcement Deletion
   - Photo Gallery Albums
   - Admin Authentication
 - Mid-March 2024
   - Photo Gallery Management
   - Accessibility Improvements
   - Sermon Viewer Improvements
 - Late March 2024 - Early April 2024
   - Home Page Carousel
   - Content Additions (Text, Images)
   - Photo Gallery Uploads
 - Mid-April 2024
   - Final Design Touch-ups
   - Responsive Design
   - Bug Fixes
   - Deployment
 - Late April 2024
   - Final Testing
   - Documentation
 - Early May 2024
   - Presentation

## 📦 Deployment
 - Remote
    - The website is currently up at <a href="https://sacglorychurch.org">sacglorychurch.org</a> and the database is slowly being populated with our client's content.

    - If you plan on deploying remotely yourself, please note that setting up an SSL certificate for the Frontend will necessitate setting one up for the Backend. 
 - Local
     - A local server and database can be run on a developer's machine like any Java program and any React project. On top of that, they may need to install DynamoDB on
      their local machines. This can be done by following the guide found on the 
     [AWS DynamoDB Developer Guide](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html)
     under "Deploying." Our team used the [Docker](https://www.docker.com/) container option for consistency.


  - Regardless of where it runs, the backend server will expect the user to do two things: 
    - Setting up AWS DynamoDB Access Keys 
      - When running the server, it looks at environment variables for the DynamoDB access information. When running the server from Powershell, they can be temporarily set like so:
        ```
        $env:AWS_ACCESS_KEY_ID = '<???>'
        $env:AWS_SECRET_ACCESS_KEY = '<???>'
        ```
        Note that, if running the server locally and with a local database through Docker, these values don't matter, at long as they exist.
    - Configuring an Authentication Provider
      - In the [resources directory](Backend/Backend/src/main/resources), the server will expect a file named env.properties, with two lines:
        ```
        okta.oauth2.issuer=<URL>
        okta.oauth2.audience=<URL>
        ```
        The URLs in this file should point to the Auth0 domains you are using. You may also need to update the Frontend [env](Frontend/env) file to match.



## 🔍 Testing

  For automated API tests, see [Backend Testing](Backend/API%20Testing/README.md).

  To test our Backend, we use JUnit and Mockito, the usual Spring Framework testing tools. Each method on each service level has a multitude of tests to ensure that they function correctly.

  Outside of our automated tests, we did extensive Frontend testing to ensure that our website performs as desired.
  
## ❤️ Our Team
Team RunDMC++ is a group of Computer Science students at California State University, Sacramento. We built this project as part of our Senior Project course. Our team consists of:

- [Allison Keagy](https://github.com/TheHonkmeister)
- [Andrew Tracy](https://github.com/AndrewT-CSUS) 
- [Ernest Kim](https://github.com/spjcyTofu)
- [Ethan Calvo](https://github.com/YungGuam)
- [Grace Jung](https://github.com/gjung01) 
- [Ibrahim Ouattara](https://github.com/Eyebeee) 
- [Kevin Cendana](https://github.com/kevin-cendana)
- [Will Roberts](https://github.com/willsonic4)

![Team Logo](/Readme%20Resources/Team%20Logo.png)
  
## 📷 Sample Image Timeline

You can see our design progress as we got closer to completion. As we received different feedback and adapted to changing requirements, the project underwent many visual changes. This was all in service to our client, who was incredibly pleased with the final result.

Initial Static Text Page
![Initial Static Page Design](/Readme%20Resources/General%20Static%20Text%20Page.png)

Initial Design for our Sermon Viewer
  ![Initial Design for our Sermon Viewer](/Readme%20Resources/Previous%20Sermons.png)

Mockup Rework with a New Colorscheme
  ![New Colorscheme Mockup](/Readme%20Resources/Mockup%20Rework.png)

Sermon Uploader Page, as of December 2023
  ![Sermon Uploader Page](/Readme%20Resources/Sermon%20Uploader.png)

Current Home Page, as of April 2024
![Current Home Page](/Readme%20Resources/Home%20Page.png)

Current About Us Page, as of April 2024
  ![Current About Us Page](/Readme%20Resources/About%20Page.png)
