import React, { useState } from "react";

const Appliance = ({ appliance, applianceTypes, onUpdate }) => {
    const [applianceItem, setApplianceItem] = useState(appliance);

    const updateAppliance = (key, value) => {
        const updatedAppliance = {
            ...applianceItem,
            [key]: value
        };
        setApplianceItem(updatedAppliance);
    }

    return (
        <div className="appliance-form">
            <div className="input-container">
                <input type="text" value={applianceItem && applianceItem["model_name"]} onChange={(e) => updateAppliance("model_name", e.target.value)} />
                <label id="name">Name: </label>
            </div>
            <div className="input-container">
                <input type="text" value={applianceItem && applianceItem.state} onChange={(e) => updateAppliance("state", e.target.value)} />
                <label id="state">State: </label>
            </div>
            <div className="input-container">
                <input type="number" value={applianceItem && applianceItem["price_per_day"]} onChange={(e) => updateAppliance("price_per_day", parseInt(e.target.value))} />
                <label id="price_per_day">Price per day: </label>
            </div>
            <div className="input-container">
                <input type="number" value={applianceItem && applianceItem["max_time"]} onChange={(e) => updateAppliance("max_time", parseInt(e.target.value))} />
                <label id="max_time">Max Time: </label>
            </div>
            <div className="input-container">
                <input type="number" value={applianceItem && applianceItem["available_appliances"]} onChange={(e) => updateAppliance("available_appliances", parseInt(e.target.value))} />
                <label id="available_appliance">Available Appliance: </label>
            </div>

            <div className="input-container select">
                <select name="slct" id="slct" onChange={(e) => {
                    let applianceType = applianceTypes.find(at => at.id === parseInt(e.target.value));
                    updateAppliance("appliance_type", applianceType);
                }}>
                    {applianceTypes.map(applianceType => {
                        return <option value={applianceType.id}>{applianceType.name}</option>;
                    })}
                </select>
            </div>

            <div className="input-container">
                <button className="login" onClick={() => onUpdate(applianceItem, applianceItem["serial_number"])}>Update</button>                
            </div>
        </div>
    )
}

export default Appliance;