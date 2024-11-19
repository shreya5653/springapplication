import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./WorkerForm.css"; // Import the CSS file

const WorkerForm = ({ token }) => {
  const [worker, setWorker] = useState({
    firstName: "",
    lastName: "",
    email: "",
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setWorker((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/workers/add", worker, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(() => navigate("/workers"))
      .catch((error) => console.error("Error adding worker:", error));
  };

  return (
    <div className="worker-form-container">
      <form onSubmit={handleSubmit} className="worker-form">
        <h2 className="form-title">Add Worker</h2>
        <div className="form-group">
          <label htmlFor="firstName" className="form-label">
            First Name
          </label>
          <input
            id="firstName"
            name="firstName"
            value={worker.firstName}
            onChange={handleChange}
            placeholder="Enter First Name"
            className="form-input"
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="lastName" className="form-label">
            Last Name
          </label>
          <input
            id="lastName"
            name="lastName"
            value={worker.lastName}
            onChange={handleChange}
            placeholder="Enter Last Name"
            className="form-input"
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="email" className="form-label">
            Email
          </label>
          <input
            id="email"
            name="email"
            value={worker.email}
            onChange={handleChange}
            placeholder="Enter Email"
            className="form-input"
            required
          />
        </div>
        <button type="submit" className="submit-button">
          Submit
        </button>
      </form>
    </div>
  );
};

export default WorkerForm;
