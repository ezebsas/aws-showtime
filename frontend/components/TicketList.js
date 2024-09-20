import Link from 'next/link';

const TicketList = ({ tickets }) => {
  return (
    <ul>
      {tickets.map(ticket => (
        <li key={ticket.id}>
          <Link href={`/ticket/${ticket.id}`}>
            <a>{ticket.name}</a>
          </Link>
        </li>
      ))}
    </ul>
  );
};

export default TicketList;
