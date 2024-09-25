import Spectacle from './Spectacle';
import styles from '../styles/spectacleList.module.css';

const SpectacleList = ({ spectacles }) => {
  return (
    <div className={styles.spectacleList}>
      <h2>List of events</h2>
      {spectacles.map((spectacle, index) => (
        <Spectacle key={index} {...spectacle} />
      ))}
    </div>
  );
};

export default SpectacleList;
