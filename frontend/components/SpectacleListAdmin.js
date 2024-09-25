import SpectacleAdmin from './SpectacleAdmin';
import styles from '../styles/spectacleList.module.css';

const SpectacleListAdmin = ({ spectacles, handleDelete }) => {
  return (
    <div className={styles.spectacleList}>
      <h2>List of spectacles</h2>
      {spectacles.map((spectacle, index) => (
        <SpectacleAdmin key={index} {...spectacle} handleDelete={handleDelete} />
      ))}
    </div>
  );
};


export default SpectacleListAdmin;
