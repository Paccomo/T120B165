// @ts-ignore  
import { jwtDecode } from 'jwt-decode';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const CompanyContainer = styled.div`
max-width: 600px;
margin: 5px auto;
padding: 20px;
background-color: #0c7075;
border-radius: 8px;
box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
`;

const CompanyHeader = styled.h1`
color: white;
margin: 10px
text-align: center;
font-size: 22px;
`;

const CompanyItem = styled.li`
list-style-type: none;
margin: 10px 0;
padding: 10px;
background-color: #05161A;
border: 1px solid #ddd;
border-radius: 4px;
box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
display: flex;
justify-content: space-between;
align-items: center;
`;

const DeleteButton = styled.button`
  background-color: #ff6961;
  color: #fff;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  align-items: right;
`;

const NewCompanyButton = styled.button`
background-color: #4caf50;
color: white;
border: none;
margin: 10px
padding: 20px;
border-radius: 4px;
cursor: pointer;

&:hover {
  background-color: #45a049;
}
`;

const ModalContainer = styled.div`
background-color: #70d6d1;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  max-width: 400px;
  width: 100%;
`;

const EditButtonContainer = styled.div`
  display: flex;
  justify-content: right;
`;

const EditButton = styled.button`
  background-color: #2196f3;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  margin-left: 10px;

  &:hover {
    background-color: #0b7dda;
  }
`;

const CreateCompanyButton = styled.button`
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 10px;

  &:hover {
    background-color: #45a049;
  }
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

const ErrorMessage = styled.div`
  color: #ff0000;
  margin-top: 10px;
`;

const List = styled.ul`
  list-style: none;
  padding: 0;
`;

const CompanyList = () => {
    const [companies, setCompanies] = useState([]);
    const [showCreateCompanyModal, setShowCreateCompanyModal] = useState(false);
    const [showEditCompanyModal, setShowEditCompanyModal] = useState(false);
    const [name, setCompanyName] = useState('');
    const [address, setCompanyAddress] = useState('');
    const [city, setCompanyCity] = useState('');
    const [phoneNumber, setCompanyPhoneNumber] = useState('');
    const [postalCode, setCompanyPostalCode] = useState('');
    const [website, setCompanyWebsite] = useState('');
    const [email, setCompanyEmail] = useState('');
    const [editCompanyId, setEditCompanyId] = useState(null);
    const [editError, setEditError] = useState<string | null>(null);
    const [createError, setCreateError] = useState<string | null>(null);

    const fetchCompanies = async () => {
        try {
            const response = await axios.get('http://localhost:256/api/v1/companies', {});
            setCompanies(response.data);
        } catch (error) {
            console.error('Error fetching companies:', error);
        }
    };

    useEffect(() => {
        fetchCompanies();
    }, []); // Run this effect only once on component mount

    const refreshToken = () => {
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

                            return Cookies.get('access_token');
                        })
                        .catch(error => {
                            console.error('Token renewal failed:', error);
                            return null;
                        });
                } else {
                    return accessToken;
                }
            } else {
                return null;
            }
        } catch (error) {
            console.error('Error decoding token:', error);
        }
    }

    const handleDelete = async (companyId: any) => {
        const token = refreshToken();
        try {
            await axios.delete(`http://localhost:256/api/v1/companies/${companyId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            // Update the local state by removing the deleted company
            setCompanies(prevCompanies =>
                prevCompanies.filter((company: any) => company.id !== companyId)
            );
            // Clear any previous error messages
            setEditError(null);
        } catch (error: any) {
            if (error.response && error.response.status === 403) {
                console.error('Insufficient privileges to delete the company:', error);
                setEditError('You do not have sufficient privileges to delete this company.');
            } else if (error.response && error.response.status === 404) {
                console.error('Impossible to delete non-existing company:', error);
                setEditError("You can't delete company, which doesn't exist.");
            } else {
                console.error('Error deleting compnay:', error);
                // Display a generic error message for other errors
                setEditError('An error occurred while deleting the company. Please try again later.');
            }
        }
    };

    const openCreateCompanyModal = () => {
        setShowCreateCompanyModal(true);
    };

    const closeCreateCompanyModal = () => {
        setShowCreateCompanyModal(false);
    };

    const handleCreateCompany = async (e: any) => {
        e.preventDefault();
        const token = refreshToken();
        console.log(token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        await axios.post('http://localhost:256/api/v1/companies', {
            name: name,
            address: address,
            city: city,
            phoneNumber: phoneNumber,
            postalCode: postalCode,
            website: website,
            email: email,
            fk_user: 4
        })
            .then(response => {
                console.log('Company created:', response.data);
                closeCreateCompanyModal();
            })
            .catch(error => {
                if (error.response && error.response.status === 403) {
                    console.error('Insufficient privileges to create the company:', error);
                    setCreateError('You do not have sufficient privileges to create this company.');
                } else if (error.response && error.response.status === 422) {
                    console.error('Invalid company information:', error);
                    setCreateError('Entered data for company is invalid.');
                } else {
                    console.error('Error creating company:', error);
                    setCreateError('An error occurred while creating the company. Please check your input and try again.');
                }
            });
    }

    const openEditCompanyModal = (companyId: any, companyName: any, companyAddress: any, companyCity: any, companyPhone: any, companyPostal: any, companyWeb: any, companyMail: any) => {
        setEditCompanyId(companyId);
        setCompanyName(companyName);
        setCompanyAddress(companyAddress);
        setCompanyCity(companyCity);
        setCompanyPhoneNumber(companyPhone);
        setCompanyPostalCode(companyPostal);
        setCompanyWebsite(companyWeb);
        setCompanyEmail(companyMail);
        setShowEditCompanyModal(true);
    };

    const closeEditCompanyModal = () => {
        setEditCompanyId(null);
        setShowEditCompanyModal(false);
    };

    const handleEditCompany = async (e: any) => {
        e.preventDefault();
        const token = refreshToken();
        console.log(token);
        try {
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
            await axios.patch(`http://localhost:256/api/v1/companies/${editCompanyId}`, {
                name: name,
                address: address,
                city: city,
                phoneNumber: phoneNumber,
                postalCode: postalCode,
                website: website,
                email: email
            });
            closeEditCompanyModal();
            setEditError(null);
        } catch (error: any) {
            if (error.response && error.response.status === 403) {
                console.error('Insufficient privileges to edit the company:', error);
                setEditError('You do not have sufficient privileges to edit this company.');
            } else if (error.response && error.response.status === 404) {
                console.error('Impossible to edit non-existing company:', error);
                setEditError("You can't edit company, which doesn't exist.");
            } else if (error.response && error.response.status === 422) {
                console.error('Invalid company information:', error);
                setCreateError('Entered data for company is invalid.');
            } else {
                console.error('Error updating company:', error);
                setEditError('An error occurred while updating the company. Please check your input and try again.');
            }
        }
    };

    return (
        <CompanyContainer>
            <CompanyHeader style={{fontFamily: 'Rubik Broken Fax'}}>
                Company List
                <EditButtonContainer>
                    <NewCompanyButton  style={{marginTop: "15px"}} onClick={openCreateCompanyModal}>
                        New Company
                    </NewCompanyButton>
                </EditButtonContainer>
            </CompanyHeader>
            <List>
                {editError && <ErrorMessage>{editError}</ErrorMessage>}
                {companies.map((company: any) => (
                    <CompanyItem key={company.id}>
                        <Link to={`/companies/${company.id}`}>
                            {company.name} - {company.address}, {company.city}
                        </Link>
                        <EditButtonContainer>
                            <EditButton style={{fontFamily: 'Rubik Broken Fax'}} onClick={() => openEditCompanyModal(company.id, company.name, company.address, company.city, company.phoneNumber, company.postalCode, company.website, company.email)}>
                                Edit
                            </EditButton>
                            <DeleteButton  style={{fontFamily: 'Rubik Broken Fax'}}onClick={() => handleDelete(company.id)}>
                                Delete
                            </DeleteButton>
                        </EditButtonContainer>

                    </CompanyItem>
                ))}
            </List>

            {}
            {showCreateCompanyModal && (
                <ModalContainer>
                    <h1>Create company</h1>
                    <Label>Company name:</Label>
                    <Input type="text" value={name} onChange={(e) => setCompanyName(e.target.value)} />
                    <br />

                    <Label>Company address:</Label>
                    <Input type="text" value={address} onChange={(e) => setCompanyAddress(e.target.value)} />
                    <br />

                    <Label>Company postal code:</Label>
                    <Input type="text" value={postalCode} onChange={(e) => setCompanyPostalCode(e.target.value)} />
                    <br />

                    <Label>Company city:</Label>
                    <Input type="text" value={city} onChange={(e) => setCompanyCity(e.target.value)} />
                    <br />

                    <Label>Company phone number:</Label>
                    <Input type="text" value={phoneNumber} onChange={(e) => setCompanyPhoneNumber(e.target.value)} />
                    <br />

                    <Label>Company email:</Label>
                    <Input type="email" value={email} onChange={(e) => setCompanyEmail(e.target.value)} />
                    <br />

                    <Label>Company website:</Label>
                    <Input type="url" value={website} onChange={(e) => setCompanyWebsite(e.target.value)} />
                    <br />

                    <CreateCompanyButton  style={{margin: "10px"}} onClick={handleCreateCompany}>Create Company</CreateCompanyButton>
                    <NewCompanyButton style={{margin: "10px"}} onClick={closeCreateCompanyModal}>Close</NewCompanyButton>
                    {createError && <ErrorMessage>{createError}</ErrorMessage>}
                </ModalContainer>
            )}

            {}
            {showEditCompanyModal && (
                <ModalContainer>
                    <h1>Create company</h1>
                    <Label>Company name:</Label>
                    <Input type="text" value={name} onChange={(e) => setCompanyName(e.target.value)} />
                    <br />

                    <Label>Company address:</Label>
                    <Input type="text" value={address} onChange={(e) => setCompanyAddress(e.target.value)} />
                    <br />

                    <Label>Company postal code:</Label>
                    <Input type="text" value={postalCode} onChange={(e) => setCompanyPostalCode(e.target.value)} />
                    <br />

                    <Label>Company city:</Label>
                    <Input type="text" value={city} onChange={(e) => setCompanyCity(e.target.value)} />
                    <br />

                    <Label>Company phone number:</Label>
                    <Input type="text" value={phoneNumber} onChange={(e) => setCompanyPhoneNumber(e.target.value)} />
                    <br />

                    <Label>Company email:</Label>
                    <Input type="email" value={email} onChange={(e) => setCompanyEmail(e.target.value)} />
                    <br />

                    <Label>Company website:</Label>
                    <Input type="url" value={website} onChange={(e) => setCompanyWebsite(e.target.value)} />
                    <br />

                    <CreateCompanyButton  style={{margin: "10px"}} onClick={handleEditCompany}>Save Changes</CreateCompanyButton>
                    <NewCompanyButton style={{margin: "10px"}} onClick={closeEditCompanyModal}>Close</NewCompanyButton>
                    {editError && <ErrorMessage>{editError}</ErrorMessage>}
                </ModalContainer>
            )}
        </CompanyContainer>
    );
};

export default CompanyList;
