import { useCart } from '../context/CartContext';
import '../styles/globals.css';
import Header from '../components/Header';
import Link from 'next/link'; // Import Link from next/link

const Cart = () => {
  const { cart } = useCart();

  return (
    <>
      <Header />
      <div className="container">
        <h2>My Cart</h2>
        {cart.length === 0 ? (
          <p>Your cart is empty</p>
        ) : (
          <div>
            {cart.map((item, index) => (
              <li key={index}>
                <h2>{item.name}</h2>
                <p>Date: {item.date}</p>
                <p>Status: {item.status}</p>

                {/* Iterate over eventSections to display section details */}
                <h3>Event Sections:</h3>
                <ul>
                  {item.eventSections.map((section) => (
                    <li key={section.id}>
                      <p>Section ID: {section.id}</p>
                      <p>Price: {section.price} €</p>
                      <p>Available Seats: {section.availableSeats}</p>
                    </li>
                  ))}
                </ul>
              </li>
            ))}
            <Link href={`/spectacle/${cart[0].id}`} legacyBehavior>Keep buying</Link>
          </div>
        )}
      </div>
    </>
  );
};

export default Cart;
