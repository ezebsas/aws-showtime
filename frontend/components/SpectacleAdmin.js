import Image from 'next/image';
import styles from '../styles/spectacleList.module.css';

const SpectacleAdmin = ({ id, title, date, price, image, description, handleDelete }) => {

  return (
    <div className={styles.spectacle}>
      <Image 
        src={image} 
        alt={title} 
        className={styles.spectacleImage} 
        width={500} 
        height={300} 
        layout="responsive" 
      />
      <h3 className={styles.spectacleTitle}>{title}</h3>
      <p className={styles.spectacleDate}>Date: {date}</p>
      <p className={styles.spectaclePrice}>Price: {price} €</p>
      <p className={styles.spectacleDescription}>{description}</p>

      <button className={styles.deleteButton} onClick={() => handleDelete(id)}>Delete</button>
    </div>
  );
};

export default SpectacleAdmin;
