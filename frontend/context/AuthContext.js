// context/AuthContext.js
import { createContext, useContext, useEffect, useState } from 'react';
import axios from 'axios';
import config from '../config';
import { useRouter } from 'next/router';

const AuthContext = createContext();

export const useAuth = () => {
  return useContext(AuthContext);
};

export const AuthProvider = ({ children }) => {
  const [jwt, setJwt] = useState(null);
  const router = useRouter();
  const [redirecting, setRedirecting] = useState(false); // Flag to prevent multiple redirects

  useEffect(() => {
    if (typeof window !== 'undefined') { // Check if running in the browser
      const jwtStore = localStorage.getItem('jwt');
      if (jwtStore) {
        const payload = parseJwt(jwtStore);
        if (!payload || Date.now() >= payload.exp * 1000) {
          localStorage.removeItem('jwt'); // Clear expired token
          if (!redirecting) {
            setRedirecting(true);
            router.push('/login'); // Redirect to login if token is expired
          }
        } else {
          setJwt(jwtStore); // Set the token if valid
        }
      } else {
        if (!redirecting) {
          setRedirecting(true);
          router.push('/login'); // Redirect to login if no JWT is found
        }
      }
    }
  }, [router, redirecting]);

  const login = async (email, password) => {
    try {
      const res = await axios.post(`${config.url}auth/login`, { username: email, password });
      const token = res.data.token;
      setJwt(token);  // Store the token in context
      localStorage.setItem('jwt', token); // Store the token in local storage
      return token;
    } catch (error) {
      console.error('Login failed:', error);
      throw error;
    }
  };

  const logout = () => {
    setJwt(null);
    localStorage.removeItem('jwt'); // Clear the token on logout
    router.push('/login'); // Redirect to login after logout
  };

  return (
    <AuthContext.Provider value={{ jwt, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

// Function to parse JWT and get payload
function parseJwt(token) {
  try {
    const base64Url = token.split('.')[1];
    const base64 = decodeURIComponent(atob(base64Url).split('').map(c => {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(base64);
  } catch (e) {
    return null; // Return null if parsing fails
  }
}
