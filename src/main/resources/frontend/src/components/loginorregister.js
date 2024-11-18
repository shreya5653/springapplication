import React, { useState } from "react";
import { login, register } from "../services/authService";
import { useNavigate } from "react-router-dom";
import "./loginorregister.css";

const LoginOrRegister = ({ setIsLoggedIn, setProfile, setToken }) => {
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    email: "",
  });
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isLogin) {
        const response = await login({
          username: formData.username,
          password: formData.password,
        });

        setMessage(
          response === "Login successful"
            ? response
            : "Invalid credentials. Please try again."
        );

        if (response === "Login successful") {
          localStorage.setItem("username", formData.username);
          localStorage.setItem("token", response.token);
          setMessage("Login successful");
          setIsLoggedIn(true);
          setToken(response.token);
          setProfile(response.data);
          navigate("/profile");
        } else {
          setMessage("Invalid credentials. Please try again.");
        }
      } else {
        const response = await register({
          username: formData.username,
          password: formData.password,
          email: formData.email,
        });

        setMessage(
          typeof response === "string"
            ? response
            : "Registration failed. Try again."
        );
        if (response.includes("successful")) {
          setIsLogin(true);
        }
      }
    } catch (error) {
      setMessage(error.message || "An error occurred.");
    }
  };

  const toggleForm = () => {
    setIsLogin(!isLogin);
    setMessage("");
  };

  return (
    <div>
      <div className="form-container">
        <h2 className="form-heading">{isLogin ? "Login" : "Register"}</h2>
        <hr />
        <form onSubmit={handleSubmit} className="form">
          <input
            type="text"
            placeholder="Username"
            value={formData.username}
            onChange={(e) =>
              setFormData({ ...formData, username: e.target.value })
            }
            className="form-input"
            required
          />
          {!isLogin && (
            <input
              type="email"
              placeholder="Email"
              value={formData.email}
              onChange={(e) =>
                setFormData({ ...formData, email: e.target.value })
              }
              className="form-input"
              required
            />
          )}
          <input
            type="password"
            placeholder="Password"
            value={formData.password}
            onChange={(e) =>
              setFormData({ ...formData, password: e.target.value })
            }
            className="form-input"
            required
          />
          <button type="submit" className="form-button">
            {isLogin ? "Login" : "Register"}
          </button>
        </form>
        {message && <p className="form-message">{message}</p>}
        <button onClick={toggleForm} className="toggle-button">
          {isLogin
            ? "Don't have an account? Register here."
            : "Already have an account? Login here."}
        </button>
      </div>
    </div>
  );
};

export default LoginOrRegister;
