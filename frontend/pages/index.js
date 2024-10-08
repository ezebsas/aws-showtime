import { useEffect, useState } from 'react';
import Header from '../components/Header';
import SpectacleList from '../components/SpectacleList';
import '../styles/globals.css';
import axios from 'axios';
import config from '../config';
import { useAuth } from '../context/AuthContext';
import { useRouter } from 'next/router';

const Home = () => {
  const [spectacles, setSpectacles] = useState([]);

  const {jwt} = useAuth();
  const router = useRouter();

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
  }, [jwt, router]);

  return (
    <div className="container">
      <Header />
      <SpectacleList spectacles={spectacles} />
    </div>
  );
};

export default Home;
