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

  return (
    <>
      <Header />
      <div className="container">
        <div className="purchase-details">
          <h2>Purchase confirmed for {purchase.title}</h2>
          <p style={{fontWeight: 600}}>Congratulations, you have purchased your tickets for {purchase.title}</p>

          <hr></hr>

          <p>Date: {purchase.date}</p>
          <p>Ubication: {purchase.ubication}</p>
          <p>Hour: {purchase.hour}</p>

          <hr></hr>

          <div className='purchase-shower'>
            <p>Quant</p>
            <p>Title</p>
            
            <p>{purchase.quantity}</p>
            <p>Ticket for {purchase.title}</p>
          </div>

          <div className='modal-buttons'>
            <button onClick={()=>{router.push('/')}}>Back home</button>
            <button onClick={()=>{router.push('/tickets')}}>Go to my tickets</button>
          </div>

        </div>
      </div>
    </>
  );
};

export default Confirmed;
