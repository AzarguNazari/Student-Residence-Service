import React, { useEffect, useState } from "react";
import Rodal from "rodal";
import { format } from "date-fns";

import 'rodal/lib/rodal.css';
import './page.css';
import api from "../api";
import LOCAL_STORAGE from "../local-storage";
import UpdateRent from "../components/update_rent";

const StudentRental = () => {
    const [rents, setRents] = useState([]);
    const [rentItem, setRentItem] = useState({});
    const [isEditVisible, setEditVisible] = useState(false);

    const getAppliances = () => {
        api.get("http://localhost:9852/api/v1/appliances/rent", {
            params: {
                "student-id": parseInt(LOCAL_STORAGE.getStudentId()),
                "status": "RENTED"
            }
        })
            .then(({ data }) => {
                setRents(data['rents']);
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

    const updateRentItem = (rent) => {
        setRentItem(rent);
        showRentDialog();
    }

    const updateRent = (rent) => {
        const applianceId = rent['appliance']['serial_number'];
        const rentId = rent['serial_number'];

        api.put(`http://localhost:9852/api/v1/appliances/${applianceId}/rent/${rentId}`, rent)
            .then(() => {
                hideRentDialog();
                getAppliances();
            });
    }

    const terminateRent = (rent) => {
        const applianceId = rent['appliance']['serial_number'];
        const rentId = rent['serial_number'];

        const updatedRent = {
            ...rent,
            "status": "TERMINATED"
        }

        api.put(`http://localhost:9852/api/v1/appliances/${applianceId}/rent/${rentId}`, updatedRent)
            .then(() => {
                hideRentDialog();
                getAppliances();
            });
    }

    const getDate = (timestamp) =>
        format(new Date(Date.parse(timestamp)), "dd/MM/yyyy");

    return (
        <div className="page student-page">
            <h2>Rentals Page</h2>

            {rents.length > 0 && <table>
                <thead>
                    <tr>
                        <th>Appliance Name</th>
                        <th>Rented on</th>
                        <th>Rent Expires on</th>
                        <th>Number of Appliances rented</th>
                        <th>Rent Amount</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {rents.map(({ rent }) =>
                        <tr>
                            <td>{rent && rent['appliance']['model_name']}</td>
                            <td>{rent && getDate(rent['creation_date'])}</td>
                            <td>{rent && getDate(rent['selected_end_date'])}</td>
                            <td>{rent && rent['number_of_appliances']}</td>
                            <td>{rent && rent['rent_amount']}</td>
                            <td>
                                <button className="btn-small btn-small-a" onClick={() => updateRentItem(rent)}>Update</button>
                                <button className="btn-small btn-small-a" onClick={() => terminateRent(rent)}>Terminate</button>
                            </td>
                        </tr>
                    )}
                </tbody>
            </table>}

            <Rodal visible={isEditVisible} onClose={hideRentDialog} closeOnEsc={true}>
                <UpdateRent rent={rentItem} updateRent={updateRent} />
            </Rodal>
            
            {rents.length === 0 && <h3 style={{ "text-align": "center" }}>No Active Rents! 😬</h3>}
            <button className="btn btn-medium btn-medium-a back"> <a style={{ "text-decoration": "none", "color": "#fff" }} href="/student">Go Back</a> </button>
        </div>
    );
}

export default StudentRental;