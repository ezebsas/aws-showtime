import { useEffect, useState } from 'react';
import SpectacleListAdmin from './SpectacleListAdmin';
import '../styles/globals.css';

const Home = () => {
  const [spectacles, setSpectacles] = useState([]);

  useEffect(() => {
    const fetchSpectacles = async () => {
      const data = [
        {
          id: '1',
          title: 'Rock Concert',
          date: '2023-12-01',
          price: 50,
          image: '/images/rock.jpeg',
          description: 'An electrifying rock concert with the best groups of the current scene.'
        },
        {
          id: '2',
          title: 'Theatre Play',
          date: '2023-11-15',
          price: 30,
          image: '/images/teatro.jpeg',
          description: 'A captivating theatre play that explores the themes of love and betrayal.'
        },
      ];
      setSpectacles(data);
    };

    fetchSpectacles();
  }, []);

  return (
    <div className="container">
      <SpectacleListAdmin spectacles={spectacles} />
    </div>
  );
};

export default Home;
