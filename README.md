<h1 align="center">
  <img src="./Readme Resources/header.png" alt="Sacramento Glory Korean Church" width=100%>
</h1>

## ⭐ Status
<b>Development is almost complete!</b> 
Our team is currently working on testing and documentation, and our client is working on adding custom content. 
We are on track to meet our deadline of May 2024.

## 📝 Project Description

<b><i>Sacramento Glory Korean Church</i></b> is a local church that holds Presbyterian sermons for roughly 50 Korean speakers. 

Prior to contacting us, they did not have a website, and were struggling to get information out to their community as well as attracting new members. Our client asked for a website to assist with these issues, as well as offer resources to their members that they were unable to before.

Our team put in our utmost effort to fulfill our client's requirements. This would include...

- A beautiful, easy-to-navigate, modern, responsive website that is accessible to all members of the church.
- A page where churchgoers can watch recordings of previous sermons.
- A widget on the home page where they can view announcements and upcoming events.
- A photo gallery with multiple photo albums & the ability to freely upload photos. 
- A page where potential new members can learn more about their church and their beliefs to decide if it is the right community for them. 
   

## 📡 Technologies
   - HTML / CSS
   - JavaScript
   - Java Spring
   - React
   - AWS
   - DynamoDB
   - Docker

## ⌚ Feature Timeline 

 - Late January - Early February 2024
   - Admin User Authentication
   - Photo Gallery
 - Mid February 2024
   - Church Events and Announcements on the Home Page
   - Admin Sermon, Events, and Announcement Editor
   - Color Scheme Updates
 - Late February 2024 - Early March 2024
   - Expanded Admin Features
   - Automatic Event and Announcement Deletion
   - Photo Gallery Albums
   - Admin Authentication
 - Mid March 2024
   - Photo Gallery Management
   - Accessibility Improvements
   - Sermon Viewer Improvements
 - Late March 2024 - Early April 2024
   - Home Page Carousel
   - Content Additions (Text, Images)
   - Photo Gallery Uploads
 - Mid April 2024
   - Final Design Touchups
   - Responsive Design
   - Bug Fixes
   - Deployment
 - Late April 2024
   - Final Testing
   - Documentation
 - Early May 2024
   - Presentation

## 🏃‍♂️ Deployment
 - Remote
    - The website is currently up at <a href="https://sacglorychurch.org">sacglorychurch.org</a> & awaiting content from the client. 
 - Local

     - A local server and database can be run on a developer's machine. To do so, they will need to install DynamoDB on
      their local machines. This can be done by following the guide found on the 
     [AWS DynamoDB Developer Guide](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html)
     under "Deploying." Our team used the [Docker](https://www.docker.com/) container option for consistency.
     - Our server doesn't run in a container, however, so we have to set up the AWS_ACCESS_KEY_ID and 
     AWS_SECRET_ACCESS_KEY ourselves. These don't have to be anything specific, as this is just a local database. 
     This can be done by adding them as environment variables to whatever IDE you use, or, if running the server from
     Powershell, you can add them temporarily by running these commands, filling them in with whatever you want the strings to be.
     They will last until you close the terminal:

```
      $env:AWS_ACCESS_KEY_ID = '<???>'
      $env:AWS_SECRET_ACCESS_KEY = '<???>'
```

## 🔍 Testing

  Our team is currently in the testing phases of our project as of April 2024. We are working on testing the functionality of our website, as well as the security of our server.
  
## ❤️ Our Team
Team RunDMC++ is a group of Computer Science students at California State University, Sacramento. We are working on this project as part of our Senior Project course. Our team consists of:

- [Allison Keagy](https://github.com/TheHonkmeister)
- [Andrew Tracy](https://github.com/AndrewT-CSUS) 
- [Ernest Kim](https://github.com/spjcyTofu)
- [Ethan Calvo](https://github.com/YungGuam)
- [Grace Jung](https://github.com/gjung01) 
- [Ibrahim Ouattara](https://github.com/Eyebeee) 
- [Kevin Cendana](https://github.com/kevin-cendana)
- [Will Roberts](https://github.com/willsonic4)
  
## 📷 Sample Image Timeline

You can see our design progress as we get closer to completion. As we received different feedback and adapted to changing requirements, the project has undergone many changes. Our client has been very happy with our progress so far, and we are excited to release the final product.

Initial Static Text Page
![Initial Static Page Design](/Readme%20Resources/General%20Static%20Text%20Page.png)

Initial Design for our Sermon Viewer
  ![Initial Design for our Sermon Viewer](/Readme%20Resources/Previous%20Sermons.png)

Mockup Rework with a New Colorscheme
  ![New Colorscheme Mockup](/Readme%20Resources/Mockup%20Rework.png)

Sermon Uploader Page, as of December 2023
  ![Sermon Uploader Page](/Readme%20Resources/Sermon%20Uploader.png)

Current About Us Page, as of April 2024
  ![Current About Us Page](/Readme%20Resources/About%20Page.png)
