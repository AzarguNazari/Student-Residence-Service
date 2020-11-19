import React, { useEffect, useState } from "react";

import 'rodal/lib/rodal.css';
import './page.css';
import './TerminatedRents.css';
import api from "../api";
import { format } from "date-fns";
import get from "lodash/get";

const TerminatedRents = () => {
    const [rents, setRents] = useState([]);

    const getAppliances = () => {
        api.get("http://localhost:9852/api/v1/appliances/rent", {
            params: { "status": "TERMINATED" }
        }).then(({ data }) => setRents(data['rents']));
    };

    useEffect(() => {
        getAppliances();
    }, []);

    const getDate = (timestamp) =>
        format(new Date(Date.parse(timestamp)), "dd/MM/yyyy");

    const getName = (rent) => {
        const { "first_name": firstName, "last_name": lastName } = get(rent, ["student", "User"], {});
        return firstName + " " + lastName;
    };

    return (
        <div className="page page-container">
            <h2>Terminated Rents</h2>
            {rents.length > 0 && <table>
                <thead>
                    <tr>
                        <th>Rented By</th>
                        <th>Cost</th>
                        <th>Rent Terminated on Date</th>
                        <th>Number of Appliances</th>
                    </tr>
                </thead>
                <tbody>
                    {rents.map(({ rent }) =>
                        <tr>
                            <td>{rent && getName(rent)}</td>
                            <td>{rent && rent["rent_amount"]}</td>
                            <td>{rent && getDate(rent["actual_end_date"])}</td>
                            <td>{rent && rent["number_of_appliances"]}</td>
                        </tr>
                    )}
                </tbody>
            </table>}
            {rents.length === 0 && <h3 style={{ "text-align": "center" }}>No Terminated Rents! 😬</h3>}
            <button className="btn btn-medium btn-medium-a back"> <a style={{ "text-decoration": "none", "color": "#fff" }} href="/admin">Go Back</a> </button>
        </div>
    );
}

export default TerminatedRents;