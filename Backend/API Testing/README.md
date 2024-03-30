<h2>INTRO</h2>
The JSON file included in this folder contains a [Postman](https://www.postman.com/) collection that allows the user to test all API endpoints. All endpoints have tests included with them that check for correct functionality.

There are three folders:
 - <h3>Ignores Auth</h3>
   - These endpoints do not have any authentication requirements, and as such only need to test for proper function
 - <h3>Missing Auth</h3>
   - These endpoints *do* have authentication requirements, but the calls here do not have the correct authentication. This way, we can assure that our API security is functioning correctly
 - <h3>Has Auth</h3>
   - These are mirrors of the endpoints in the previous file, but have authentication set up. The tester will need to run the "Get Access Token" endpoint before testing these endpoints.
 
<h2>SETUP</h2>
When using postman, the user can import the collection into their workspace. It comes set up with some collection variables, but a user might need to change some to account for their local development environment. Mainly, "hostname" will change if you have your Java Spring using a non-default port.

The user will also need to create an **Environment Variable:** `Auth0ApiPrefix`

This variable should be of type Secret, and should be similar in format to `dev-[YOUR_AUTH0_VALUES].us`

Now, the user can get their access token. If their database is empty, I reccomend running the **CREATE** endpoints in **Has Auth** a few times (At least 11 times for Sermons, 3 times for Announcements, and 1 time for Events)

Assuming the user has done this, they should be able to test each endpoint... individually

<h2>ISSUE(S)</h2>
Currently, the collection doesn't *quite* work when running all the endpoints in one large run. It's something to do with the way the token is handled, I believe, but I'm unable to test it completely as I've hit the limit on the number of times I can run all the endpoints in the collection at once (25). I am very disappointed that Postman has implemented this restriction, and if I had more time, I would look into switching to a different API testing client. ***THE USER CAN STILL TEST EACH ENDPOINT INDIVIDUALLY***, and in my opinion is useful, as they can observe the changes made to the database live, but it still is a shame that they can only test all the endpoints together a limited number of times.