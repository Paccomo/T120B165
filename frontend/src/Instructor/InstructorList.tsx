// @ts-ignore
import { jwtDecode } from 'jwt-decode';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const InstructorContainer = styled.div`
max-width: 600px;
margin: 5px auto;
padding: 20px;
background-color: #0c7075;
border-radius: 8px;
box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
`;

const InstructorHeader = styled.h1`
color: white;
margin: 10px
text-align: center;
font-size: 22px;
`;

const InstructorItem = styled.li`
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

const NewInstructorButton = styled.button`
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

const CreateInstructorButton = styled.button`
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

const InstructorList = () => {
    const [instructors, setInstructors] = useState([]);
    const [showCreateInstructorModal, setShowCreateInstructorModal] = useState(false);
    const [showEditInstructorModal, setShowEditInstructorModal] = useState(false);
    const [name, setInstructorName] = useState('');
    const [surname, setInstructorSurname] = useState('');
    const [editInstructorId, setEditInstructorId] = useState(null);
    const [editError, setEditError] = useState<string | null>(null);
    const [createError, setCreateError] = useState<string | null>(null);

    const fetchInstructors = async () => {
        try {
            const response = await axios.get('http://localhost:256/api/v1/instructors', {});
            setInstructors(response.data);
        } catch (error) {
            console.error('Error fetching instructors:', error);
        }
    };

    useEffect(() => {
        fetchInstructors();
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
                    axios
                        .post(
                            'http://localhost:256/api/v1/auth/refresh',
                            {},
                            {
                                headers: {
                                    Authorization: `Bearer ${refreshToken}`,
                                },
                            }
                        )
                        .then((response) => {
                            const { access_token, refresh_token } = response.data;

                            Cookies.set('access_token', access_token);
                            Cookies.set('refresh_token', refresh_token);

                            return Cookies.get('access_token');
                        })
                        .catch((error) => {
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
    };

    const handleDelete = async (instructorId: any) => {
        const token = refreshToken();
        try {
            await axios.delete(`http://localhost:256/api/v1/instructors/${instructorId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            // Update the local state by removing the deleted instructor
            setInstructors((prevInstructors) =>
                prevInstructors.filter((instructor: any) => instructor.id !== instructorId)
            );
            // Clear any previous error messages
            setEditError(null);
        } catch (error: any) {
            if (error.response && error.response.status === 403) {
                console.error('Insufficient privileges to delete the instructor:', error);
                setEditError('You do not have sufficient privileges to delete this instructor.');
            } else if (error.response && error.response.status === 404) {
                console.error('Impossible to delete non-existing instructor:', error);
                setEditError("You can't delete instructor, which doesn't exist.");
            } else {
                console.error('Error deleting instructor:', error);
                // Display a generic error message for other errors
                setEditError('An error occurred while deleting the instructor. Please try again later.');
            }
        }
    };

    const openCreateInstructorModal = () => {
        setShowCreateInstructorModal(true);
    };

    const closeCreateInstructorModal = () => {
        setShowCreateInstructorModal(false);
    };

    const handleCreateInstructor = async (e: any) => {
        e.preventDefault();
        const token = refreshToken();
        console.log(token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        await axios
            .post('http://localhost:256/api/v1/instructors', {
                name: name,
                surname: surname,
                fk_shootingRange: 1,
            })
            .then((response) => {
                console.log('Instructor created:', response.data);
                closeCreateInstructorModal();
            })
            .catch((error) => {
                if (error.response && error.response.status === 403) {
                    console.error('Insufficient privileges to create the instructor:', error);
                    setCreateError('You do not have sufficient privileges to create this instructor.');
                } else if (error.response && error.response.status === 422) {
                    console.error('Invalid instructor information:', error);
                    setCreateError('Entered data for instructor is invalid.');
                } else {
                    console.error('Error creating instructor:', error);
                    setCreateError(
                        'An error occurred while creating the instructor. Please check your input and try again.'
                    );
                }
            });
    };

    const openEditInstructorModal = (
        instructorId: any,
        instructorName: any,
        instructorSurname: any
    ) => {
        setEditInstructorId(instructorId);
        setInstructorName(instructorName);
        setInstructorSurname(instructorSurname);
        setShowEditInstructorModal(true);
    };

    const closeEditInstructorModal = () => {
        setEditInstructorId(null);
        setShowEditInstructorModal(false);
    };

    const handleEditInstructor = async (e: any) => {
        e.preventDefault();
        const token = refreshToken();
        console.log(token);
        try {
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
            await axios.patch(`http://localhost:256/api/v1/instructors/${editInstructorId}`, {
                name: name,
                surname: surname
            });
            closeEditInstructorModal();
            setEditError(null);
        } catch (error: any) {
            if (error.response && error.response.status === 403) {
                console.error('Insufficient privileges to edit the instructor:', error);
                setEditError('You do not have sufficient privileges to edit this instructor.');
            } else if (error.response && error.response.status === 404) {
                console.error('Impossible to edit non-existing instructor:', error);
                setEditError("You can't edit instructor, which doesn't exist.");
            } else if (error.response && error.response.status === 422) {
                console.error('Invalid instructor information:', error);
                setCreateError('Entered data for instructor is invalid.');
            } else {
                console.error('Error updating instructor:', error);
                setEditError(
                    'An error occurred while updating the instructor. Please check your input and try again.'
                );
            }
        }
    };

    return (
        <InstructorContainer>
            <InstructorHeader style={{fontFamily: 'Rubik Broken Fax'}}>
                Instructor List
                <EditButtonContainer>
                    <NewInstructorButton style={{marginTop: "15px"}} onClick={openCreateInstructorModal}>New Instructor</NewInstructorButton>
                </EditButtonContainer>
            </InstructorHeader>
            <List>
                {editError && <ErrorMessage>{editError}</ErrorMessage>}
                {instructors.map((instructor: any) => (
                    <InstructorItem key={instructor.id}>
                        <Link to={`/instructors/${instructor.id}`}>
                            {instructor.name} {instructor.surname}
                        </Link>
                        <EditButtonContainer>
                            <EditButton
                            style={{fontFamily: 'Rubik Broken Fax'}}
                                onClick={() =>
                                    openEditInstructorModal(
                                        instructor.id,
                                        instructor.name,
                                        instructor.surname
                                    )
                                }
                            >
                                Edit
                            </EditButton>
                            <DeleteButton style={{fontFamily: 'Rubik Broken Fax'}} onClick={() => handleDelete(instructor.id)}>Delete</DeleteButton>
                        </EditButtonContainer>
                    </InstructorItem>
                ))}
            </List>

            {showCreateInstructorModal && (
                <ModalContainer>
                    <h1>Create instructor</h1>
                    <Label>Instructor name:</Label>
                    <Input type="text" value={name} onChange={(e) => setInstructorName(e.target.value)} />
                    <br />

                    <Label>Instructor surname:</Label>
                    <Input type="text" value={surname} onChange={(e) => setInstructorSurname(e.target.value)} />
                    <br />

                    <CreateInstructorButton onClick={handleCreateInstructor}>Create Instructor</CreateInstructorButton>
                    <NewInstructorButton onClick={closeCreateInstructorModal}>Close</NewInstructorButton>
                    {createError && <ErrorMessage>{createError}</ErrorMessage>}
                </ModalContainer>
            )}

            {showEditInstructorModal && (
                <ModalContainer>
                    <h1>Edit instructor</h1>
                    <Label>Instructor name:</Label>
                    <Input type="text" value={name} onChange={(e) => setInstructorName(e.target.value)} />
                    <br />

                    <Label>Instructor surname:</Label>
                    <Input type="text" value={surname} onChange={(e) => setInstructorSurname(e.target.value)} />
                    <br />

                    <CreateInstructorButton onClick={handleEditInstructor}>Save Changes</CreateInstructorButton>
                    <NewInstructorButton onClick={closeEditInstructorModal}>Close</NewInstructorButton>
                    {editError && <ErrorMessage>{editError}</ErrorMessage>}
                </ModalContainer>
            )}

        </InstructorContainer>
    );
};

export default InstructorList;