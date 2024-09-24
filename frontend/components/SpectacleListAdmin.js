import SpectacleAdmin from './SpectacleAdmin';
import styles from '../styles/spectacleList.module.css';

const SpectacleListAdmin = ({ spectacles, handleDelete }) => {
  return (
    <div className={styles.spectacleList}>
      <h2>List of spectacles</h2>
      {spectacles ? (
        <p>No events available</p>
      ):(
        spectacles.map((spectacle, index) => (
          <SpectacleAdmin key={index} {...spectacle} handleDelete={() => handleDelete(speactacle.id)} />
        ))
      )}
    </div>
  );
};


export default SpectacleListAdmin;
