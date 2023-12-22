import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import { useParams } from 'react-router-dom';

const FirearmTable = () => {
    const [firearm, setFirearm] = useState<any>([]);
    const { firearmId } = useParams();

    const fetchFirearm = async (id: any) => {
        try {
            const response = await axios.get(`http://localhost:256/api/v1/firearms/${id}`);
            setFirearm(response.data);
        } catch (error) {
            console.error('Error fetching instructor:', error);
        }
    };

    useEffect(() => {
        fetchFirearm(firearmId);
    }, [firearmId]);

    return (
        <div>
            <h1 style={{ color: '#fff', fontFamily: 'Rubik Broken Fax' }}>Firearm information</h1>
            <br />
            <br />
            {firearm ? (
                <Table striped bordered hover style={{ borderColor: '#0F969C' }}>
                    <tbody>
                        <tr>
                            <td style={{ width: '30%', color: "#fff" }}>Manufacturer</td>
                            <td style={{ color: '#fff' }}>{firearm.manufacturer}</td>
                        </tr>
                        <tr>
                            <td style={{ color: '#fff' }}>Model</td>
                            <td style={{ color: '#fff' }}>{firearm.model}</td>
                        </tr>
                        <tr>
                            <td style={{ color: '#fff' }}>Caliber</td>
                            <td style={{ color: '#fff' }}>{firearm.caliber}</td>
                        </tr>
                        <tr>
                            <td style={{ color: '#fff' }}>Price per shot</td>
                            <td  style={{ color: '#fff' }}>{firearm.price}</td>
                        </tr>
                        {firearm.picture && (
                            <tr>
                                <td style={{ color: '#fff' }}>Picture</td>
                                <td>
                                    <img src={firearm.picture} alt="Firearm" style={{ maxWidth: '50%' }} />
                                </td>
                            </tr>
                        )}
                    </tbody>
                </Table>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
};

export default FirearmTable;
