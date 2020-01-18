import React, { useState } from "react";
import "./Rent.css";

const Rent = ({ appliance, createRent }) => {
    const [items, setItems] = useState(appliance["available_appliances"]);
    const [errorMsg, setErrorMsg] = useState(null);
    const [date, setDate] = useState(null);

    const changeApplianceNumber = (number) => {
        if(number > appliance['available_appliances']) {
            setErrorMsg("Invalid number");
        } else {
            setItems(number);
        }
    };

    const onDateChange = (value) => {
        let timestamp = new Date(value).toJSON();
        setDate(timestamp);
    }

    const createRentAction = () => {
        createRent(items, date);
    }

    return (
        <div className="appliance-form">
            <h3>Rent appliance - {appliance['model_name']}</h3>

            <div className="input-container">
                <label id="number_of_appliance">Number of appliances: </label>
                <input type="number" onChange={(e) => changeApplianceNumber(e.target.value)} />
                <span>Please choose a number between 0 and {appliance["available_appliances"]}</span>
                {errorMsg && <span>{errorMsg}</span>}
            </div>
            <div className="input-container rent-date">
                <label id="available_appliance">End date of rental: </label>
                <input type="date" onChange={(e) => onDateChange(e.target.value)} />                
            </div>
            <div className="input-container">
                <button className="login" onClick={createRentAction}>Rent</button>
            </div>
        </div>
    )
}

export default Rent;