import { useState, useEffect } from 'react';
import axios from 'axios';
import styles from '../styles/statistics.module.css';
import config from '../config';
import { useAuth } from '../context/AuthContext';

const Statistics = () => {
  const { jwt } = useAuth();
  const [stats, setStats] = useState(null);
  const [hourlyRevenue, setHourlyRevenue] = useState(null);
  const [dailyRevenue, setDailyRevenue] = useState(null);
  const [weeklyRevenue, setWeeklyRevenue] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true); // Loading state

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true); // Start loading

      if (jwt) {
        try {
          // Fetch statistics
          let response = await axios.get(`${config.url}stats`, {
            headers: {
              Authorization: `Bearer ${jwt}`,
            },
          });
          setStats(response.data); // Set the fetched statistics

          // Fetch hourly revenue
          response = await axios.get(`${config.url}stats/hourly-sales`, {
            headers: {
              Authorization: `Bearer ${jwt}`,
            },
          });
          setHourlyRevenue(response.data); // Set the fetched hourly revenue

          // Fetch daily revenue
          response = await axios.get(`${config.url}stats/daily-sales`, {
            headers: {
              Authorization: `Bearer ${jwt}`,
            },
          });
          setDailyRevenue(response.data); // Set the fetched daily revenue

          // Fetch weekly revenue
          response = await axios.get(`${config.url}stats/weekly-sales`, {
            headers: {
              Authorization: `Bearer ${jwt}`,
            },
          });
          setWeeklyRevenue(response.data); // Set the fetched weekly revenue

        } catch (error) {
          console.error('Error fetching data:', error);
          setError(error.message); // Set the error message
        } finally {
          setLoading(false); // Set loading to false after fetch attempt
        }
      } else {
        setLoading(false); // Set loading to false if jwt is not available
      }
    };

    fetchData();
  }, [jwt]); // Dependency array includes jwt

  return (
    <div className={styles.statistics}>
      <h2>Statistics</h2>
      {loading && <p>Loading...</p>} {/* Show loading message */}
      {error && <p className={styles.error}>{error}</p>}
      {!loading && stats && (
        <>
          <p>Unique users: {stats.uniqueUsers}</p>
          <p>Total events: {stats.totalEvents}</p>
          <p>Total tickets sold: {stats.totalTicketsSold}</p>
          <p>Total revenue: {stats.totalRevenue} $</p>
          <p>Hourly revenue: {hourlyRevenue.value} $</p>
          <p>Daily revenue: {dailyRevenue.value} $</p>
          <p>Weekly revenue: {weeklyRevenue.value} $</p>
        </>
      )}
    </div>
  );
};

export default Statistics;
