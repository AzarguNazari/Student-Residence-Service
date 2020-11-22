import React, { useState, useEffect } from "react";
import api from "../api";

const AnnouncementFilters = ({ applyFilter, applianceTypes }) => {
    const [announcementFilter, setAnnouncementFilter] = useState({});
    const [admins, setAdmins] = useState([]);

    const onDateChange = (date) => {
        let timestamp = new Date(date).toJSON();
        updateFilter('creation-date', timestamp);
    }

    const updateFilter = (key, value) => {
        const updatedFilter = {
            ...announcementFilter,
            [key]: value
        };

        setAnnouncementFilter(updatedFilter);
    }

    const getAdmins = () => {
        api.get("http://localhost:9852/api/v1/bulletinboard/admins")
            .then(({ data }) => {
                setAdmins(data);
            })
    }

    useEffect(() => {
        getAdmins();
    }, []);
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

            <div className="input-container select">
                <select name="slct" id="slct" onChange={(e) => updateFilter("issuer-id", parseInt(e.target.value))}>
                    <option selected disabled>Select an Issuer</option>
                    {admins.map((admin) =>
                        <option value={admin['id']}>{admin['first_name'] + " " + admin['last_name']}</option>
                    )}
                </select>
            </div>

            <div className="input-container">
                <button className="login" onClick={() => applyFilter(announcementFilter)}>Apply filter</button>
            </div>

        </div>
    )
}

export default AnnouncementFilters;