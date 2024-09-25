import { useCart } from '../context/CartContext';
import '../styles/globals.css';
import Header from '../components/Header';

const Cart = () => {
  const { cart } = useCart();

  return (
    <>
      <Header />
      <div className="container">
        <h2>My cart</h2>
        {cart.length === 0 ? (
          <p>Your cart is empty</p>
        ) : (
          <ul>
            {cart.map((item, index) => (
              <li key={index}>
                <h3>{item.title}</h3>
                <p>Date: {item.date}</p>
                <p>Price: {item.price} €</p>
                <img src={item.image} alt={item.title} style={{ maxWidth: '100px' }} />
              </li>
            ))}
          </ul>
        )}
      </div>
    </>
  );
};

export default Cart;
