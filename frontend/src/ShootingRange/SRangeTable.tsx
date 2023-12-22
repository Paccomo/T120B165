import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import { useParams } from 'react-router-dom';

const SRangeTable = () => {
    const [range, setRange] = useState<any>([]);
    const { rangeId } = useParams();

    const fetchRange = async (id: any) => {
        try {
            const response = await axios.get(`http://localhost:256/api/v1/shootingRanges/${id}`);
            setRange(response.data);
        } catch (error) {
            console.error('Error fetching instructor:', error);
        }
    };

    useEffect(() => {
        fetchRange(rangeId);
    }, [rangeId]);

    return (
        <div>
            <h1 style={{ color: '#fff', fontFamily: 'Rubik Broken Fax' }}>Shooting range information</h1>
            <br />
            <br />
            {range ? (
                <div>
                    {/* Shooting Range Table */}
                    <Table striped bordered hover style={{ borderColor: '#0F969C' }}>
                        <tbody>
                            <tr>
                                <td style={{ width: '30%', color: "#fff" }}>Address</td>
                                <td  style={{ color: '#fff' }}>{range.address}</td>
                            </tr>
                            <tr>
                                <td style={{ color: '#fff' }}>City</td>
                                <td style={{ color: '#fff' }}>{range.city}</td>
                            </tr>
                            <tr>
                                <td style={{ color: '#fff' }}>1 hour price</td>
                                <td style={{ color: '#fff' }}>{range.price}</td>
                            </tr>
                            <tr>
                                <td style={{ color: '#fff' }}>Maximum amount of shooters at a time</td>
                                <td style={{ color: '#fff' }}>{range.maxShooters}</td>
                            </tr>
                            <tr>
                                <td style={{ color: '#fff' }}>Shooting range type</td>
                                <td style={{ color: '#fff' }}>{range.outdoor ? 'Outdoor' : 'Indoor'}</td>
                            </tr>
                        </tbody>
                    </Table>

                    {/* Working Hours Table */}
                    <br />
                    <h1 style={{ color: '#fff', fontFamily: 'Rubik Broken Fax' }}>Open hours</h1>
                    <br />
                    <Table striped bordered hover style={{ borderColor: '#0F969C' }}>

                        <tbody>
                            <tr>
                                <td style={{ width: '25%' ,color: '#fff' }}>Monday</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.monOpen ?? '--'}</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.monClose ?? '--'}</td>
                            </tr>
                            <tr>
                                <td style={{ width: '25%'  ,color: '#fff'}}>Tuesday</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.tueOpen ?? '--'}</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.tueClose ?? '--'}</td>
                            </tr>
                            <tr>
                                <td style={{ width: '25%' ,color: '#fff' }}>Wednesday</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.wedOpen ?? '--'}</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.wedClose ?? '--'}</td>
                            </tr>
                            <tr>
                                <td style={{ width: '25%' ,color: '#fff' }}>Thursday</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.thurOpen ?? '--'}</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.thurClose ?? '--'}</td>
                            </tr>
                            <tr>
                                <td style={{ width: '25%' ,color: '#fff' }}>Friday</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.friOpen ?? '--'}</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.friClose ?? '--'}</td>
                            </tr>
                            <tr>
                                <td style={{ width: '25%' ,color: '#fff' }}>Saturday</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.satOpen ?? '--'}</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.satClose ?? '--'}</td>
                            </tr>
                            <tr>
                                <td style={{ width: '25%' ,color: '#fff' }}>Sunday</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.sunOpen ?? '--'}</td>
                                <td style={{ color: '#fff' }}>{range.fk_hours?.sunClose ?? '--'}</td>
                            </tr>
                        </tbody>
                    </Table>
                </div>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );

};

export default SRangeTable;
