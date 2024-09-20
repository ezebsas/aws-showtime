// pages/index.js
import { useEffect, useState } from 'react';
import Header from '../components/Header';
import SpectacleList from '../components/SpectacleList';
import '../styles/globals.css';

const Home = () => {
  const [spectacles, setSpectacles] = useState([]);

  useEffect(() => {
    // Simuler une réponse API avec des données statiques
    const fetchSpectacles = async () => {
      const data = [
        {
          title: 'Concert de Rock',
          date: '2023-12-01',
          price: 50,
          image: '/images/rock.jpeg',
          description: 'Un concert de rock électrisant avec les meilleurs groupes de la scène actuelle.'
        },
        {
          title: 'Concert de Rock',
          date: '2023-12-01',
          price: 50,
          image: '/images/rock.jpeg',
          description: 'Un concert de rock électrisant avec les meilleurs groupes de la scène actuelle.'
        },
        {
          title: 'Pièce de Théâtre',
          date: '2023-11-15',
          price: 30,
          image: '/images/teatro.jpeg',
          description: 'Une pièce de théâtre captivante qui explore les thèmes de l\'amour et de la trahison.'
        },
        {
          title: 'Pièce de Théâtre',
          date: '2023-11-15',
          price: 30,
          image: '/images/teatro.jpeg',
          description: 'Une pièce de théâtre captivante qui explore les thèmes de l\'amour et de la trahison.'
        },
        // Ajoute d'autres spectacles ici
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
