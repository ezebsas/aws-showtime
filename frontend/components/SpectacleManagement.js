import { useEffect, useState } from 'react';
import SpectacleListAdmin from './SpectacleListAdmin';
import '../styles/globals.css';
import axios from 'axios';
import config from '../config';

const Home = () => {
  const [spectacles, setSpectacles] = useState([]);

  const closeEvent = async (eventId) => {
    await axios.put(`${config.url}/events/${eventId}/close`)
    refetch();
  }

  const fetchSpectacles = async () => {
    const data = 
      await axios.get(`${config.url}events/list`)
    setSpectacles(data);
  };

  useEffect(() => {
    fetchSpectacles();
  }, []);

  const refetch = () => {
    fetchSpectacles();
  }

  return (
    <div className="container">
      <SpectacleListAdmin 
        spectacles={spectacles}
        handleDelete={closeEvent}
     />
    </div>
  );
};

export default Home;
