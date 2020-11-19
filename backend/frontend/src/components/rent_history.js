import React, { Fragment } from "react";
import { format } from "date-fns";
import get from "lodash/get";

import "./RentHistory.css";

const RentHistory = ({ rentHistory }) => {

    const terminatedRents = rentHistory.filter(({ rent }) => rent.status === "TERMINATED");
    const activeRents = rentHistory.filter(rent => rent.status === "RENTED");

    const getDate = (timestamp) => {
        console.log('timestamp---', timestamp);
        format(new Date(Date.parse(timestamp)), "dd/MM/yyyy");
    }

    const getName = (rent) => {
        const { "first_name": firstName, "last_name": lastName } = get(rent, ["student", "User"], {});
        return firstName + " " + lastName;
    };

    return (
        <Fragment>
            <h2>Rent History for the appliance</h2>

            <div className="active-rents">
                <h3>Active Rents</h3>
                {activeRents.map(({ rent }) => {
                    return (<ul>
                        <li>Rented by: {getName(rent)}</li>
                        <li>Rent Cost: {rent["rent_amount"]}</li>
                        <li>Will terminate on: {getDate(rent["selected_end_date"])} </li>
                        <li>Number of appliances:{rent["number_of_appliances"]} </li>
                    </ul>)
                })}
            </div>

            <div className="terminated-rents">
                <h3>Terminated Rents</h3>
                {terminatedRents.map(({ rent }) => {
                    return (<ul>
                        <li>Rented by: {getName(rent)}</li>
                        <li>Rent Cost: {rent["rent_amount"]}</li>
                        <li>Number of appliances:{rent["number_of_appliances"]} </li>
                        <li>Terminated on: {getDate(rent["actual_end_date"])}</li>
                    </ul>)
                })}
            </div>
        </Fragment>
    )
}

export default RentHistory;