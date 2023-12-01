<h1 align="center">
  <img src="./Frontend/imageWeb/sgkc2_transparent_shadow.png" alt="Sacramento Glory Korean Church">
<a href="https://github.com/AndrewT-CSUS/RunDMCPP_SeniorProject/tree/main/Frontend">Frontend</a> | <a href="https://github.com/AndrewT-CSUS/RunDMCPP_SeniorProject/tree/main/Backend/Backend">Backend</a>
</h1>

## 📝 Project Description

Our senior project is a website for a local church. Sacramento Glory Korean Church is a local church that holds
Presbyterian sermons for roughly 50 Korean speakers. As of now, they don't have a website, and are struggling to get
information out to their members. They are having difficulties attracting new members as well. They asked for a
website to assist with these issues, as well as offer resources to their members that they were unable to before.

Primarily, the client wants a page where churchgoers can watch recordings of previous sermons, a widget on the
home page where they can view announcements and upcoming events, and a photo gallery. He also wants a page where 
potential new members can learn more about their church and their beliefs to decide if it is the right place for them. 
   
As of now, we have build out the basic structure of the website, and created a system that allows admins to add sermons to our database, and allows users to search for them and view the linked recording in the form of an embedded YouTube
video. Soon, we will expand this system to support both events and announcements. We have a page for information
that new attendants will want to know about, and are collaborating with the client to make sure that answers to any
questions that he can think of ends up there. We are currently in the process of designing a photo gallery
solution as well.

## 📡 Technologies
   - Java Spring
   - React
   - AWS / DynamoDB

## ⌚ Future Feature Timeline 

 - Late January - Early February 2024
   - Admin User Authentication
   - Photo Gallery
   - Likely Color Scheme Updates
 - Mid February 2024
   - Church Events and Announcements on the Home Page
   - Admin Sermon, Events, and Announcement Editor
   - Likely More Color Scheme Updates
 - Late February 2024 
   - Expanded Admin Features
   - Automatic Event and Announcement Deletion

## 🔧 Development

- TODO: 191
## 🏃‍♂️ Deployment

   - ### Local

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
   
   - ### Remote

     - TODO: 191
## 🔍 Testing

   - TODO: 191
## 🕵️‍♀️ About Us
   - Team RunDMC++ Consists of Alli, Andrew, Ernest, Ethan, Grave, IB, Kevin, and Will

