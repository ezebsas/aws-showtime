import { useEffect, useState } from 'react';
import { useCart } from '../context/CartContext';
import '../styles/globals.css';
import Header from '../components/Header';
import axios from 'axios';
import config from '../config'; // Adjust the import path as necessary
import { useAuth } from '../context/AuthContext';

const Bookings = () => {
  const { jwt } = useAuth(); // Get JWT from authentication context
  const [tickets, setTickets] = useState([]); // State to hold tickets
  const [error, setError] = useState(false); // State to handle errors

  useEffect(() => {
    const fetchTickets = async () => {
      if (jwt) {
        try {
          const response = await axios.get(`${config.url}tickets`, {
            headers: {
              Authorization: `Bearer ${jwt}`, // Include the JWT in the headers
            },
          });
          setTickets(response.data._embedded.tickets); 
        } catch (error) {
          console.error('Error fetching tickets:', error);
          setError(true);
        }
      }
    };

    fetchTickets();
  }, [jwt]); // Dependency array includes jwt

  return (
    <>
      <Header />
      <div className="container">
        <h2>My Bookings</h2>
        {tickets.length === 0 ? (
          <p>You have no bookings yet.</p>
        ) : (
          <ul>
            {tickets.map((item, index) => (
              <li key={index}>
                <p>Ticket id: {item.id}</p>
                <p>Price: {item.total} €</p>
                <p>Quenatity: {item.quantity} €</p>
              </li>
            ))}
          </ul>
        )}
        {error && <p>There was an error fetching your tickets. Please try again later.</p>}
      </div>
    </>
  );
};

export default Bookings;
