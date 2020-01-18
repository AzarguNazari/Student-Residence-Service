import React, { useState } from "react";
import "./NewAppliance.css";

const NewAppliance = ({ onUpdate, applianceTypes }) => {

    const [appliance, setAppliance] = useState({});

    const updateAppliance = (key, value) => {
        const updatedAppliance = {
            ...appliance,
            [key]: value
        };

        setAppliance(updatedAppliance);
    }

    return (
        <div className="appliance-form">
            <h2>Add a new appliance</h2>
            <div className="input-container">
                <input required type="text" onChange={(e) => updateAppliance("model_name", e.target.value)} />
                <label id="name">Name: </label>
            </div>
            <div className="input-container">
                <input required type="text" onChange={(e) => updateAppliance("state", e.target.value)} />
                <label id="state">State: </label>
            </div>
            <div className="input-container">
                <input required type="number" onChange={(e) => updateAppliance("price_per_day", e.target.value)} />
                <label id="price_per_day">Price per day: </label>
            </div>
            <div className="input-container">
                <input required type="number" onChange={(e) => updateAppliance("max_time", e.target.value)} />
                <label id="max_time">Max Time: </label>
            </div>
            <div className="input-container">
                <input required type="number" onChange={(e) => updateAppliance("available_appliances", e.target.value)} />
                <label id="available_appliance">Available Appliance: </label>
            </div>
            <div className="input-container select">
                <select name="slct" id="slct" onChange={(e) => {
                    let applianceType = applianceTypes.find(at => at.id === parseInt(e.target.value));
                    updateAppliance("appliance_type", applianceType);
                }}>
                    <option selected disabled>Select a Type</option>
                    {applianceTypes.map(applianceType => {
                        return <option value={applianceType.id}>{applianceType.name}</option>;
                    })}
                </select>
            </div>

            <div className="input-container">
                <button className="login" onClick={() => onUpdate(appliance)}>Add</button>
            </div>
        </div>)
}

export default NewAppliance;