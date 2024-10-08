import { useEffect, useState } from 'react';
import Header from '../components/Header';
import SpectacleList from '../components/SpectacleList';
import '../styles/globals.css';
import axios from 'axios';
import config from '../config';
import { useAuth } from '../context/AuthContext';

const Home = () => {
  const [spectacles, setSpectacles] = useState([]);

  const {jwt} = useAuth();

  useEffect(() => {
    const fetchSpectacles = async () => {
      if(jwt){
        const data = 
          await axios.get(`${config.url}events`,{
            headers: {
              Authorization: `Bearer ${jwt}`,
            }
          })
        setSpectacles(data.data._embedded.events);
      }
    };

    fetchSpectacles();
  }, [jwt]);

  return (
    <div className="container">
      <Header />
      <SpectacleList spectacles={spectacles} />
    </div>
  );
};

export default Home;
