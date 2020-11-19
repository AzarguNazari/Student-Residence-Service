import React, { Fragment, useState } from "react";

const UpdateRent = ({ rent, updateRent }) => {
    const [date, setDate] = useState(null);

    const onDateChange = (value) => {
        let timestamp = new Date(value).toJSON();
        setDate(timestamp);
    }

    const updateRentAction = () => {
        const updatedRent = {
            ...rent,
            "selected_end_date": date
        }
        updateRent(updatedRent);
    }

    return (
        <div className="appliance-form">
            <h3>Update appliance - {rent['appliance'] && rent['appliance']['model_name']}</h3>
            <div className="input-container">
                <label id="available_appliance">End date of rental: </label>
                <input type="date" onChange={(e) => onDateChange(e.target.value)} />                
            </div>
            <button onClick={updateRentAction}>Update</button>
        </div>
    )
}

export default UpdateRent;