// @ts-ignore  
import { jwtDecode } from 'jwt-decode';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const UserContainer = styled.div`
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f4f4f4;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
`;

const UserHeader = styled.h1`
  color: #333;
  text-align: center;
`;

const UserItem = styled.li`
  list-style-type: none;
  margin: 10px 0;
  padding: 10px;
  background-color: #fff;
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

const NewUserButton = styled.button`
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 4px;
  cursor: pointer;

  &:hover {
    background-color: #45a049;
  }
`;

const ModalContainer = styled.div`
  background-color: white;
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

const CreateUserButton = styled.button`
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

const UserList = () => {
    const [users, setUsers] = useState([]);
    const [showCreateUserModal, setShowCreateUserModal] = useState(false);
    const [showEditUserModal, setShowEditUserModal] = useState(false);
    const [name, setUserName] = useState('');
    const [surname, setUserSurname] = useState('');
    const [password, setUserPassword] = useState('');
    const [type, setUserType] = useState('');
    const [email, setUserEmail] = useState('');
    const [editUserId, setEditUserId] = useState(null);
    const [editError, setEditError] = useState<string | null>(null);
    const [createError, setCreateError] = useState<string | null>(null);

    const fetchUsers = async () => {
        try {
            const response = await axios.get('http://localhost:256/api/v1/users', {});
            setUsers(response.data);
        } catch (error) {
            console.error('Error fetching users:', error);
        }
    };

    useEffect(() => {
        fetchUsers();
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

    const handleDelete = async (userId: any) => {
        const token = refreshToken();
        try {
            await axios.delete(`http://localhost:256/api/v1/users/${userId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            // Update the local state by removing the deleted user
            setUsers(prevUsers =>
                prevUsers.filter((user: any) => user.id !== userId)
            );
            // Clear any previous error messages
            setEditError(null);
        } catch (error: any) {
            if (error.response && error.response.status === 403) {
                console.error('Insufficient privileges to delete the user:', error);
                setEditError('You do not have sufficient privileges to delete this user.');
            } else if (error.response && error.response.status === 404) {
                console.error('Impossible to delete non-existing user:', error);
                setEditError("You can't delete user, which doesn't exist.");
            } else {
                console.error('Error deleting user:', error);
                // Display a generic error message for other errors
                setEditError('An error occurred while deleting the user. Please try again later.');
            }
        }
    };

    const openCreateUserModal = () => {
        setShowCreateUserModal(true);
    };

    const closeCreateUserModal = () => {
        setShowCreateUserModal(false);
    };

    const handleCreateUser = async (e: any) => {
        e.preventDefault();
        const token = refreshToken();
        console.log(token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        await axios.post('http://localhost:256/api/v1/users', {
            name: name,
            surname: surname,
            email: email,
            password: password,
            type: type
        })
            .then(response => {
                console.log('User created:', response.data);
                closeCreateUserModal();
            })
            .catch(error => {
                if (error.response && error.response.status === 403) {
                    console.error('Insufficient privileges to create the user:', error);
                    setCreateError('You do not have sufficient privileges to create this user.');
                } else if (error.response && error.response.status === 422) {
                    console.error('Invalid user information:', error);
                    setCreateError('Entered data for user is invalid.');
                } else {
                    console.error('Error creating user:', error);
                    setCreateError('An error occurred while creating the user. Please check your input and try again.');
                }
            });
    }

    const openEditUserModal = (userId: any, userName: any, userSurname: any, userType: any, userEmail: any) => {
        setEditUserId(userId);
        setUserName(userName);
        setUserSurname(userSurname);
        setUserEmail(userEmail);
        setUserType(userType);
        setShowEditUserModal(true);
    };

    const closeEditUserModal = () => {
        setEditUserId(null);
        setShowEditUserModal(false);
    };

    const handleEditUser = async (e: any) => {
        e.preventDefault();
        const token = refreshToken();
        console.log(token);
        try {
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
            await axios.patch(`http://localhost:256/api/v1/users/${editUserId}`, {
                name: name,
                surname: surname,
                email: email,
                type: type
            });
            closeEditUserModal();
            setEditError(null);
        } catch (error: any) {
            if (error.response && error.response.status === 403) {
                console.error('Insufficient privileges to edit the user:', error);
                setEditError('You do not have sufficient privileges to edit this user.');
            } else if (error.response && error.response.status === 404) {
                console.error('Impossible to edit non-existing user:', error);
                setEditError("You can't edit user, which doesn't exist.");
            } else if (error.response && error.response.status === 422) {
                console.error('Invalid user information:', error);
                setCreateError('Entered data for user is invalid.');
            } else {
                console.error('Error updating user:', error);
                setEditError('An error occurred while updating the user. Please check your input and try again.');
            }
        }
    };

    return (
        <UserContainer>
            <UserHeader>
                User List
                <EditButtonContainer>
                    <NewUserButton onClick={openCreateUserModal}>
                        New User
                    </NewUserButton>
                </EditButtonContainer>
            </UserHeader>
            <List>
                {editError && <ErrorMessage>{editError}</ErrorMessage>}
                {users.map((user: any) => (
                    <UserItem key={user.id}>
                        <Link to={`/users/${user.id}`}>
                            {user.name} - {user.address}, {user.city}
                        </Link>
                        <EditButtonContainer>
                            <EditButton onClick={() => openEditUserModal(user.id, user.name, user.surname, user.type, user.email)}>
                                Edit
                            </EditButton>
                            <DeleteButton onClick={() => handleDelete(user.id)}>
                                Delete
                            </DeleteButton>
                        </EditButtonContainer>

                    </UserItem>
                ))}
            </List>

            { }
            {showCreateUserModal && (
                <ModalContainer>
                    <h1>Create user</h1>
                    <Label>User name:</Label>
                    <Input type="text" value={name} onChange={(e) => setUserName(e.target.value)} />
                    <br />

                    <Label>User surname:</Label>
                    <Input type="text" value={surname} onChange={(e) => setUserSurname(e.target.value)} />
                    <br />

                    <Label>User email:</Label>
                    <Input type="email" value={email} onChange={(e) => setUserEmail(e.target.value)} />
                    <br />

                    <Label>User password:</Label>
                    <Input type="password" value={password} onChange={(e) => setUserPassword(e.target.value)} />
                    <br />

                    <Label>User type:</Label>
                    <Input type="text" value={type} onChange={(e) => setUserType(e.target.value)} />
                    <br />

                    <CreateUserButton onClick={handleCreateUser}>Create User</CreateUserButton>
                    <NewUserButton onClick={closeCreateUserModal}>Close</NewUserButton>
                    {createError && <ErrorMessage>{createError}</ErrorMessage>}
                </ModalContainer>
            )}

            { }
            {showEditUserModal && (
                <ModalContainer>
                    <h1>Create user</h1>
                    <Label>User name:</Label>
                    <Input type="text" value={name} onChange={(e) => setUserName(e.target.value)} />
                    <br />

                    <Label>User surname:</Label>
                    <Input type="text" value={surname} onChange={(e) => setUserSurname(e.target.value)} />
                    <br />

                    <Label>User email:</Label>
                    <Input type="email" value={email} onChange={(e) => setUserEmail(e.target.value)} />
                    <br />

                    <Label>User type:</Label>
                    <Input type="text" value={type} onChange={(e) => setUserType(e.target.value)} />
                    <br />
                    
                    <CreateUserButton onClick={handleEditUser}>Save Changes</CreateUserButton>
                    <NewUserButton onClick={closeEditUserModal}>Close</NewUserButton>
                    {editError && <ErrorMessage>{editError}</ErrorMessage>}
                </ModalContainer>
            )}
        </UserContainer>
    );
};

export default UserList;
