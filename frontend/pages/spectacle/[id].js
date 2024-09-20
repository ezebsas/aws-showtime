import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import Header from '../../components/Header';
import '../../styles/globals.css';

const SpectaclePage = () => {
  const router = useRouter();
  const { id } = router.query;
  const [spectacle, setSpectacle] = useState(null);

  useEffect(() => {
    const fetchSpectacle = async () => {
      const data = [
        {
          id: '1',
          title: 'Rock concert',
          date: '2023-12-01',
          price: 50,
          image: '/images/rock.jpeg',
          description: 'Description of the event.'
        },
        {
          id: '2',
          title: 'Theatre Play',
          date: '2023-11-15',
          price: 30,
          image: '/images/teatro.jpeg',
          description: 'Description of the event.'
        },
      ];
      const selectedSpectacle = data.find(item => item.id === id);
      setSpectacle(selectedSpectacle);
    };

    if (id) {
      fetchSpectacle();
    }
  }, [id]);

  if (!spectacle) {
    return <div>Charging...</div>;
  }

  return (
    <div className="container">
      <Header />
      <div className="spectacle-details">
        <img src={spectacle.image} alt={spectacle.title} className="spectacle-image" />
        <h2>{spectacle.title}</h2>
        <p>Date: {spectacle.date}</p>
        <p>Price: {spectacle.price} €</p>
        <p>{spectacle.description}</p>
        <button>Add to cart</button>
      </div>
    </div>
  );
};

export default SpectaclePage;
