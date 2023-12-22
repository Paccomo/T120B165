import axios from 'axios';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';

const LogoutButton = () => {

    const navigate = useNavigate();

  const handleLogout = async () => {
    try {
      // Call your server-side logout endpoint
      await axios.post('http://localhost:256/api/v1/auth/logout');
      // set tokens cookies
      Cookies.remove('access_token');
      Cookies.remove('refresh_token');

      navigate("/login");
    } catch (error) {
      console.error('Logout failed', error);
      // Handle logout failure
    }
  };

  return <button style={{ margin: '10px', backgroundColor: '#6DA5C0', borderColor: '#6DA5C0' }} onClick={handleLogout}>Logout</button>;
};

export default LogoutButton;