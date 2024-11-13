import Image from 'next/image';

const SpectacleItem = ({ spectacle }) => {
    return (
      <div className="spectacle-item">
        <Image 
          src={spectacle.image} 
          alt={spectacle.title} 
          className="spectacle-image" 
          width={500}
          height={300}
          layout="responsive"
        />
        <div className="spectacle-info">
          <h2 className="spectacle-title">{spectacle.title}</h2>
          <p className="spectacle-description">{spectacle.description}</p>
          <p className="spectacle-price">Price: {spectacle.price} €</p>
          <p className="spectacle-location">Place: {spectacle.location}</p>
          <p className="spectacle-date">Date: {spectacle.date}</p>
        </div>
      </div>
    );
  };
  
  export default SpectacleItem;
  