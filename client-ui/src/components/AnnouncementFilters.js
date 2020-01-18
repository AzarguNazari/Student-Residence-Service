import React, { useState } from "react";

const AnnouncementFilters = ({ applyFilter, applianceTypes }) => {
    const [announcementFilter, setAnnouncementFilter] = useState({});

    const onDateChange = (date) => {
        let timestamp = new Date(date).toJSON();
        updateFilter('date', timestamp);
    }

    const updateFilter = (key, value) => {
        const updatedFilter = {
            ...announcementFilter,
            [key]: value
        };

        setAnnouncementFilter(updatedFilter);
    }

    return (
        <div className="filters appliance-form">

            <div className="input-container">
                <input type="number" onChange={(e) => updateFilter("external-id", parseInt(e.target.value))}></input>
                <label>Filter by External ID</label>
            </div>

            <div className="input-container">
                <input type="date" onChange={(e) => onDateChange(e.target.value)} />
                <label id="available_appliance">Date: </label>
            </div>

            <div className="input-container">
                <button className="login" onClick={() => applyFilter(announcementFilter)}>Apply filter</button>
            </div>
        </div>
    )
}

export default AnnouncementFilters;