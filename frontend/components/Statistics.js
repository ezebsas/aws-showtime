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

  /*
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
  */
  useEffect(() => {
    const fetchData = async () => {
      setLoading(true); // Start loading

      if (jwt) {
        try {
          // Fetch spectacle
          let response = await axios.get(`${config.url}stats`, {
            headers: {
              Authorization: `Bearer ${jwt}`,
            },
          });
          setStats(response.data); // Set the fetched spectacle
          response = await axios.get(`${config.url}stats/hourly-sales`, {
            headers: {
              Authorization: `Bearer ${jwt}`,
            },
          });
          setHourlyRevenue(response.data); // Set the fetched spectacle
          response = await axios.get(`${config.url}stats/daily-sales`, {
            headers: {
              Authorization: `Bearer ${jwt}`,
            },
          });
          setDailyRevenue(response.data); // Set the fetched spectacle
          response = await axios.get(`${config.url}stats/weekly-sales`, {
            headers: {
              Authorization: `Bearer ${jwt}`,
            },
          });
          setWeeklyRevenue(response.data); // Set the fetched spectacle

          // Redirect if spectacle is not found
        } catch (error) {
          console.error('Error fetching data:', error);
          setError(true);
        } finally {
          setLoading(false); // Set loading to false after fetch attempt
        }
      } else {
        setLoading(false); // Set loading to false if jwt or id is not available
      }
    };

    fetchData();
  }, [jwt]); // Dependency array includes jwt and id

  return (
    <div className={styles.statistics}>
      <h2>Statistics</h2>
      {error && <p className={styles.error}>{error}</p>}
      {stats && (
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
