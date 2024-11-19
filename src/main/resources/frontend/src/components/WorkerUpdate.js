import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import "./WorkerUpdate.css"; // Import CSS for styling

const WorkerUpdate = ({ token }) => {
  const [worker, setWorker] = useState({ firstName: "", lastName: "", email: "" });
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/workers/${id}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => setWorker(response.data))
      .catch((error) => console.error("Error fetching worker details:", error));
  }, [id, token]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setWorker((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .put("http://localhost:8080/api/workers/update", worker, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(() => navigate("/workers"))
      .catch((error) => console.error("Error updating worker:", error));
  };

  return (
    <form className="worker-form" onSubmit={handleSubmit}>
      <h2>Update Worker</h2>
      <input
        name="firstName"
        value={worker.firstName}
        onChange={handleChange}
        placeholder="First Name"
        required
      />
      <input
        name="lastName"
        value={worker.lastName}
        onChange={handleChange}
        placeholder="Last Name"
        required
      />
      <input
        name="email"
        value={worker.email}
        onChange={handleChange}
        placeholder="Email"
        required
      />
      <button type="submit" className="submit-btn">Update</button>
    </form>
  );
};

export default WorkerUpdate;
