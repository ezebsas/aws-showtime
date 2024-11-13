import { useState } from 'react';
import HeaderAdmin from '../components/HeaderAdmin';
import Statistics from '../components/Statistics';
import SpectacleListAdmin from '../components/SpectacleManagement';

const Admin = () => {
  const [selectedComponent, setSelectedComponent] = useState(null);

  return (
    <div>
      <HeaderAdmin setSelectedComponent={setSelectedComponent} />
      <Statistics />
    </div>
  );
};

export default Admin;
