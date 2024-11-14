// src/components/AuthForm.js
import React, { useState } from "react";
import "./AuthForm.css";

function AuthForm({ onAuthSuccess }) {
  const [formData, setFormData] = useState({
    fullName: "",
    email: "",
    password: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onAuthSuccess(formData);
  };

  return (
    <div className="auth-form-container">
      <h2 className="auth-form-title">Sign In</h2>
      <hr />
      <form className="auth-form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="fullName" className="form-label">
            Full Name:
          </label>
          <input
            type="text"
            id="fullName"
            placeholder="Enter Your Name"
            name="fullName"
            className="form-input"
            value={formData.fullName}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="email" className="form-label">
            Email:
          </label>
          <input
            type="email"
            id="email"
            name="email"
            placeholder="Enter Your E-mail"
            className="form-input"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="password" className="form-label">
            Password:
          </label>
          <input
            type="password"
            id="password"
            name="password"
            placeholder="Enter Your Password"
            className="form-input"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>

        <button type="submit" className="form-submit-btn">
          Submit
        </button>
      </form>
    </div>
  );
}

export default AuthForm;
