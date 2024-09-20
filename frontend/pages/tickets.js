import { useEffect, useState } from 'react';
import axios from 'axios';
import Header from '../components/Header';
import Footer from '../components/Footer';
import TicketList from '../components/TicketList';

const Tickets = () => {
  const [tickets, setTickets] = useState([]);

  useEffect(() => {
    const fetchTickets = async () => {
      const response = await axios.get('http://localhost:5000/api/tickets'); // Remplace par l'URL de ton backend
      setTickets(response.data);
    };

    fetchTickets();
  }, []);

  return (
    <div>
      <Header />
      <main>
        <h1>Tickets disponibles</h1>
        <TicketList tickets={tickets} />
      </main>
      <Footer />
    </div>
  );
};

export default Tickets;
