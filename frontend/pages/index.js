import { useEffect, useState } from 'react';
import Header from '../components/Header';
import SpectacleList from '../components/SpectacleList';
import '../styles/globals.css';

const Home = () => {
  const [spectacles, setSpectacles] = useState([]);

  useEffect(() => {
    const fetchSpectacles = async () => {
      const data = [
        {
          id: '1',
          title: 'Rock concert',
          date: '2023-12-01',
          price: 50,
          image: '/images/rock.jpeg',
          description: 'Event description.'
        },
        {
          id: '2',
          title: 'Theatre Play',
          date: '2023-11-15',
          price: 30,
          image: '/images/teatro.jpeg',
          description: 'Event description.'
        },
      ];
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
