// components/SpectacleList.js
import Spectacle from './Spectacle';
import styles from './SpectacleList.module.css';

const SpectacleList = ({ spectacles }) => {
  return (
    <div className={styles.spectacleList}>
      <h2>Liste des spectacles</h2>
      {spectacles.map((spectacle, index) => (
        <Spectacle key={index} {...spectacle} />
      ))}
    </div>
  );
};

export default SpectacleList;
