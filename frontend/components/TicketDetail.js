const TicketDetail = ({ ticket }) => {
    return (
      <div>
        <h2>{ticket.name}</h2>
        <p>{ticket.description}</p>
        <p>Prix: {ticket.price} €</p>
        {/* Ajoute d'autres détails du ticket ici */}
      </div>
    );
  };
  
  export default TicketDetail;
  