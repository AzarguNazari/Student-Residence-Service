import React, { useEffect, useState } from "react";
import get from "lodash/get";
import Rodal from "rodal";

import 'rodal/lib/rodal.css';
import './page.css';
import "./Student.css";
import api from "../api";
import Rent from "../components/rent";
import LOCAL_STORAGE from "../local-storage";
import history from "../context/history";

const Student = () => {
    const [appliances, setAppliances] = useState([]);
    const [isEditVisible, setEditVisible] = useState(false);
    const [applianceItem, setApplianceItem] = useState({});


    const getAppliances = () => {
        api.get("http://localhost:9852/api/v1/appliances")
            .then((data) => {
                let applianceList = get(data, ["data", "appliances"], []);
                applianceList = applianceList.filter(({ appliance }) => appliance['available_appliances'] > 0 && appliance['state'] !== "BROKEN");
                setAppliances(applianceList);
            });
    }

    useEffect(() => {
        getAppliances();
    }, []);

    const showRentDialog = () => {
        setEditVisible(true);
    }

    const hideRentDialog = () => {
        setEditVisible(false);
    }

    const showRent = (appliance) => {
        setApplianceItem(appliance);
        showRentDialog();
    }

    const getStudentRentals = () => {
        history.push("/rentals");
    }

    const getAnnouncements = () => {
        history.push('/announcements');
    }

    function logOut() {
        LOCAL_STORAGE.clearToken();
        history.push('/');
      }

    const createRent = (numberOfAppliances, date) => {
        const applianceId = applianceItem['serial_number'];
        const reqBody = {
            student: {
                id: parseInt(LOCAL_STORAGE.getStudentId())
            },
            appliance: {
                "serial_number": applianceId
            },
            "selected_end_date": date,
            "number_of_appliances": parseInt(numberOfAppliances),
            "status": "RENTED"
        };

        api.post(`http://localhost:9852/api/v1/appliances/${applianceId}/rent`, reqBody)
            .then(() => {
                hideRentDialog();
                getAppliances();
            });
    }

    return (
        <div className="page student-page">
            <h2>Student Page</h2>
            <table>
                <thead>
                    <tr>
                        <th>Serial Number</th>
                        <th>Model Name</th>
                        <th>Appliance Type</th>
                        <th>State</th>
                        <th>Price Per Day</th>
                        <th>Max Time</th>
                        <th>Available Appliances</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {appliances.map(({ appliance }) =>
                        <tr>
                            <td>{appliance && appliance['serial_number']}</td>
                            <td>{appliance && appliance['model_name']}</td>
                            <td>{appliance && appliance['appliance_type'].name}</td>
                            <td>{appliance && appliance['state']}</td>
                            <td>{appliance && appliance['price_per_day']}</td>
                            <td>{appliance && appliance['max_time']}</td>
                            <td>{appliance && appliance['available_appliances']}</td>
                            <td>
                                <button className="btn-small btn-small-a" onClick={() => showRent(appliance)}>Rent</button>
                            </td>
                        </tr>
                    )}
                </tbody>
            </table>
            <button className="btn btn-medium btn-medium-a" onClick={getStudentRentals}> My Rentals </button>
            <button className="btn btn-medium btn-medium-a" onClick={getAnnouncements}> Announcements </button>
            <button className="btn btn-medium btn-medium-a btn-medium-a-red" onClick={logOut}>Log out</button>
            <Rodal visible={isEditVisible} onClose={hideRentDialog} closeOnEsc={true} width={500} height={400}>
                <Rent appliance={applianceItem} createRent={createRent} />
            </Rodal>

        </div>
    );
}

export default Student;