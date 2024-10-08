import { useRouter } from 'next/router';
import { useState } from 'react';
import Header from '../../components/Header';
import '../../styles/globals.css';
import { useCart } from '../../context/CartContext';
import { useEffect } from 'react';
import { useAuth } from '../../context/AuthContext';
import axios from 'axios';
import config from '../../config';

const BuyTicket = () => {
  const {jwt} = useAuth();
  const { cart } = useCart();
  const router = useRouter();
  const { id } = router.query; // Get the id from the router query

  // Find the spectacle in the cart using the id
  const spectacle = cart.find(item => item.id === Number(id));

  const [quantity, setQuantity] = useState('');
  const [open, setOpen] = useState(false);
  const [error, setError] = useState(false);
  const [venue, setVenue] = useState([]);
  const [selectedSectionId, setSelectedSectionId] = useState(null); // State to track selected section

  // Redirect to home if spectacle is not found
  useEffect(() => {
    if (!spectacle) {
      router.push('/'); // Redirect to home page
    }
    const fetchVenue = async () => {
      if(jwt && spectacle){
        const data = 
          await axios.get(`${config.url}venues/${spectacle.venueId}`,{
            headers: {
              Authorization: `Bearer ${jwt}`,
            }
          })
        setVenue(data.data);
      }
    };

    fetchVenue();
  }, [jwt, spectacle, router]); // Dependency array includes spectacle and router

  // If spectacle is not found, return null to prevent rendering
  if (!spectacle) {
    return null; // Prevent rendering while redirecting
  }

  const handleConfirm = () => {
    if(quantity){
      setOpen(true);
    }else{
      alert('Please select a quantity');
    }
  }

  // Function to handle ticket purchase
  const buyTicket = async () => {
    if (!selectedSectionId || !quantity) {
      alert('Please select a section and quantity.'); // Ensure section and quantity are selected
      return;
    }

    try {
      const response = await axios.post(
        `${config.url}tickets`, 
        {
          eventSection: selectedSectionId, // Use the selected section ID
          quantity: Number(quantity) // Ensure quantity is a number
        },
        {
          headers: {
            Authorization: `Bearer ${jwt}`, // Include the Authorization header here
          }
        }
      );

      console.log('Purchase successful:', response.data); // Log the response from the server
      router.push(`/confirm/${id}`); // Redirect to a confirmation page or wherever appropriate
    } catch (error) {
      console.error('Error purchasing tickets:', error);
      setError(true); // Set error state to true
    }
  };

  return (
    <>
      <Header />
      <div className="container">
        <div className="spectacle-details">
          <h2>{spectacle.name}</h2>
          <p>Lugar: {venue.name}</p>
          <p>Ciudad: {venue.city}</p>
          <p>Ubication: {venue.address}</p>
          <p>Date: {spectacle.date}</p>

          {spectacle.eventSections.map((section) => (
            <div 
              key={section.id} 
              onClick={() => setSelectedSectionId(section.id)} // Set selected section on click
              style={{
                border: selectedSectionId === section.id ? '2px solid blue' : '2px solid grey', // Change border color if selected
                cursor: 'pointer', // Change cursor to pointer
                padding: '10px', // Add some padding for better click area
                margin: '5px 0' // Add margin between sections
              }}
            >
              <p>Sección: {section.id}</p>
              <p>Asientos disponibles: {section.availableSeats}</p>
              <p>Precio: {section.price} €</p>
              
              {/* Show form-container only if this section is selected */}
              {selectedSectionId === section.id && (
                <div className='form-container'>
                  <div className='form-item'>
                    <label htmlFor="quant">Select quantity:</label>
                    <select name="quant" id="quant" onChange={(ev) => { setQuantity(ev.target.value) }}>
                      {Array.from(Array(section.availableSeats + 1)).map((o, index) => { // Add 1 to include 0 to availableSeats
                        return <option key={`${o}-${index}`} value={index}>{index}</option>; // Set value to index
                      })}
                    </select>
                  </div>
                </div>
              )}
            </div>
          ))}


          {error && (
            <div className='modal-container'>
              <h3 color='red'>Something went wrong</h3>
              <div className='modal-info'>
                <p>Try again later</p>
              </div>
            </div>
          )}
          {open && (
            <div className='modal'>
              <div className='modal-container'>
                <h2>You are about to buy {quantity} tickets</h2>
                <div className='modal-info'>
                  <p>Once the purchase is made, you cannot make a return. Are you sure you want to make the purchase?</p>
                  <div className='modal-buttons'>
                    <button onClick={() => setOpen(false)}>Cancel</button>
                    <button onClick={buyTicket}>Buy</button>
                  </div>
                </div>
              </div>
            </div>
          )}
          {open && (
            <div className='modal-back-shadow' onClick={() => { setOpen(false) }}></div>
          )}
          <button onClick={handleConfirm}>Buy tickets</button>
        </div>
      </div>
    </>
  );
};

export default BuyTicket;
