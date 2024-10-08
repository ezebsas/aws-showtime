import { createContext, useContext, useEffect, useState } from 'react';
import axios from 'axios';
import config from '../config'; // Adjust the import path as necessary
import { useAuth } from '../context/AuthContext';

const UserContext = createContext();

export const useUser = () => {
    return useContext(UserContext);
};

export const UserProvider = ({ children }) => {
    const { jwt } = useAuth();
    const [user, setUser] = useState({
        username: '',
        firstname: '',
        lastname: '',
        country: '',
    });

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const response = await axios.get(`${config.url}users/me`, {
                    headers: {
                        Authorization: `Bearer ${jwt}`, // Include the Authorization header here
                    }
                }); // Adjust the endpoint as necessary
                setUser({
                    username: response.data.username,
                    firstname: response.data.firstname,
                    lastname: response.data.lastname,
                    country: response.data.country,
                });
            } catch (error) {
                console.error('Error fetching user data:', error);
            }
        };

        // Check if user is empty and fetch data if so
        if (!user.username) {
            fetchUserData();
        }
    }, [jwt, user.username]); // Dependency array includes user.username

    return (
        <UserContext.Provider value={{ user, setUser }}>
            {children}
        </UserContext.Provider>
    );
};
