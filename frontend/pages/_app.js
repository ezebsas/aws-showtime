import { AuthProvider } from '../context/AuthContext';
import { CartProvider } from '../context/CartContext';
import { UserProvider } from '../context/UserContext';
import '../styles/globals.css';

function MyApp({ Component, pageProps }) {
  return (
    <AuthProvider>
      <UserProvider>
        <CartProvider>
          <Component {...pageProps} />
        </CartProvider>
      </UserProvider>
    </AuthProvider>
  );
}

export default MyApp;
