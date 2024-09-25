import Link from 'next/link';
import { useCart } from '../context/CartContext';
import styles from '../styles/spectacleList.module.css';

const Spectacle = ({ id, title, date, price, image, description }) => {
  const { addToCart } = useCart();

  const handleAddToCart = () => {
    addToCart({ id, title, date, price, image, description });
  };

  return (
    <div className={styles.spectacle}>
      <img src={image} alt={title} className={styles.spectacleImage} />
      <h3 className={styles.spectacleTitle}>{title}</h3>
      <p className={styles.spectacleDate}>Date: {date}</p>
      <p className={styles.spectaclePrice}>Price: {price} €</p>
      <p className={styles.spectacleDescription}>{description}</p>

      <Link href={`/spectacle/${id}`} legacyBehavior>
        <button className={styles.button} onClick={handleAddToCart}>See more</button>
      </Link>
    </div>
  );
};

export default Spectacle;
