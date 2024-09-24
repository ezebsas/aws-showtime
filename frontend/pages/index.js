import { useEffect, useState } from 'react';
import Header from '../components/Header';
import SpectacleList from '../components/SpectacleList';
import '../styles/globals.css';
import axios from 'axios';
import config from '../config';

const Home = () => {
  const [spectacles, setSpectacles] = useState([]);

  useEffect(() => {
    const fetchSpectacles = async () => {
      const data = 
        await axios.get(`${config.url}events/list`)
      setSpectacles(data);
    };

    fetchSpectacles();
  }, []);

  return (
    <div className="container">
      <Header />
      <SpectacleList spectacles={spectacles} />
    </div>
  );
};

export default Home;
