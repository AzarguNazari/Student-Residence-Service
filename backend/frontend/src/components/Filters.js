import React, { useState } from "react";

const Filters = ({ applyFilter, applianceTypes }) => {
    const [filter, setFilter] = useState({});

    const updateFilter = (key, value) => {
        const updatedFilter = {
            ...filter,
            [key]: value
        };

        setFilter(updatedFilter);
    }

    return (
        <div className="filters appliance-form">

            <div className="input-container">
                <label>Filter by model name</label>
                <input type="text" onChange={(e) => updateFilter("model-name", e.target.value)}></input>
            </div>

            <div className="select input-container">
                <select name="slct" id="slct" onChange={(e) => {
                    updateFilter("type", parseInt(e.target.value));
                }}>
                    <option selected disabled>Filter by Appliance Type</option>
                    {applianceTypes.map(applianceType => {
                        return <option value={applianceType.id}>{applianceType.name}</option>;
                    })}
                </select>
            </div>

            <div className="select input-container">
                <select name="slct" id="slct" onChange={(e) => {
                    updateFilter("state", e.target.value);
                }}>
                    <option selected disabled>Filter by Status</option>
                    <option value="broken"> Broken</option>
                    <option value="available">Available</option>
                </select>
            </div>

            <div className="input-container">
                <button className="login" onClick={() => applyFilter(filter)}>Apply filter</button>
            </div>

        </div>
    )
}

export default Filters;