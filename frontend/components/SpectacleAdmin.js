import { useCart } from '../context/CartContext';
import styles from '../styles/spectacleList.module.css';

const SpectacleAdmin = ({ id, title, date, price, image, description, handleDelete }) => {
  const { addToCart } = useCart();

  return (
    <div className={styles.spectacle}>
      <img src={image} alt={title} className={styles.spectacleImage} />
      <h3 className={styles.spectacleTitle}>{title}</h3>
      <p className={styles.spectacleDate}>Date: {date}</p>
      <p className={styles.spectaclePrice}>Price: {price} €</p>
      <p className={styles.spectacleDescription}>{description}</p>

      <button className={styles.deleteButton} onClick={() => handleDelete(id)}>Delete</button>
    </div>
  );
};

export default SpectacleAdmin;
