import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import { useParams } from 'react-router-dom';

const InstructorTable = () => {
    const [instructor, setInstructor] = useState<any>([]);
    const { instructorId } = useParams();

    const fetchInstructor = async (id: any) => {
        try {
            const response = await axios.get(`http://localhost:256/api/v1/instructors/${id}`);
            setInstructor(response.data);
        } catch (error) {
            console.error('Error fetching instructor:', error);
        }
    };

    useEffect(() => {
        fetchInstructor(instructorId);
    }, [instructorId]);

    return (
        <div>
            <h1 style={{ color: '#fff', fontFamily: 'Rubik Broken Fax' }}>Instructor information</h1>
            <br/>
            <br/>
            {instructor ? (
                <Table striped bordered hover style={{ borderColor: '#0F969C' }}>
                <tbody>
                    <tr>
                        <td style={{ width: '30%', color: '#fff' }}>Name</td>
                        <td style={{ color: '#fff' }}>{instructor.name}</td>
                    </tr>
                    <tr>
                        <td style={{ color: '#fff' }}>Surname</td>
                        <td style={{ color: '#fff' }}>{instructor.surname}</td>
                    </tr>
                </tbody>
            </Table>
            ) : (
                <p style={{ color: '#fff' }}>Loading...</p>
            )}
        </div>
    );
};

export default InstructorTable;
