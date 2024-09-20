// components/Spectacle.js
import styles from './SpectacleList.module.css';

const Spectacle = ({ title, date, price, image, description }) => {
  return (
    <div className={styles.spectacle}>
      <img src={image} alt={title} className={styles.spectacleImage} />
      <h3 className={styles.spectacleTitle}>{title}</h3>
      <p className={styles.spectacleDate}>Date: {date}</p>
      <p className={styles.spectaclePrice}>Prix: {price} €</p>
      <p className={styles.spectacleDescription}>{description}</p>
      <button>Acheter</button>
    </div>
  );
};

export default Spectacle;
