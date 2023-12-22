import { Link } from 'react-router-dom';

function SRangeCard() {
    const cardStyle = {
        width: '500px', // Set the desired width
        height: '500px', // Set the desired height
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: "#6DA5C0",
      };
    
      const imgStyle = {
        maxWidth: '100%',
        maxHeight: '70%', // Adjust as needed
      };

    return (
        <Link to="/ranges">
            <div className="card" style={cardStyle}>
                <img src="/src/assets/img/target.svg" className="card-img-top"  style={imgStyle} alt="shooting ranges"></img>
                <div className="card-body" style={{ color: '#072E33' }}>
                    <h5 className="card-title" style={{fontFamily: 'Rubik Broken Fax'}}>Shooting ranges</h5>
                    <p className="card-text">List of currently active shooting ranges registered in this system.</p>
                </div>
            </div>
        </Link>
    );
}

export default SRangeCard;