import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import Header from '../../components/Header';
import '../../styles/globals.css';

const BuyTicket = () => {
  const router = useRouter();
  const { id } = router.query;
  const [spectacle, setSpectacle] = useState(null);

  const [quantity, setQuantity] = useState('');

  const [open, setOpen] = useState(false);
  const [error, setError] = useState(false);

  useEffect(() => {
    const fetchSpectacle = async () => {
      const data = 
        {
            id: '1',
            title: 'Rock concert',
            date: '2023-12-01',
            price: 50,
            image: '/images/rock.jpeg',
            description: 'Description of the event.',
            hour: '21:00',
            ubication: 'Estadio unico de la plata',
            duration: 3,
            quantityMax: 4,
            ticketsAvailable: 200,
        };
      setSpectacle(data);
    };

    if (id) {
      fetchSpectacle();
    }
  }, [id]);

  if (!spectacle) {
    return <div>Charging...</div>;
  }

  const handleConfirm = () => {
    setOpen(true);
  }

  const buyTicket = () => {
    try {
        router.push(`/confirm/${id}`);
    } catch {
        setError(true);
    }
  }

  return (
    <>
      <Header />
      <div className="container">
        <div className="spectacle-details">
          <img src={spectacle.image} alt={spectacle.title} className="spectacle-image" />
          <h2>{spectacle.title}</h2>
          <p>Date: {spectacle.date}</p>
          <p>Price: {spectacle.price} €</p>
          <p>Duration: {spectacle.duration}hs</p>
          <p>Ubication: {spectacle.ubication}</p>
          <p>Hour: {spectacle.hour}</p>
          <p>{spectacle.description}</p>

          <div className='form-container'>
            <div className='form-item'>
                <label for="quant">Select quantity:</label>
                <select name="quant" id="quant" onChange={(ev) => {setQuantity(ev.target.value)}}>
                    {Array.from(Array(spectacle.quantityMax)).map((o, index) => {
                        return <option key={`${o}-${index}`} value={index + 1}>{index + 1}</option>
                    })}
                </select>
            </div>
          </div>
                {open && (
                    <div className='modal'>
                        {error ? (
                            <div className='modal-container'>
                                <h3 color='red'>Something went wrong</h3>
                                <div className='modal-info'>
                                    <p>Try again later</p>
                                </div>
                            </div>
                        ):(
                            <div className='modal-container'>
                                <h2>You are about to buy {quantity} tickets</h2>
                                <div className='modal-info'>
                                    <p>Once the purchase is made, you cannot make a return. Are you sure you want to make the purchase?</p>
                                    <div className='modal-buttons'>
                                        <button onClick={()=>setOpen(false)}>Cancel</button>
                                        <button onClick={buyTicket}>Buy</button>
                                    </div>
                                </div>
                            </div>
                        )}
                    </div>
                )}
                {open && (
                    <div className='modal-back-shadow' onClick={()=>{setOpen(false)}}></div>
                )}
          <button onClick={handleConfirm}>Buy</button>
        </div>
      </div>
    </>
  );
};

export default BuyTicket;
