// pages/ticket/[id].js
import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import axios from 'axios';
import Header from '../../components/Header';
import TicketDetail from '../../components/TicketDetail';

const Ticket = () => {
  const router = useRouter();
  const { id } = router.query;
  const [ticket, setTicket] = useState(null);

  useEffect(() => {
    if (id) {
      const fetchTicket = async () => {
        const response = await axios.get(`http://localhost:5000/api/tickets/${id}`);
        setTicket(response.data);
      };

      fetchTicket();
    }
  }, [id]);

  if (!ticket) {
    return <div>Charging...</div>;
  }

  return (
    <div>
      <Header />
      <main>
        <TicketDetail ticket={ticket} />
      </main>
    </div>
  );
};

export default Ticket;