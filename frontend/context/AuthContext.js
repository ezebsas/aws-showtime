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

  // useEffect(()=>{
  //   const jwtStore = localStorage.getItem('jwt')
  //   if(!jwtStore){
  //     router.push('login');
  //   }
  // },[]);

  const login = async (email, password) => {
    try {
      const res = await axios.post(`${config.url}auth/login`, { email, password });
      const token = res.data.token;
      setJwt(token);  // Guarda el token en el contexto
      // También puedes guardar el token en localStorage si quieres mantenerlo entre recargas de la página
      localStorage.setItem('jwt', token);
      return token;
    } catch (error) {
      console.error('Login failed:', error);
      throw error;
    }
  };

  const logout = () => {
    setJwt(null);
    localStorage.removeItem('jwt'); // Limpia el token en el logout
  };

  return (
    <AuthContext.Provider value={{ jwt, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
