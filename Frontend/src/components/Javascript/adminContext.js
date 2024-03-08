

// Import libraries
import React, { createContext, useContext, useState, useEffect } from 'react';
import { useAuth0 } from '@auth0/auth0-react';

// Create a context
const AdminContext = createContext();

// Create a custom hook to use the admin context
export const useAdmin = () => useContext(AdminContext);

// Provider component
export const AdminProvider = ({ children }) => {
    const { user, isAuthenticated } = useAuth0();  // Get the user and isAuthenticated state from Auth0
    const [isAdmin, setIsAdmin] = useState(false); // State: isAdmin

    // On component mount: Check if the authenticated user is the admin
    useEffect(() => {
        // Email of the admin user specifically has to match the hardcoded value (only 1 Google account allowed!)
        if (isAuthenticated && user.email === 'rundmcpp@gmail.com') {
            setIsAdmin(true);
        } else {
            setIsAdmin(false);
        }
    }, [user, isAuthenticated]);

    // Return the AdminContext.Provider with the value of isAdmin
    return (
        <AdminContext.Provider value={{ isAdmin }}>
            {children}
        </AdminContext.Provider>
    );
};


// Example of how to use the adminContext.js in a component:

/*
import React from 'react';
import { useAdmin } from './components/Javascript/adminContext';

const SomeComponent = () => {
  const { isAdmin } = useAdmin();

  return (
    <div>
      {isAdmin ? <p>I'm an admin!</p> : <p>I'm not an admin.</p>}
    </div>
  );
};

export default SomeComponent;
*/
