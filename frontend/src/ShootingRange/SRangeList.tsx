// @ts-ignore  
import { jwtDecode } from 'jwt-decode';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const SRangeContainer = styled.div`
  max-width: 600px;
  margin: 5px auto;
  padding: 20px;
  background-color: #0c7075;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
`;

const SRangeHeader = styled.h1`
  color: white;
  margin: 10px
  text-align: center;
  font-size: 22px;
`;

const SRangeItem = styled.li`
  list-style-type: none;
  margin: 10px 0;
  padding: 10px;
  background-color: #05161A;
  border-color: #05161A;
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

const NewSRangeButton = styled.button`
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
max-height: 80vh; /* Set maximum height to 80% of the viewport height */
overflow-y: auto; /* Add this line for vertical scrolling */
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

const CreateSRangeButton = styled.button`
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
  border-color: #05161A;
  padding: 0;
`;

const SRangeList = () => {
    const [ranges, setRanges] = useState([]);
    const [showCreateSRangeModal, setShowCreateSRangeModal] = useState(false);
    const [showEditSRangeModal, setShowEditSRangeModal] = useState(false);

    const [address, setSRangeAddress] = useState('');
    const [city, setSRangeCity] = useState('');
    const [price, setSRangePrice] = useState('');
    const [max, setSRangeMax] = useState('');
    const [outdoor, setSRangeOutdoor] = useState<boolean|null|string>('');

    const [monOpen, setSRangeMonOpen] = useState('');
    const [monClose, setSRangeMonClose] = useState('');

    const [tueOpen, setSRangeTueOpen] = useState('');
    const [tueClose, setSRangeTueClose] = useState('');

    const [wedOpen, setSRangeWedOpen] = useState('');
    const [wedClose, setSRangeWedClose] = useState('');

    const [thurOpen, setSRangeThurOpen] = useState('');
    const [thurClose, setSRangeThurClose] = useState('');

    const [friOpen, setSRangeFriOpen] = useState('');
    const [friClose, setSRangeFriClose] = useState('');

    const [satOpen, setSRangeSatOpen] = useState('');
    const [satClose, setSRangeSatClose] = useState('');

    const [sunOpen, setSRangeSunOpen] = useState('');
    const [sunClose, setSRangeSunClose] = useState('');

    const [editSRangeId, setEditSRangeId] = useState(null);
    const [editError, setEditError] = useState<string | null>(null);
    const [createError, setCreateError] = useState<string | null>(null);

    const fetchSRanges = async () => {
        try {
            const response = await axios.get('http://localhost:256/api/v1/shootingRanges', {});
            setRanges(response.data);
        } catch (error) {
            console.error('Error fetching shooting ranges:', error);
        }
    };

    useEffect(() => {
        fetchSRanges();
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

    const handleDelete = async (rangeId: any) => {
        const token = refreshToken();
        try {
            await axios.delete(`http://localhost:256/api/v1/shootingRanges/${rangeId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            setRanges(prevRanges =>
                prevRanges.filter((range: any) => range.id !== rangeId)
            );
            // Clear any previous error messages
            setEditError(null);
        } catch (error: any) {
            if (error.response && error.response.status === 403) {
                console.error('Insufficient privileges to delete the shooting range:', error);
                setEditError('You do not have sufficient privileges to delete this shooting range.');
            } else if (error.response && error.response.status === 404) {
                console.error('Impossible to delete non-existing shooting range:', error);
                setEditError("You can't delete shooting range, which doesn't exist.");
            } else {
                console.error('Error deleting shooting range:', error);
                // Display a generic error message for other errors
                setEditError('An error occurred while deleting the shooting range. Please try again later.');
            }
        }
    };

    const openCreateSRangeModal = () => {
        setShowCreateSRangeModal(true);
    };

    const closeCreateSRangeModal = () => {
        setShowCreateSRangeModal(false);
    };

    const handleCreateSRange = async (e: any) => {
        e.preventDefault();
        const token = refreshToken();
        console.log(token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        await axios.post('http://localhost:256/api/v1/shootingRanges', {
            address: address,
            city: city,
            price: price,
            maxShooters: max,
            isOutdoor: outdoor,
            fk_company: 1,
            monOpen: monOpen,
            monClose: monClose,
            tueOpen: tueOpen,
            tueClose: tueClose,
            wedOpen: wedOpen,
            wedClose: wedClose,
            thurOpen: thurOpen,
            thurClose: thurClose,
            friOpen: friOpen,
            froClose: friClose,
            satOpen: satOpen,
            satClose: satClose,
            sunOpen: sunOpen,
            sunClose: sunClose
        })
            .then(response => {
                console.log('Shooting range created:', response.data);
                closeCreateSRangeModal();
            })
            .catch(error => {
                if (error.response && error.response.status === 403) {
                    console.error('Insufficient privileges to create the shooting range:', error);
                    setCreateError('You do not have sufficient privileges to create this shooting range.');
                } else if (error.response && error.response.status === 422) {
                    console.error('Invalid shooting range information:', error);
                    setCreateError('Entered data for shooting range is invalid.');
                } else {
                    console.error('Error creating shooting range:', error);
                    setCreateError('An error occurred while creating the shooting range. Please check your input and try again.');
                }
            });
    }

    const openEditSRangeModal = (rangeId: any, rangeOutdoor: any, address: any, city: any, price: any, max: any,
        monOpen: any, monClose: any, tueOpen: any, tueClose:any, wedOpen:any,wedClose:any, thurOpen:any, thurClose:any, friOpen:any, friClose:any, satOpen:any, satClose:any, sunOpen:any, sunClose:any) => {
        setEditSRangeId(rangeId);
        setSRangeAddress(address);
        setSRangeCity(city);
        setSRangePrice(price);
        if (rangeOutdoor == "true"){
            setSRangeOutdoor(true);
        } else {
            setSRangeOutdoor(false);
        }
        setSRangeMax(max);
        setSRangeMonOpen(monOpen);
        setSRangeMonClose(monClose);
        setSRangeTueOpen(tueOpen);
        setSRangeTueClose(tueClose);
        setSRangeWedOpen(wedOpen);
        setSRangeWedClose(wedClose);
        setSRangeThurOpen(thurOpen);
        setSRangeThurClose(thurClose);
        setSRangeFriOpen(friOpen);
        setSRangeFriClose(friClose);
        setSRangeSatOpen(satOpen);
        setSRangeSatClose(satClose);
        setSRangeSunOpen(sunOpen);
        setSRangeSunClose(sunClose);
        setShowEditSRangeModal(true);
    };

    const closeEditSRangeModal = () => {
        setEditSRangeId(null);
        setShowEditSRangeModal(false);
    };

    const handleEditSRange = async (e: any) => {
        e.preventDefault();
        const token = refreshToken();
        console.log(token);
        try {
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
            await axios.patch(`http://localhost:256/api/v1/shootingRanges/${editSRangeId}`, {
                address: address,
            city: city,
            price: price,
            maxShooters: max,
            isOutdoor: outdoor,
            fk_company: 1,
            monOpen: monOpen,
            monClose: monClose,
            tueOpen: tueOpen,
            tueClose: tueClose,
            wedOpen: wedOpen,
            wedClose: wedClose,
            thurOpen: thurOpen,
            thurClose: thurClose,
            friOpen: friOpen,
            froClose: friClose,
            satOpen: satOpen,
            satClose: satClose,
            sunOpen: sunOpen,
            sunClose: sunClose
            });
            closeEditSRangeModal();
            setEditError(null);
        } catch (error: any) {
            if (error.response && error.response.status === 403) {
                console.error('Insufficient privileges to edit the shooting range:', error);
                setEditError('You do not have sufficient privileges to edit this shooting range.');
            } else if (error.response && error.response.status === 404) {
                console.error('Impossible to edit non-existing shooting range:', error);
                setEditError("You can't edit shooting range, which doesn't exist.");
            } else if (error.response && error.response.status === 422) {
                console.error('Invalid shooting range information:', error);
                setCreateError('Entered data for shooting range is invalid.');
            } else {
                console.error('Error updating shooting range:', error);
                setEditError('An error occurred while updating the shooting range. Please check your input and try again.');
            }
        }
    };

    return (
        <SRangeContainer>
            <SRangeHeader style={{fontFamily: 'Rubik Broken Fax'}}>
                Shooting ranges list
                <EditButtonContainer>
                    <NewSRangeButton style={{marginTop: "15px"}} onClick={openCreateSRangeModal}>
                        New shooting range
                    </NewSRangeButton>
                </EditButtonContainer>
            </SRangeHeader>
            <List>
                {editError && <ErrorMessage>{editError}</ErrorMessage>}
                {ranges.map((range: any) => (
                    <SRangeItem key={range.id}>
                        <Link to={`/ranges/${range.id}`}>
                            {range.address}, {range.city}
                        </Link>
                        <EditButtonContainer>
                            <EditButton style={{fontFamily: 'Rubik Broken Fax'}} onClick={() => openEditSRangeModal(range.id, range.outdoor, range.address, range.city, range.price, range.maxShooters,
                                range.fk_hours.monOpen, range.fk_hours.monClose, range.fk_hours.tueOpen, range.fk_hours.tueClose, range.fk_hours.wedOpen, range.fk_hours.wedClose, range.fk_hours.thurOpen, range.fk_hours.thurClose, range.fk_hours.friOpen,
                                range.fk_hours.friClose, range.fk_hours.satOpen, range.fk_hours.satClose, range.fk_hours.sunOpen, range.fk_hours.sunClose)}>
                                Edit
                            </EditButton>
                            <DeleteButton style={{fontFamily: 'Rubik Broken Fax'}} onClick={() => handleDelete(range.id)}>
                                Delete
                            </DeleteButton>
                        </EditButtonContainer>

                    </SRangeItem>
                ))}
            </List>

            {}
            {showCreateSRangeModal && (
                <ModalContainer>
                    <h1>Create shooting range</h1>

                    <Label>Shooting range address:</Label>
                    <Input type="text" value={address} onChange={(e) => setSRangeAddress(e.target.value)} />
                    <br />

                    <Label>Shooting range city:</Label>
                    <Input type="text" value={city} onChange={(e) => setSRangeCity(e.target.value)} />
                    <br />

                    <Label>Shooting range price:</Label>
                    <Input type="number" min="0.01" step="0.01" value={price} onChange={(e) => setSRangePrice(e.target.value)} />
                    <br />

                    <Label>Shooting range maximum amount of shooters:</Label>
                    <Input type="number" min="1" step="1" value={max} onChange={(e) => setSRangeMax(e.target.value)} />
                    <br />

                    <Label>Is outdoor shooting range:</Label>
                    <Input type="checkbox" value="true" onChange={(e) => setSRangeOutdoor(e.target.value)} />
                    <br />

                    <Label>Monday work hours:</Label>
                    <Input type="time" value={monOpen} onChange={(e) => setSRangeMonOpen(e.target.value)} />
                    <Input type="time" value={monClose} onChange={(e) => setSRangeMonClose(e.target.value)} />
                    <br />

                    <Label>Tuesday work hours:</Label>
                    <Input type="time" value={tueOpen} onChange={(e) => setSRangeTueOpen(e.target.value)} />
                    <Input type="time" value={tueClose} onChange={(e) => setSRangeTueClose(e.target.value)} />
                    <br />

                    <Label>Wednesday work hours:</Label>
                    <Input type="time" value={wedOpen} onChange={(e) => setSRangeWedOpen(e.target.value)} />
                    <Input type="time" value={wedClose} onChange={(e) => setSRangeWedClose(e.target.value)} />
                    <br />

                    <Label>Thurssday work hours:</Label>
                    <Input type="time" value={thurOpen} onChange={(e) => setSRangeThurOpen(e.target.value)} />
                    <Input type="time" value={thurClose} onChange={(e) => setSRangeThurClose(e.target.value)} />
                    <br />

                    <Label>Friday work hours:</Label>
                    <Input type="time" value={friOpen} onChange={(e) => setSRangeFriOpen(e.target.value)} />
                    <Input type="time" value={friClose} onChange={(e) => setSRangeFriClose(e.target.value)} />
                    <br />

                    <Label>Saturday work hours:</Label>
                    <Input type="time" value={satOpen} onChange={(e) => setSRangeSatOpen(e.target.value)} />
                    <Input type="time" value={satClose} onChange={(e) => setSRangeSatClose(e.target.value)} />
                    <br />

                    <Label>Sunday work hours:</Label>
                    <Input type="time" value={sunOpen} onChange={(e) => setSRangeSunOpen(e.target.value)} />
                    <Input type="time" value={sunClose} onChange={(e) => setSRangeSunClose(e.target.value)} />
                    <br />

                    <CreateSRangeButton style={{margin: "10px"}} onClick={handleCreateSRange}>Create shooting range</CreateSRangeButton>
                    <NewSRangeButton style={{margin: "10px"}} onClick={closeCreateSRangeModal}>Close</NewSRangeButton>
                    {createError && <ErrorMessage>{createError}</ErrorMessage>}
                </ModalContainer>
            )}

            {}
            {showEditSRangeModal && (
                <ModalContainer>
                    <h1>Update shooting range</h1>
                    <Label>Shooting range address:</Label>
                    <Input type="text" value={address} onChange={(e) => setSRangeAddress(e.target.value)} />
                    <br />

                    <Label>Shooting range city:</Label>
                    <Input type="text" value={city} onChange={(e) => setSRangeCity(e.target.value)} />
                    <br />

                    <Label>Shooting range price:</Label>
                    <Input type="number" min="0.01" step="0.01" value={price} onChange={(e) => setSRangePrice(e.target.value)} />
                    <br />

                    <Label>Shooting range maximum amount of shooters:</Label>
                    <Input type="number" min="1" step="1" value={max} onChange={(e) => setSRangeMax(e.target.value)} />
                    <br />

                    <Label>Is outdoor shooting range:</Label>
                    <Input type="checkbox" value="true" onChange={(e) => setSRangeOutdoor(e.target.value)} />
                    <br />

                    <Label>Monday work hours:</Label>
                    <Input type="time" value={monOpen} onChange={(e) => setSRangeMonOpen(e.target.value)} />
                    <Input type="time" value={monClose} onChange={(e) => setSRangeMonClose(e.target.value)} />
                    <br />

                    <Label>Tuesday work hours:</Label>
                    <Input type="time" value={tueOpen} onChange={(e) => setSRangeTueOpen(e.target.value)} />
                    <Input type="time" value={tueClose} onChange={(e) => setSRangeTueClose(e.target.value)} />
                    <br />

                    <Label>Wednesday work hours:</Label>
                    <Input type="time" value={wedOpen} onChange={(e) => setSRangeWedOpen(e.target.value)} />
                    <Input type="time" value={wedClose} onChange={(e) => setSRangeWedClose(e.target.value)} />
                    <br />

                    <Label>Thurssday work hours:</Label>
                    <Input type="time" value={thurOpen} onChange={(e) => setSRangeThurOpen(e.target.value)} />
                    <Input type="time" value={thurClose} onChange={(e) => setSRangeThurClose(e.target.value)} />
                    <br />

                    <Label>Friday work hours:</Label>
                    <Input type="time" value={friOpen} onChange={(e) => setSRangeFriOpen(e.target.value)} />
                    <Input type="time" value={friClose} onChange={(e) => setSRangeFriClose(e.target.value)} />
                    <br />

                    <Label>Saturday work hours:</Label>
                    <Input type="time" value={satOpen} onChange={(e) => setSRangeSatOpen(e.target.value)} />
                    <Input type="time" value={satClose} onChange={(e) => setSRangeSatClose(e.target.value)} />
                    <br />

                    <Label>Sunday work hours:</Label>
                    <Input type="time" value={sunOpen} onChange={(e) => setSRangeSunOpen(e.target.value)} />
                    <Input type="time" value={sunClose} onChange={(e) => setSRangeSunClose(e.target.value)} />
                    <br />

                    <CreateSRangeButton style={{margin: "10px"}} onClick={handleEditSRange}>Save Changes</CreateSRangeButton>
                    <NewSRangeButton style={{margin: "10px"}} onClick={closeEditSRangeModal}>Close</NewSRangeButton>
                    {editError && <ErrorMessage>{editError}</ErrorMessage>}
                </ModalContainer>
            )}
        </SRangeContainer>
    );
};

export default SRangeList;
