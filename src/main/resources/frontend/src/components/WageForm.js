import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import "./WageForm.css";

const WageForm = ({ token }) => {
  const { workerId, wageId } = useParams();
  const [wage, setWage] = useState({ date: "", amount: "" });
  const navigate = useNavigate();

  useEffect(() => {
    if (wageId) {
      // Fetch existing wage details for editing
      axios
        .get(`http://localhost:8080/api/wages/${workerId}/${wageId}`, {
          headers: { Authorization: `Bearer ${token}` },
        })
        .then((response) => setWage(response.data))
        .catch((error) => console.error("Error fetching wage details:", error));
    }
  }, [workerId, wageId, token]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setWage((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const url = wageId
      ? `http://localhost:8080/api/wages/update`
      : `http://localhost:8080/api/wages/add/${workerId}`;
    axios
      .post(url, wage, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(() => navigate(`/wages/${workerId}`))
      .catch((error) => console.error("Error saving wage:", error));
  };

  return (
    <div className="wage-form-container">
      <form onSubmit={handleSubmit} className="wage-form">
        <h2>{wageId ? "Update Wage" : "Add Wage"}</h2>
        <div className="form-group">
          <label htmlFor="date">Date:</label>
          <input
            id="date"
            type="date"
            name="date"
            value={wage.date}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="amount">Amount:</label>
          <input
            id="amount"
            type="number"
            name="amount"
            value={wage.amount}
            onChange={handleChange}
            placeholder="Amount"
            required
          />
        </div>
        <button type="submit" className="submit-button">
          Save
        </button>
      </form>
    </div>
  );
};

export default WageForm;
