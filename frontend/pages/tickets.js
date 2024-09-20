// import { useEffect, useState } from 'react';
// import axios from 'axios';
// import TicketList from '../components/TicketList';

// const Tickets = () => {
//   const [tickets, setTickets] = useState([]);

//   useEffect(() => {
//     const fetchTickets = async () => {
//       // const response = await axios.get('http://localhost:5000/api/tickets');
//       setTickets(response.data);
//     };

//     fetchTickets();
//   }, []);

//   return (
//     <div>
//       {/* <Header /> */}
//       <main>
//         <h1>Tickets availables</h1>
//         <TicketList tickets={tickets} />
//       </main>
//     </div>
//   );
// };

// export default Tickets;
