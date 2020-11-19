import React, { useState, Fragment, useEffect } from "react";
import api from "../api";
import get from "lodash/get";


const NewAnnouncement = ({ onAddAnnouncement }) => {

    const [announcement, setAnnouncement] = useState({});
    const [appliances, setAppliances] = useState([]);
    const [announcementTypes, setAnnouncementTypes] = useState([]);

    const getAnnouncementTypes = () => {
        api.get("http://localhost:9852/api/v1/bulletinboard/types")
            .then(({ data }) => {
                setAnnouncementTypes(data);
            })
    };

    const updateAnnouncement = (key, value) => {
        const updatedAnnouncement = {
            ...announcement,
            "user": {
                "id": 1
            },
            [key]: value
        };
        setAnnouncement(updatedAnnouncement);
    };

    useEffect(() => {
        getAnnouncementTypes();
    }, []);

    const getAppliances = () => {
        api.get("http://localhost:9852/api/v1/appliances", { params: { pageNumber: 0 } })
            .then((data) => {
                const applianceList = get(data, ["data", "appliances"], []);
                setAppliances(applianceList);
            });
    }
    const updateAnnouncementType = (typeId) => {
        let announcementType = announcementTypes.find(at => at.id === parseInt(typeId));
        updateAnnouncement("announcement_type", announcementType);
        if (announcementType.name === "New HA Available") {
            getAppliances();
        } else {
            const { "appliance_serial_number": serialNumber, ...rest } = announcement;
            setAnnouncement(rest);
            updateAnnouncement("announcement_type", announcementType);

        }
    };

    return (
        <div className="form appliance-form">
            <div className="input-container">
                <input type="text" onChange={(e) => updateAnnouncement("description", e.target.value)} />
                <label id="name">Description: </label>
            </div>
            <div className="input-container select">
                <select name="slct" id="slct" onChange={(e) => updateAnnouncementType(parseInt(e.target.value))}>
                    <option selected disabled>Select a Type</option>
                    {announcementTypes.map(announcementType => {
                        return <option value={announcementType.id}>{announcementType.name}</option>;
                    })}
                </select>
            </div>
            <div className="input-container select">
                <select name="slct" id="slct" onChange={(e) => updateAnnouncement("priority", e.target.value)}>
                    <option selected disabled>Select a Priority</option>
                    <option value="LOW">Low</option>
                    <option value="MEDIUM">Medium</option>
                    <option value="HIGH">High</option>
                </select>
            </div>

            {announcement &&
                announcement['announcement_type'] &&
                announcement['announcement_type'].name === "New HA Available" &&
                <div className="input-container select">
                    <select name="slct" id="slct" onChange={(e) => updateAnnouncement("appliance_serial_number", parseInt(e.target.value))}>
                    <option selected disabled>Select an Appliance</option>
                        {appliances.map(({ appliance }) =>
                            <option value={appliance['serial_number']}>{appliance['model_name']}</option>
                        )}
                    </select>
                </div>}
                <div className="input-container">
                    <button className="login" onClick={() => onAddAnnouncement(announcement)}>Add</button>
                </div>
        </div>)
}

export default NewAnnouncement;