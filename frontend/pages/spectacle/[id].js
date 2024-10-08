import { useRouter } from 'next/router';
import Link from 'next/link';
import { useEffect, useState } from 'react';
import Header from '../../components/Header';
import '../../styles/globals.css';
import axios from 'axios';
import config from '../../config'; // Adjust the path based on the actual location
import { useAuth } from '../../context/AuthContext'; // Ensure this is the correct path

const SpectaclePage = () => {
  const [spectacle, setSpectacle] = useState(null);
  const { jwt } = useAuth(); // Retrieve jwt from useAuth
  const router = useRouter();
  const { id } = router.query; // Get the id from the router query

  useEffect(() => {
    if (jwt && id) {
      const fetchSpectacle = async () => {
        try {
          const response = await axios.get(`${config.url}events/${id}`, {
            headers: {
              Authorization: `Bearer ${jwt}`,
            }
          });
          setSpectacle(response.data); // Set the fetched spectacle
        } catch (error) {
          console.error('Error fetching spectacle:', error);
        }
      };

      fetchSpectacle();
    }
  }, [jwt, id, router]); // Add id to the dependency array

  if (!spectacle) {
    return <div>Loading...</div>; // Show loading state while fetching
  }

  return (
    <>
      <Header />
      <div className="container">
        <div className="spectacle-details">
          <h2>{spectacle.name}</h2> {/* Display the name */}
          <p>Date: {spectacle.date}</p>
          <p>Status: {spectacle.status}</p> {/* Display the status */}
        </div>
        <Link href={`/buy-ticket/${id}`} legacyBehavior>
          <button>Buy ticket</button>
        </Link>
      </div>
    </>
  );
};

export default SpectaclePage;
