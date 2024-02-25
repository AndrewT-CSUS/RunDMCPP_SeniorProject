
// Import libraries
import React, { } from 'react';  
import { Auth0Provider, useAuth0 } from '@auth0/auth0-react';  // OAuth
import './loginPage.css';
import logo from '../images/sgkc4.png';

// LoginPage component: Login page for Auth0
function LoginPage() {

    // OAuth: Variables to handle login, authentication (if user is logged in), logout
    const auth0 = useAuth0();
    const loginWithRedirect = auth0.loginWithRedirect;
    const isAuthenticated = auth0.isAuthenticated;
    const logout = auth0.logout;
    const user = auth0.user;

    // Function: Component to show the user's account picture, name, and logout button
    function LoggedInUserProfile() {
        return (
        <div className = "logged-in-container">
            {/* User picture has temporarily been removed, as 'rundmcpp@gmail.com' is a bit too long */}
            <div className="user-picture-and-logout">
                <h2 className="user-name">{user.name}</h2>        
                <button onClick={() => logout({ returnTo: window.location.origin })}>Log Out</button>
            </div>
        </div>
        );
    }

    // Render the login page
    return (        
    <div className = "login-page">
        <div className = "login-logo-container">
            <img src={logo} alt="logo" className="logo"/>
        </div>
        {/* If user is logged in, show their account picture and name. */}
        {isAuthenticated && (
            <LoggedInUserProfile />
        )}
        {/* If user is not logged in, show the login button */}
        {!isAuthenticated && (
            <div>
                <button onClick={loginWithRedirect}>Log In</button>
            </div>
        )}
        
    </div>
    );
}

/* App component - Defined here in order to wrap the LoginPage component with Auth0Provider.
    This is necessary to use the Auth0Provider in the LoginPage component, as it is the root component.
    Some prefer to seperate this logic, or keep it all in this 1 file for simplicity.*/
function App() {
    // To find your domain and clientId, go to https://manage.auth0.com/dashboard/
    // Go to 'Applications' (3rd symbol from top in left toolbar that looks like 3 diamonds) -> Settings
    const domain = process.env.REACT_APP_AUTH0_DOMAIN;
    const clientId = process.env.REACT_APP_AUTH0_CLIENT_ID;

    return (
        <Auth0Provider
            domain={domain}
            clientId={clientId}
            redirectUri={window.location.origin}>
            <LoginPage />
        </Auth0Provider>
    );
}

export default App;
export { LoginPage }