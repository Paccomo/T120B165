import { useState } from 'react';
import axios from 'axios';
import styled from 'styled-components';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';

// Create styled components
const FormContainer = styled.form`
  display: flex;
  flex-direction: column;
  max-width: 300px;
  margin: auto;
  padding: 20px;
  border: 1px solid #0C7075;
  border-radius: 5px;
`;

const Label = styled.label`
  margin-bottom: 10px;
`;

const Input = styled.input`
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
`;

const Button = styled.button`
  background-color: #4caf50;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;

  &:hover {
    background-color: #45a049;
  }
`;

const ErrorMessage = styled.p`
  color: red;
  margin-top: 10px;
`;

const LoginForm = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e: any) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:256/api/v1/auth/authenticate', {
        email,
        password,
      });

      const { access_token, refresh_token } = response.data;

      Cookies.set('access_token', access_token);
      Cookies.set('refresh_token', refresh_token);

      navigate("/");
    } catch (error) {
      console.error('Login failed', error);
      setErrorMessage('Invalid username or password');
    }
  };

  return (
    <FormContainer onSubmit={handleLogin}>
      <Label  style={{color: 'white'}} >
        Email:
        <Input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
      </Label>
      <Label style={{color: 'white'}}>
        Password:
        <Input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      </Label>
      <Button type="submit">Login</Button>
      {errorMessage && <ErrorMessage>{errorMessage}</ErrorMessage>}
    </FormContainer>
  );
};

export default LoginForm;
