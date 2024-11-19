import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./WorkerList.css";

const WorkerList = ({ token }) => {
  const [workers, setWorkers] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/workers/list", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => setWorkers(response.data))
      .catch((error) => console.error("Error fetching workers:", error));
  }, [token]);

  const deleteWorker = (id) => {
    axios
      .delete(`http://localhost:8080/api/workers/delete/${id}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(() =>
        setWorkers((prev) => prev.filter((worker) => worker.id !== id))
      )
      .catch((error) => console.error("Error deleting worker:", error));
  };

  return (
    <div className="list">
      <h2>Worker List</h2>
      <button onClick={() => navigate("/add-worker")} className="add">Add Worker</button>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {workers.map((worker) => (
            <tr key={worker.id}>
              <td>{worker.id}</td>
              <td>{worker.firstName}</td>
              <td>{worker.lastName}</td>
              <td>{worker.email}</td>
              <td className="action-buttons">
                <button
                  className="update-button"
                  onClick={() => navigate(`/update-worker/${worker.id}`)}
                >
                  Update
                </button>
                <button
                  className="delete-button"
                  onClick={() => deleteWorker(worker.id)}
                >
                  Delete
                </button>
                <button
                  className="view-wages-button"
                  onClick={() => navigate(`/wages/${worker.id}`)}
                >
                  View Wages
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default WorkerList;
