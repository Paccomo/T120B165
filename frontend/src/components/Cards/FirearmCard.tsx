import { Link } from 'react-router-dom';

function FirearmCard() {
    const cardStyle = {
        width: '500px', // Set the desired width
        height: '500px', // Set the desired height
        display: 'flex',
        backgroundColor: "#6DA5C0",
        justifyContent: 'center',
        alignItems: 'center',
      };
    
      const imgStyle = {
        maxWidth: '100%',
        maxHeight: '70%', // Adjust as needed
      };

    return (
        <Link to="/firearms">
            <div className="card" style={cardStyle}>
                <img src="/src/assets/img/taurus.svg" className="card-img-top" style={imgStyle}  alt="firearm"></img>
                <div className="card-body" style={{ color: '#072E33' }}>
                    <h5 className="card-title" style={{fontFamily: 'Rubik Broken Fax'}}>Firearms</h5>
                    <p className="card-text">All currently rented firearms with their respective shooting ranges.</p>
                </div>
            </div>
        </Link>
    );
}

export default FirearmCard;