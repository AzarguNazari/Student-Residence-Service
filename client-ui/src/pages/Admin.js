import React, { useEffect, useState } from "react";
import get from "lodash/get";
import Rodal from "rodal";

import history from "../context/history";
import useAuth from "../context/auth";
import Appliance from "../components/appliance";
import LOCAL_STORAGE from "../local-storage";

import 'rodal/lib/rodal.css';
import './page.css';
import "./Admin.css";
import api from "../api";
import NewAppliance from "../components/new_appliance";
import RentHistory from "../components/rent_history";
import Filters from "../components/Filters";
import ReactPaginate from "react-paginate";

const Admin = () => {
  const { setAuthTokens } = useAuth();
  const [appliances, setAppliances] = useState([]);
  const [isEditVisible, setEditVisible] = useState(false);
  const [existingAppliance, setExistingAppliance] = useState({});
  const [type, setType] = useState("");
  const [applianceTypes, setApplianceTypes] = useState([]);
  const [rentHistory, setRentHistory] = useState([]);
  const [pagination, setPagination] = useState({});

  function logOut() {
    setAuthTokens();
    LOCAL_STORAGE.clearToken();
    history.push('/');
  }

  const getApplianceTypes = () => {
    api.get("http://localhost:9852/api/v1/appliances/types")
      .then(({ data }) => {
        setApplianceTypes(data);
      })
  }

  const getAppliances = () => {
    api.get("http://localhost:9852/api/v1/appliances", { params: { pageNumber: 0 } })
      .then(({ data }) => {

        const paginationObj = {
          pageNumber: data['pageNumber'],
          totalPages: data['totalPages'],
          totalElements: data['totalElements']
        }
        setPagination(paginationObj);
        const applianceList = get(data, ["appliances"], []);
        setAppliances(applianceList);
      });
  }

  useEffect(() => {
    getAppliances();
    getApplianceTypes();
  }, []);

  const getAppliance = (serialNumber) => {
    api.get(`http://localhost:9852/api/v1/appliances/${serialNumber}`)
      .then(({ data }) => {
        setExistingAppliance(data);
      });
  }

  const showEditAppliance = (serialNumber) => {
    getAppliance(serialNumber);
    setType("edit");
    setEditVisible(true);
  }

  const closeEditAppliance = () => {
    setEditVisible(false);
  }

  const updateExistingAppliance = (updatedAppliance, serialNumber) => {
    api.put(`http://localhost:9852/api/v1/appliances/${serialNumber}`, updatedAppliance)
      .then(() => {
        closeEditAppliance();
        getAppliances();
      });
  }

  const deleteAppliance = (serialNumber) => {
    api.delete(`http://localhost:9852/api/v1/appliances/${serialNumber}`)
      .then(() => {
        closeEditAppliance();
        getAppliances();
      });
  }

  const createNewAppliance = () => {
    setEditVisible(true);
    setType("new");
  }

  const addNewAppliance = (appliance) => {
    api.post(`http://localhost:9852/api/v1/appliances`, appliance)
      .then(() => {
        closeEditAppliance();
        getAppliances();
      })
  }

  const showRentHistory = (serialNumber) => {
    api.get(`http://localhost:9852/api/v1/appliances/${serialNumber}/rent`)
      .then(({ data }) => {
        setRentHistory(data['rents']);
        setType("history");
        setEditVisible(true);
      });
  };

  const showActiveRents = () => {
    history.push("/admin-active-rents");
  };

  const showTerminatedRents = () => {
    history.push("/admin-terminated-rents");
  };

  const navigateAnnouncements = () => {
    history.push("/announcements");
  };

  const selectFilters = () => {
    setType("filters");
    setEditVisible(true);
  };

  const applyFilter = (filter) => {
    api.get("http://localhost:9852/api/v1/appliances", { params: { ...filter } })
      .then(({ data }) => {
        const paginationObj = {
          pageNumber: data['pageNumber'],
          totalPages: data['totalPages'],
          totalElements: data['totalElements']
        }
        setPagination(paginationObj);
        const applianceList = get(data, ["appliances"], []);
        setAppliances(applianceList);
        setEditVisible(false);
      });
  }

  const onPageChange = ({ selected: pageNumber }) => {
    api.get("http://localhost:9852/api/v1/appliances", { params: { pageNumber } })
      .then(({ data }) => {
        const paginationObj = {
          pageNumber: data['pageNumber'],
          totalPages: data['totalPages'],
          totalElements: data['totalElements']
        }
        const applianceList = get(data, ["appliances"], []);
        setAppliances(applianceList);
        setEditVisible(false);
      });
  }

  return (
    <div className="page">
      <h2>Admin Page</h2>

      <button className="btn btn-medium btn-medium-a" onClick={selectFilters}>Filters</button>

      <table>
        <thead>
          <tr>
            <th>Serial Number</th>
            <th>Model Name</th>
            <th>Appliance Type</th>
            <th>State</th>
            <th>Price Per Day</th>
            <th>Max Time</th>
            <th>Available Appliances</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {appliances.map(({ appliance }) =>
            <tr>
              <td>{appliance && appliance['serial_number']}</td>
              <td>{appliance && appliance['model_name']}</td>
              <td>{appliance && appliance['appliance_type'].name}</td>
              <td>{appliance && appliance['state']}</td>
              <td>{appliance && appliance['price_per_day']}</td>
              <td>{appliance && appliance['max_time']}</td>
              <td>{appliance && appliance['available_appliances']}</td>
              <td>
                <button className="btn-small btn-small-a" onClick={() => showEditAppliance(appliance["serial_number"])}>Edit</button>
                <button className="btn-small btn-small-a" onClick={() => showRentHistory(appliance["serial_number"])}>Rent History</button>
                <button className="btn-small btn-small-a btn-small-a-red" onClick={() => deleteAppliance(appliance["serial_number"])}>Delete</button>
              </td>
            </tr>
          )}
        </tbody>
        <ReactPaginate
          previousLabel="<<"
          nextLabel=">>"
          containerClassName="pagination"
          pageCount={pagination['totalPages']}
          pageRangeDisplayed={2}
          marginPagesDisplayed={1}
          onPageChange={onPageChange}
        />
      </table>

      <button className="btn btn-medium btn-medium-a" onClick={createNewAppliance}>Add New Appliance</button>
      <button className="btn btn-medium btn-medium-a" onClick={showActiveRents}>Show all active rents</button>
      <button className="btn btn-medium btn-medium-a" onClick={showTerminatedRents}>Show all terminated rents</button>


      <button className="btn btn-medium btn-medium-a" onClick={navigateAnnouncements}> Announcements </button>

      <Rodal visible={isEditVisible} closeOnEsc={true} onClose={closeEditAppliance} customStyles={{ "overflow-y": "scroll" }} width={500} height={400}>
        {type === "edit" && existingAppliance.appliance && <Appliance appliance={existingAppliance.appliance} applianceTypes={applianceTypes} onUpdate={updateExistingAppliance} />}
        {type === "new" && <NewAppliance onUpdate={addNewAppliance} applianceTypes={applianceTypes} />}
        {type === "history" && <RentHistory rentHistory={rentHistory} />}
        {type === "filters" && <Filters applyFilter={applyFilter} applianceTypes={applianceTypes} />}
      </Rodal>

      <button className="btn btn-medium btn-medium-a btn-medium-a-red" onClick={logOut}>Log out</button>
    </div>
  );
}

export default Admin;