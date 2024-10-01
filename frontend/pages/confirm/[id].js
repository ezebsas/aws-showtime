import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import Header from '../../components/Header';
import '../../styles/globals.css';

const Confirmed = () => {
  const router = useRouter();
  const { id } = router.query;
  const [purchase, setPurchase] = useState(null);

  useEffect(() => {
    const fetchPurchase = async () => {
      const data = 
        {
            id: '1',
            title: 'Rock concert',
            date: '2023-12-01',
            hour: '21:00',
            ubication: 'Estadio unico de la plata',
            quantity: 4,
        };
      setPurchase(data);
    };

    if (id) {
      fetchPurchase();
    }
  }, [id]);

  if (!purchase) {
    return <div>Charging...</div>;
  }

  const handleConfirm = () => {
    setOpen(true);
  }

  const buyTicket = () => {
    try {
        router.push('/confirm');
    } catch {

    }
  }

  return (
    <>
      <Header />
      <div className="container">
        <div className="purchase-details">
          <h2>Compra confirmada para {purchase.title}</h2>
          <p style={{fontWeight: 600}}>Felicitaciones, has adquirido tus tickets para {purchase.title}</p>

          <hr></hr>

          <p>Date: {purchase.date}</p>
          <p>Ubication: {purchase.ubication}</p>
          <p>Hour: {purchase.hour}</p>

          <hr></hr>

          <div className='purchase-shower'>
            <p>Quant</p>
            <p>Title</p>
            
            <p>{purchase.quantity}</p>
            <p>Ticket para {purchase.title}</p>
          </div>

          <div className='modal-buttons'>
            <button onClick={()=>{router.push('/')}}>Volver al inicio</button>
            <button onClick={()=>{router.push('/tickets')}}>Ver mis tickets</button>
          </div>

        </div>
      </div>
    </>
  );
};

export default Confirmed;
