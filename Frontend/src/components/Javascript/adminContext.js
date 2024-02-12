import React, { createContext, useContext, useState, useEffect } from 'react';
import { useAuth0 } from '@auth0/auth0-react';

// Create a context
const AdminContext = createContext();

// Create a custom hook to use the admin context
export const useAdmin = () => useContext(AdminContext);

// Provider component
export const AdminProvider = ({ children }) => {
    const { user, isAuthenticated } = useAuth0();
    const [isAdmin, setIsAdmin] = useState(false);

    useEffect(() => {
        // Check if the authenticated user is the admin
        if (isAuthenticated && user.email === 'rundmcpp@gmail.com') {
            setIsAdmin(true);
        } else {
            setIsAdmin(false);
        }
    }, [user, isAuthenticated]);

    return (
        <AdminContext.Provider value={{ isAdmin }}>
            {children}
        </AdminContext.Provider>
    );
};
