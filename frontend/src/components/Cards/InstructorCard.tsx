import { Link } from 'react-router-dom';

function InstructorCard() {
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
        <Link to="/Instructors">
            <div className="card" style={cardStyle}>
                <img src="/src/assets/img/instructor.svg" className="card-img-top" style={imgStyle} alt="Instructor"></img>
                <div className="card-body" style={{ color: '#072E33' }}>
                    <h5 className="card-title" style={{fontFamily: 'Rubik Broken Fax'}}>Instructors</h5>
                    <p className="card-text">All currently employed instructors with their respective shooting ranges.</p>
                </div>
            </div>
        </Link>
    );
}

export default InstructorCard;