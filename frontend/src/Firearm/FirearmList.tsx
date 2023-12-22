// @ts-ignore  
import { jwtDecode } from 'jwt-decode';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const FirearmContainer = styled.div`
max-width: 600px;
margin: 5px auto;
padding: 20px;
background-color: #0c7075;
border-radius: 8px;
box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
`;

const FirearmHeader = styled.h1`
color: white;
margin: 10px
text-align: center;
font-size: 22px;
`;

const FirearmItem = styled.li`
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

const NewFirearmButton = styled.button`
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

const CreateFirearmButton = styled.button`
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

const FirearmList = () => {
    const [firearms, setFirearms] = useState([]);
    const [showCreateFirearmModal, setShowCreateFirearmModal] = useState(false);
    const [showEditFirearmModal, setShowEditFirearmModal] = useState(false);
    const [manufacturer, setFirearmManufacturer] = useState('');
    const [model, setFirearmModel] = useState('');
    const [caliber, setFirearmCaliber] = useState('');
    const [price, setFirearmPrice] = useState('');
    const [editFirearmId, setEditFirearmId] = useState(null);
    const [editError, setEditError] = useState<string | null>(null);
    const [createError, setCreateError] = useState<string | null>(null);

    const fetchFirearms = async () => {
        try {
            const response = await axios.get('http://localhost:256/api/v1/firearms', {});
            setFirearms(response.data);
        } catch (error) {
            console.error('Error fetching firearms:', error);
        }
    };

    useEffect(() => {
        fetchFirearms();
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

    const handleDeleteFirearm = async (firearmId: any) => {
        const token = refreshToken();
        try {
            await axios.delete(`http://localhost:256/api/v1/firearms/${firearmId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            // Update the local state by removing the deleted firearm
            setFirearms(prevFirearms =>
                prevFirearms.filter((firearm: any) => firearm.id !== firearmId)
            );
            // Clear any previous error messages
            setEditError(null);
        } catch (error: any) {
            if (error.response && error.response.status === 403) {
                console.error('Insufficient privileges to delete the firearm:', error);
                setEditError('You do not have sufficient privileges to delete this firearm.');
            } else if (error.response && error.response.status === 404) {
                console.error('Impossible to delete non-existing firearm:', error);
                setEditError("You can't delete firearm, which doesn't exist.");
            } else {
                console.error('Error deleting firearm:', error);
                // Display a generic error message for other errors
                setEditError('An error occurred while deleting the firearm. Please try again later.');
            }
        }
    };

    const openCreateFirearmModal = () => {
        setShowCreateFirearmModal(true);
    };

    const closeCreateFirearmModal = () => {
        setShowCreateFirearmModal(false);
    };

    const handleCreateFirearm = async (e: any) => {
        e.preventDefault();
        const token = refreshToken();
        console.log(token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        await axios.post('http://localhost:256/api/v1/firearms', {
            manufacturer: manufacturer,
            model: model,
            caliber: caliber,
            picture: null,
            price: price,
            fk_shootingRange: 1
        })
            .then(response => {
                console.log('Firearm created:', response.data);
                closeCreateFirearmModal();
            })
            .catch(error => {
                if (error.response && error.response.status === 403) {
                    console.error('Insufficient privileges to create the firearm:', error);
                    setCreateError('You do not have sufficient privileges to create this firearm.');
                } else if (error.response && error.response.status === 422) {
                    console.error('Invalid firearm information:', error);
                    setCreateError('Entered data for firearm is invalid.');
                } else {
                    console.error('Error creating firearm:', error);
                    setCreateError('An error occurred while creating the firearm. Please check your input and try again.');
                }
            });
    }

    const openEditFirearmModal = (firearmId: any, firearmPrice: any, firearmModel: any, firearmCaliber: any, firearmManufacturer: any) => {
        setEditFirearmId(firearmId);
        setFirearmModel(firearmModel);
        setFirearmCaliber(firearmCaliber);
        setFirearmManufacturer(firearmManufacturer);
        setFirearmPrice(firearmPrice)
        setShowEditFirearmModal(true);
    };

    const closeEditFirearmModal = () => {
        setEditFirearmId(null);
        setShowEditFirearmModal(false);
    };

    const handleEditFirearm = async (e: any) => {
        e.preventDefault();
        const token = refreshToken();
        console.log(token);
        try {
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
            await axios.patch(`http://localhost:256/api/v1/firearms/${editFirearmId}`, {
                manufacturer: manufacturer,
                model: model,
                caliber: caliber,
                picture: null,
                price: price,
                fk_shootingRange: 1
            });
            closeEditFirearmModal();
            setEditError(null);
        } catch (error: any) {
            if (error.response && error.response.status === 403) {
                console.error('Insufficient privileges to edit the firearm:', error);
                setEditError('You do not have sufficient privileges to edit this firearm.');
            } else if (error.response && error.response.status === 404) {
                console.error('Impossible to edit non-existing firearm:', error);
                setEditError("You can't edit firearm, which doesn't exist.");
            } else if (error.response && error.response.status === 422) {
                console.error('Invalid firearm information:', error);
                setCreateError('Entered data for firearm is invalid.');
            } else {
                console.error('Error updating firearm:', error);
                setEditError('An error occurred while updating the firearm. Please check your input and try again.');
            }
        }
    };

    return (
        <FirearmContainer>
            <FirearmHeader style={{fontFamily: 'Rubik Broken Fax'}}>
                Firearm List
                <EditButtonContainer>
                    <NewFirearmButton style={{marginTop: "15px"}} onClick={openCreateFirearmModal}>
                        New Firearm
                    </NewFirearmButton>
                </EditButtonContainer>
            </FirearmHeader>
            <List>
                {editError && <ErrorMessage>{editError}</ErrorMessage>}
                {firearms.map((firearm: any) => (
                    <FirearmItem key={firearm.id}>
                        <Link to={`/firearms/${firearm.id}`}>
                            {firearm.manufacturer} - {firearm.model}, {firearm.caliber}
                        </Link>
                        <EditButtonContainer>
                            <EditButton style={{fontFamily: 'Rubik Broken Fax'}} onClick={() => openEditFirearmModal(firearm.id, firearm.price, firearm.model, firearm.caliber, firearm.manufacturer,)}>
                                Edit
                            </EditButton>
                            <DeleteButton style={{fontFamily: 'Rubik Broken Fax'}} onClick={() => handleDeleteFirearm(firearm.id)}>
                                Delete
                            </DeleteButton>
                        </EditButtonContainer>
                    </FirearmItem>
                ))}
            </List>

            {}
            {showCreateFirearmModal && (
                <ModalContainer>
                <h1>Create Firearm</h1>
                <Label>Firearm manufacturer:</Label>
                <Input type="text" value={manufacturer} onChange={(e) => setFirearmManufacturer(e.target.value)} />
                <br />
        
                <Label>Firearm model:</Label>
                <Input type="text" value={model} onChange={(e) => setFirearmModel(e.target.value)} />
                <br />
        
                <Label>Firearm caliber:</Label>
                <Input type="text" value={caliber} onChange={(e) => setFirearmCaliber(e.target.value)} />
                <br />
        
                <Label>Firearm price:</Label>
                <Input type="number" min="0.01" step="0.01" value={price} onChange={(e) => setFirearmPrice(e.target.value)} />
                <br />
        
                <CreateFirearmButton onClick={handleCreateFirearm}>Create Firearm</CreateFirearmButton>
                <NewFirearmButton onClick={closeCreateFirearmModal}>Close</NewFirearmButton>
                {createError && <ErrorMessage>{createError}</ErrorMessage>}
              </ModalContainer>
            )}
        
            {showEditFirearmModal && (
              <ModalContainer>
                <h1>Edit Firearm</h1>
                <Label>Firearm manufacturer:</Label>
                <Input type="text" value={manufacturer} onChange={(e) => setFirearmManufacturer(e.target.value)} />
                <br />
        
                <Label>Firearm model:</Label>
                <Input type="text" value={model} onChange={(e) => setFirearmModel(e.target.value)} />
                <br />
        
                <Label>Firearm caliber:</Label>
                <Input type="text" value={caliber} onChange={(e) => setFirearmCaliber(e.target.value)} />
                <br />
        
                <Label>Firearm price:</Label>
                <Input type="number" min="0.01" step="0.01" value={price} onChange={(e) => setFirearmPrice(e.target.value)} />
                <br />
        
                <CreateFirearmButton onClick={handleEditFirearm}>Save Changes</CreateFirearmButton>
                <NewFirearmButton onClick={closeEditFirearmModal}>Close</NewFirearmButton>
                {editError && <ErrorMessage>{editError}</ErrorMessage>}
              </ModalContainer>
            )}
          </FirearmContainer>
        );
        };
        
        export default FirearmList;