const SpectacleItem = ({ spectacle }) => {
    return (
      <div className="spectacle-item">
        <img src={spectacle.image} alt={spectacle.title} className="spectacle-image" />
        <div className="spectacle-info">
          <h2 className="spectacle-title">{spectacle.title}</h2>
          <p className="spectacle-description">{spectacle.description}</p>
          <p className="spectacle-price">Prix: {spectacle.price} €</p>
          <p className="spectacle-location">Lieu: {spectacle.location}</p>
          <p className="spectacle-date">Date: {spectacle.date}</p>
        </div>
      </div>
    );
  };
  
  export default SpectacleItem;
  