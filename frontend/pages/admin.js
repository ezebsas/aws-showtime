import { useState, useEffect } from 'react';
import axios from 'axios';
import HeaderAdmin from '../components/HeaderAdmin';
import Statistics from '../components/Statistics';
import SpectacleListAdmin from '../components/SpectacleManagement';

const Admin = () => {
  const [selectedComponent, setSelectedComponent] = useState(null);

  return (
    <div>
      <HeaderAdmin setSelectedComponent={setSelectedComponent} />

      {selectedComponent === 'statistics' && <Statistics />}
      {selectedComponent === 'events' && <SpectacleListAdmin />}
    </div>
  );
};

export default Admin;
