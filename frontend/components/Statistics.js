import { useState, useEffect } from 'react';
import axios from 'axios';
import styles from '../styles/statistics.module.css';
import config from '../config';

const Statistics = () => {
  const [stats, setStats] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch statistics data from API or local storage
    const fetchStats = async () => {
      try {
        const response = await axios.get(`${config.url}events/statistics`);
        setStats(response.data);
      } catch (error) {
        setError(error.message);
      }
    };
    fetchStats();
  }, []);

  return (
    <div className={styles.statistics}>
      <h2>Statistics</h2>
      {error && <p className={styles.error}>{error}</p>}
      {stats && (
        <>
          <p>Total number of s: {stats.totals}</p>
          <p>Total number of users: {stats.totalUsers}</p>
          <p>Total revenue: {stats.totalRevenue} €</p>
        </>
      )}
    </div>
  );
};

export default Statistics;
