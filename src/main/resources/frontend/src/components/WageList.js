import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import "./WageList.css";

const WageList = ({ token }) => {
  const { workerId } = useParams();
  const [wages, setWages] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/wages/worker/${workerId}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => setWages(response.data))
      .catch((error) => console.error("Error fetching wages:", error));
  }, [workerId, token]);

  const deleteWage = (id) => {
    axios
      .delete(`http://localhost:8080/api/wages/delete/${id}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(() => setWages((prev) => prev.filter((wage) => wage.id !== id)))
      .catch((error) => console.error("Error deleting wage:", error));
  };

  return (
    <div className="wage-list-container">
      <h2 className="wage-list-title">Wages for Worker ID: {workerId}</h2>
      <button
        className="add-wage-button"
        onClick={() => navigate(`/add-wage/${workerId}`)}
      >
        Add Wage
      </button>
      <table className="wage-table">
        <thead>
          <tr>
            <th>Date</th>
            <th>Amount</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {wages.map((wage) => (
            <tr key={wage.id}>
              <td>{wage.date}</td>
              <td>{wage.amount}</td>
              <td className="action-buttons">
                <button
                  className="update-button"
                  onClick={() => navigate(`/update-wage/${workerId}/${wage.id}`)}
                >
                  Update
                </button>
                <button
                  className="delete-button"
                  onClick={() => deleteWage(wage.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default WageList;
