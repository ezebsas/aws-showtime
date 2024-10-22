import { useRouter } from 'next/router';
import { useState, useEffect } from 'react';
import Header from '../../components/Header';
import '../../styles/globals.css';
import { useAuth } from '../../context/AuthContext';
import axios from 'axios';
import config from '../../config';

const BuyTicket = () => {
  const { jwt } = useAuth();
  const router = useRouter();
  const { id } = router.query; // Get the id from the router query

  const [quantity, setQuantity] = useState('');
  const [open, setOpen] = useState(false);
  const [error, setError] = useState(false);
  const [venue, setVenue] = useState([]);
  const [selectedSectionId, setSelectedSectionId] = useState(null); // State to track selected section
  const [spectacle, setSpectacle] = useState(null); // Initialize spectacle as null
  const [loading, setLoading] = useState(true); // Loading state

  // Combined useEffect to fetch spectacle and venue
  useEffect(() => {
    const fetchData = async () => {
      setLoading(true); // Start loading

      if (jwt && id) {
        try {
          // Fetch spectacle
          const spectacleResponse = await axios.get(`${config.url}events/${id}`, {
            headers: {
              Authorization: `Bearer ${jwt}`,
            },
          });
          setSpectacle(spectacleResponse.data); // Set the fetched spectacle

          // Redirect if spectacle is not found
          if (!spectacleResponse.data) {
            router.push('/'); // Redirect to home if spectacle is not found
            return; // Exit the function early
          }

          // Fetch venue if spectacle is fetched successfully
          const venueResponse = await axios.get(`${config.url}venues/${spectacleResponse.data.venueId}`, {
            headers: {
              Authorization: `Bearer ${jwt}`,
            },
          });
          setVenue(venueResponse.data); // Set the fetched venue
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
  }, [jwt, id, router]); // Dependency array includes jwt and id

  // If loading, show loading state
  if (loading) {
    return <div>Loading...</div>; // Show loading state while fetching
  }

  // If spectacle is not found, return null to prevent rendering
  if (!spectacle) {
    return null; // Prevent rendering while redirecting
  }

  const handleConfirm = () => {
    if (quantity) {
      setOpen(true);
    } else {
      alert('Please select a quantity');
    }
  };

  // Function to handle ticket purchase
  const buyTicket = async () => {
    if (!selectedSectionId || !quantity) {
      alert('Please select a section and quantity.'); // Ensure section and quantity are selected
      return;
    }

    try {
      await axios.post(
        `${config.url}tickets`,
        {
          eventSection: selectedSectionId, // Use the selected section ID
          quantity: Number(quantity), // Ensure quantity is a number
        },
        {
          headers: {
            Authorization: `Bearer ${jwt}`, // Include the Authorization header here
          },
        }
      );

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
          {/* Check if the event is closed */}
          {spectacle.status === 'CLOSED' ? (
            <p>Event closed, you can&apos;t buy tickets anymore.</p>
          ) : (
            <>
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
              <button onClick={handleConfirm}>Buy tickets</button> {/* Only show this button if the event is not closed */}
            </>
          )}

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
        </div>
      </div>
    </>
  );
};

export default BuyTicket;
