import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import { useParams } from 'react-router-dom';

const CompanyTable = () => {
    const [company, setCompany] = useState<any>([]);
    const { companyId } = useParams();
    const [firearms, setFirearms] = useState<any>([]);
    const [instructors, setInstructors] = useState<any>([]);

    const fetchCompany = async (id: any) => {
        try {
            const response = await axios.get(`http://localhost:256/api/v1/companies/${id}`);
            setCompany(response.data);
        } catch (error) {
            console.error('Error fetching instructor:', error);
        }
    };

    const fetchCompanyFirearms = async (id: any) => {
        try {
            const response = await axios.get(`http://localhost:256/api/v1/companies/${id}/firearms`);
            setFirearms(response.data);
        } catch (error) {
            console.error('Error fetching instructor:', error);
        }
    };

    const fetchCompanyInstructors = async (id: any) => {
        try {
            const response = await axios.get(`http://localhost:256/api/v1/companies/${id}/instructors`);
            setInstructors(response.data);
        } catch (error) {
            console.error('Error fetching instructor:', error);
        }
    };

    useEffect(() => {
        fetchCompany(companyId);
        fetchCompanyFirearms(companyId);
        fetchCompanyInstructors(companyId);
    }, [companyId]);

    return (
        <div>
            <h1 style={{ color: '#fff', fontFamily: 'Rubik Broken Fax' }}>Company information</h1>
            <br />
            <br />
            {company /*&& company.approved*/ ? (
                <div>
                    <Table striped bordered hover style={{ borderColor: '#0F969C' }}>
                        <tbody>
                            <tr>
                                <td style={{ width: '30%', color: "#fff" }}>Company name</td>
                                <td style={{ color: '#fff' }}>{company.name}</td>
                            </tr>
                            <tr>
                                <td style={{ width: '30%', color: "#fff" }}>Company address</td>
                                <td style={{ color: '#fff' }}>{company.address}</td>
                            </tr>
                            <tr>
                                <td style={{ width: '30%', color: "#fff" }}>Company city</td>
                                <td style={{ color: '#fff' }}>{company.city}</td>
                            </tr>
                            <tr>
                                <td style={{ width: '30%', color: "#fff" }}>Company postal code</td>
                                <td style={{ color: '#fff' }}>{company.postalCode}</td>
                            </tr>
                            {company.email && (
                                <tr>
                                    <td style={{ color: '#fff' }}>Company email</td>
                                    <td style={{ color: '#fff' }}>{company.email}</td>
                                </tr>
                            )}
                            {company.phoneNumber && (
                                <tr>
                                    <td style={{ color: '#fff' }}>Company phone number</td>
                                    <td style={{ color: '#fff' }}>{company.phoneNumber}</td>
                                </tr>
                            )}
                            {company.website && (
                                <tr>
                                    <td style={{ color: '#fff' }}>Company website</td>
                                    <td style={{ color: '#fff' }}>{company.website}</td>
                                </tr>
                            )}
                        </tbody>
                    </Table>

                    <br />
                    <h1 style={{ color: '#fff', fontFamily: 'Rubik Broken Fax' }}>Company instructors</h1>
                    <br />
                    <Table striped bordered hover style={{ borderColor: '#0F969C' }}>
                        <tbody>
                            {instructors.map((instructor: any) => (
                                <tr key={instructor.id}>
                                    <td style={{ color: '#fff' }}>{instructor.name}</td>
                                    <td style={{ color: '#fff' }}>{instructor.surname}</td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>

                    <br />
                    <h1 style={{ color: '#fff', fontFamily: 'Rubik Broken Fax' }}>Company firearms</h1>
                    <br />
                    <Table striped bordered hover style={{ borderColor: '#0F969C' }}>
                        <tbody>
                            {firearms.map((firearm: any) => (
                                <tr key={firearm.id}>
                                    <td style={{ color: '#fff' }}>{firearm.manufacturer}</td>
                                    <td style={{ color: '#fff' }}>{firearm.model}</td>
                                    <td style={{ color: '#fff' }}>{firearm.caliber}</td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </div>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
};

export default CompanyTable;
