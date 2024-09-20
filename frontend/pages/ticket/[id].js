// pages/ticket/[id].js
import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import axios from 'axios';
import Header from '../../components/Header';
import Footer from '../../components/Footer';
import TicketDetail from '../../components/TicketDetail';

const Ticket = () => {
  const router = useRouter();
  const { id } = router.query;
  const [ticket, setTicket] = useState(null);

  useEffect(() => {
    if (id) {
      const fetchTicket = async () => {
        const response = await axios.get(`http://localhost:5000/api/tickets/${id}`); // Remplace par l'URL de ton backend
        setTicket(response.data);
      };

      fetchTicket();
    }
  }, [id]);

  if (!ticket) {
    return <div>Chargement...</div>;
  }

  return (
    <div>
      <Header />
      <main>
        <TicketDetail ticket={ticket} />
      </main>
      <Footer />
    </div>
  );
};

export default Ticket;
