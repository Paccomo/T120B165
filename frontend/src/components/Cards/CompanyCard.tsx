import { Link } from 'react-router-dom';

function CompanyCard() {
    const cardStyle = {
        width: '500px', // Set the desired width
        height: '500px', // Set the desired height
        display: 'flex',
        justifyContent: 'center',
        backgroundColor: "#6DA5C0",
        alignItems: 'center',
    };

    const imgStyle = {
        maxWidth: '100%',
        maxHeight: '70%', // Adjust as needed
    };

    return (
        <Link to="/companies">
            <div className="card" style={cardStyle}>
                <img src="/src/assets/img/company.svg" className="card-img-top" style={imgStyle} alt="company" />
                <div className="card-body" style={{ color: '#072E33' }}>
                    <h5 className="card-title" style={{fontFamily: 'Rubik Broken Fax'}}>Companies</h5>
                    <p className="card-text">All companies, which provide their services on this site.</p>
                </div>
            </div>
        </Link>

    );
}

export default CompanyCard;