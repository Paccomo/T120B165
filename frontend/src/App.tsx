import './App.css'
import { useState, useEffect } from 'react';
// @ts-ignore  
import { jwtDecode } from 'jwt-decode';
import { Navbar, Nav, Container } from 'react-bootstrap';
import axios from 'axios';
import Cookies from 'js-cookie';
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import LoginForm from './Auth/LoginForm';
import RegisterForm from './Auth/RegisterForm';
import LogoutButton from './Auth/LogoutBtn';
import CompanyCard from './components/Cards/CompanyCard';
import FirearmCard from './components/Cards/FirearmCard';
import InstructorCard from './components/Cards/InstructorCard';
import SRangeCard from './components/Cards/SRangeCard';
import UserCard from './components/Cards/UserCard';
import CompanyList from './Company/CompanyList';
import FirearmList from './Firearm/FirearmList';
import SRangeList from './ShootingRange/SRangeList';
import UserList from './User/UserList';
import InstructorList from './Instructor/InstructorList';
import InstructorTable from './Instructor/InstructorTable';
import SRangeTable from './ShootingRange/SRangeTable';
import CompanyTable from './Company/CompanyTable';
import FirearmTable from './Firearm/FirearmTable';
import Footer from './components/Footer';

function App() {
  const [isLoggedIn, setLoggedIn] = useState(false);

  // Add logic to check for the presence and validity of the access token
  useEffect(() => {
    const accessToken = Cookies.get('access_token');
    const refreshToken = Cookies.get('refresh_token');
    let decodedToken: any = null;

    try {
      if (accessToken) {
        decodedToken = jwtDecode(accessToken);

        // Check if the token is expired
        const isTokenExpired = decodedToken.exp < Date.now() / 1000;

        if (isTokenExpired && refreshToken) {
          // Token is expired, try to renew it
          axios.post("http://localhost:256/api/v1/auth/refresh", {},
            {
              headers: {
                Authorization: `Bearer ${refreshToken}`,
              },
            }
          )
            .then(response => {
              const { access_token, refresh_token } = response.data;

              Cookies.set('access_token', access_token);
              Cookies.set('refresh_token', refresh_token);

              // Update the isValidToken state to true
              setLoggedIn(true);
            })
            .catch(error => {
              console.error('Token renewal failed:', error);

              // Token renewal failed, set isLoggedIn to false
              setLoggedIn(false);
            });
        } else {
          // Token is not expired, set isLoggedIn to true
          setLoggedIn(true);
        }
      } else {
        // No access token, set isLoggedIn to false
        setLoggedIn(false);
      }
    } catch (error) {
      console.error('Error decoding token:', error);
    }

    // Check if the token is not expired
    const isValidToken = decodedToken ? decodedToken.exp > Date.now() / 1000 : false;

    // Use nullish coalescing operator to provide a default value of false if isValidToken is undefined
    setLoggedIn(isValidToken ?? false);
  }, []);

  return (
    <Router>

      <div className="tabs" style={{ borderTop: '2px solid #0F969C', borderBottom: '2px solid #0F969C', backgroundColor: 'rgba(15, 150, 156, 0.7)', backdropFilter: 'blur(5px)' }}>
        <Navbar bg="body-tertiary" variant="dark" expand="lg" style={{ borderTop: '2px solid #0F969C', borderBottom: '2px solid #0F969C', backgroundColor: 'rgba(15, 150, 156, 0.7)', backdropFilter: 'blur(5px)' }}>
          <Container>
            <Navbar.Brand style={{ color: '#fff', fontFamily: 'Rubik Broken Fax' }}>SRs</Navbar.Brand>
            <Navbar.Toggle aria-controls="navbar" />

            <Navbar.Collapse id="navbar">
              <Nav className="ml-auto">
                {!isLoggedIn ? (
                  <>
                    <Link to="/login" className="nav-link" style={{ color: '#05161A', margin: '10px', fontFamily: 'Rubik Broken Fax' }}>Login</Link>
                    <Link to="/register" className="nav-link" style={{ color: '#05161A', margin: '10px', fontFamily: 'Rubik Broken Fax' }}>Register</Link>
                  </>
                ) : (
                  <LogoutButton />
                )}
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>
      </div>



      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginForm />} />
        <Route path="/register" element={<RegisterForm />} />
        <Route path="/companies" element={<CompanyList />} />
        <Route path="/ranges" element={<SRangeList />} />
        <Route path="/users" element={<UserList />} />
        <Route path="/firearms" element={<FirearmList />} />
        <Route path="/instructors" element={<InstructorList />} />
        <Route path="/instructors/:instructorId" element={<InstructorTable />} />
        <Route path="/firearms/:firearmId" element={<FirearmTable />} />
        <Route path="/ranges/:rangeId" element={<SRangeTable />} />
        <Route path="/companies/:companyId" element={<CompanyTable />} />
      </Routes>
      <Footer />
    </Router>
  );
}

const HomePage = () => {
  return (
    <div className="row row-cols-1 row-cols-md-2 g-4">
      <CompanyCard />
      <FirearmCard />
      <InstructorCard />
      <SRangeCard />
      {/* <UserCard /> */}
    </div>
  );
};

export default App
