import { useRouter } from 'next/router';
import { useCart } from '../../context/CartContext';
import { useEffect, useState } from 'react';
import Header from '../../components/Header';
import '../../styles/globals.css';
import { useAuth } from '../../context/AuthContext';
import axios from 'axios';
import config from '../../config';

const Confirmed = () => {
  const { jwt } = useAuth();
  const { cart } = useCart();
  const router = useRouter();
  const { id } = router.query;
  const [purchase, setPurchase] = useState(null);
  const [ticket, setTicket] = useState(null); 
  const [loading, setLoading] = useState(true); // Loading state
  const [error, setError] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true); // Start loading

      if (jwt && id) {
        try {
          // Fetch spectacle
          const ticketResponse = await axios.get(`${config.url}tickets/${id}`, {
            headers: {
              Authorization: `Bearer ${jwt}`,
            },
          });
          setTicket(ticketResponse.data); // Set the fetched spectacle

          // Redirect if spectacle is not found
          if (!ticketResponse.data) {
            router.push('/'); // Redirect to home if spectacle is not found
            return; // Exit the function early
          }

          // Fetch venue if spectacle is fetched successfully
          const purchaseResponse = await axios.get(`${config.url}events/${cart[0].id}`, {
            headers: {
              Authorization: `Bearer ${jwt}`,
            },
          });
          purchaseResponse.data.date = new Date(purchaseResponse.data.date).toLocaleString(); // Format date
          setPurchase(purchaseResponse.data); // Set the fetched venue
        } catch (error) {
          console.error('Error fetching data:', error);
          setError(true);
        } finally {
          setLoading(false); // Set loading to false after fetch attempt
        }
      } else {
        setLoading(false); // Set loading to false if jwt or id is not available
      }
    };

    fetchData();
  }, [jwt, id, cart, router]); // Include cart in the dependency array

  if (loading) {
    return <div>Loading...</div>; // Show loading state
  }

  if (error) {
    return <div>Error fetching data. Please try again later.</div>; // Show error state
  }

  if (!purchase) {
    return <div>Charging...</div>; // Fallback if purchase is not available
  }

  return (
    <>
      <Header />
      <div className="container">
        <div className="purchase-details">
          <h2>Purchase confirmed for &quot;{purchase.name}&quot;</h2>
          <p style={{fontWeight: 600}}>Congratulations, you have purchased your tickets for {purchase.name}</p>

          <hr />

          <p>Date: {purchase.date}</p>
          <p>Ubication: {ticket.eventSectionId}</p>
          
          <hr />

          <div className='purchase-shower'>
            <p>Quant</p>
            <p>Title</p>
            
            <p>{ticket.quantity}</p>
            <p>Ticket for {purchase.name}</p>
          </div>

          <div className='modal-buttons'>
            <button onClick={() => { router.push('/'); }}>Back home</button>
            <button onClick={() => { router.push('/bookings'); }}>Go to my tickets</button>
          </div>

        </div>
      </div>
    </>
  );
};

export default Confirmed;
