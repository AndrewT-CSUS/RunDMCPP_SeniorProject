import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { Auth0Provider } from '@auth0/auth0-react';
import './components/Javascript/i18n';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <React.StrictMode>
    {/* Auth0Provider wraps the app to give log in privleges. */}
    {/* These Auth0 login credentials should be in /Frontend/.env */}
    <Auth0Provider
      domain={process.env.REACT_APP_AUTH0_DOMAIN} 
      clientId={process.env.REACT_APP_AUTH0_CLIENT_ID} 
      redirectUri={window.location.origin || "https://dev-uwwvwuxa5yyo6k20.us.auth0.com"}
    >
      <App />
    </Auth0Provider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
