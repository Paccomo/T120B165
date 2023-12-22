import { useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
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

const RegistrationForm = () => {
  const [name, setName] = useState('');
  const [surname, setSurname] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const navigate = useNavigate();

  const handleRegister = async (e: any) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:256/api/v1/auth/register', {
        name,
        surname,
        email,
        password
      });

      navigate("/");
    } catch (error) {
      console.error('Registration failed', error);
    }
  };

  return (
    <FormContainer onSubmit={handleRegister}>
      <Label style={{color: 'white'}}>
        Name:
        <Input type="text" value={name} onChange={(e) => setName(e.target.value)} />
      </Label>
      <Label style={{color: 'white'}}>
        Surname:
        <Input type="text" value={surname} onChange={(e) => setSurname(e.target.value)} />
      </Label>
      <Label style={{color: 'white'}}>
        Email:
        <Input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
      </Label>
      <Label style={{color: 'white'}}>
        Password:
        <Input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      </Label>
      <Button type="submit">Register</Button>
    </FormContainer>
  );
};

export default RegistrationForm;
