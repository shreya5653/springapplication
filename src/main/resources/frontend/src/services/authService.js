import axios from "axios";

export const register = async (userData) => {
  try {
    const response = await fetch("http://localhost:8080/api/farmer/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(userData),
    });
    if (!response.ok) {
      const errorResponse = await response.json();
      throw new Error(errorResponse.message || "Registration failed");
    }
    return await response.text();
  } catch (error) {
    throw new Error(error.message || "Registration failed");
  }
};

export const login = async (userData) => {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/farmer/login",
      null,
      { params: { username: userData.username, password: userData.password } }
    );
    const token = response.data;
    localStorage.setItem("token", token);
    return token;
  } catch (error) {
    throw new Error(error.response?.data || "Login failed");
  }
};

export const fetchProfile = async (username) => {
  const token = localStorage.getItem("token");
  if (!token) throw new Error("Token is required to fetch profile");

  try {
    const response = await axios.get(
      "http://localhost:8080/api/farmer/profile",
      {
        headers: { Authorization: `Bearer ${token}` },
        params: { username }
      }
    );
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data || "Failed to fetch profile");
  }
};
