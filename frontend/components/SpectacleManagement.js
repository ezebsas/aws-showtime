import { useEffect, useState } from 'react';
import SpectacleListAdmin from './SpectacleListAdmin';
import '../styles/globals.css';

const Home = () => {
  const [spectacles, setSpectacles] = useState([]);
  const [newSpectacle, setNewSpectacle] = useState({
    title: '',
    date: '',
    price: '',
    image: '',
    description: ''
  });

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

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewSpectacle((prevSpectacle) => ({
      ...prevSpectacle,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const updatedSpectacle = {
      ...newSpectacle,
      id: Math.random().toString(36).substr(2, 9),
      price: Number(newSpectacle.price)
    };

    setSpectacles((prevSpectacles) => [...prevSpectacles, updatedSpectacle]);

    setNewSpectacle({
      title: '',
      date: '',
      price: '',
      image: '',
      description: ''
    });
  };

  return (
      <div className="container">
        <h1 className="title">Add New Spectacle</h1>

        <form className="spectacle-form" onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Title:</label>
            <input
                type="text"
                name="title"
                value={newSpectacle.title}
                onChange={handleInputChange}
                required
            />
          </div>
          <div className="form-group">
            <label>Date:</label>
            <input
                type="date"
                name="date"
                value={newSpectacle.date}
                onChange={handleInputChange}
                required
            />
          </div>
          <div className="form-group">
            <label>Price:</label>
            <input
                type="number"
                name="price"
                value={newSpectacle.price}
                onChange={handleInputChange}
                required
            />
          </div>
          <div className="form-group">
            <label>Image URL:</label>
            <input
                type="text"
                name="image"
                value={newSpectacle.image}
                onChange={handleInputChange}
                required
            />
          </div>
          <div className="form-group">
            <label>Description:</label>
            <textarea
                name="description"
                value={newSpectacle.description}
                onChange={handleInputChange}
                required
            />
          </div>
          <button type="submit" className="submit-button">Add Spectacle</button>
        </form>

        <h1 className="subtitle">Delete Spectacle</h1>
        <SpectacleListAdmin spectacles={spectacles} />
      </div>
  );
};

export default Home;
