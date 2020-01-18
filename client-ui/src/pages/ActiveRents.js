import React, { useEffect, useState } from "react";

import 'rodal/lib/rodal.css';
import './page.css';
import api from "../api";
import { format } from "date-fns";

const ActiveRents = () => {
    const [rents, setRents] = useState([]);

    const getAppliances = () => {
        api.get("http://localhost:9852/api/v1/appliances/rent", {
            params: {
                "status": "RENTED"
            }
        })
            .then(({ data }) => {
                setRents(data);
            });
    }

    useEffect(() => {
        getAppliances();
    }, []);

    const getDate = (timestamp) =>
        format(new Date(Date.parse(timestamp)), "dd/MM/yyyy");

    return (
        <div className="page page-container">
            <h2>Active Rents</h2>
            {rents.length > 0 && <table>
                <thead>
                    <tr>
                        <th>Rented By</th>
                        <th>Cost</th>
                        <th>Rent End Date</th>
                        <th>Number of Appliances</th>
                    </tr>
                </thead>
                <tbody>
                    {rents.map((rent) =>
                        <tr>
                            <td>Me</td>
                            <td>{rent && rent["rent_amount"]}</td>
                            <td>{rent && getDate(rent["selected_end_date"])}</td>
                            <td>{rent && rent["number_of_appliances"]}</td>
                        </tr>
                    )}
                </tbody>
            </table>}
            {rents.length === 0 && <h3 style={{ "text-align": "center" }}>No Active Rents! 😬</h3>}
                <button className="btn btn-medium btn-medium-a back"> <a style={{ "text-decoration": "none", "color": "#fff" }} href="/admin">Go Back</a> </button>
        </div>
    );
}

export default ActiveRents;