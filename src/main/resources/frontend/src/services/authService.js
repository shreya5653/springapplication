import axios from "axios";

export const register = async (userData) => {
  try {
    const response = await fetch("http://localhost:8080/api/farmer/signup", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(userData),
    });

    if (!response.ok) {
      const errorResponse = await response.json(); // Parse JSON
      console.log("Backend error response:", errorResponse); // Log the error response for debugging
      throw new Error(errorResponse.message || "Registration failed"); // Use message field
    }

    return await response.text(); // For successful response
  } catch (error) {
    console.error("Error during registration:", error.message || error);
    throw error.message || "Registration failed"; // Return the error message
  }
};

export const login = async (userData) => {
  if (!userData || !userData.username || !userData.password) {
    throw new Error("Missing username or password");
  }
  try {
    const response = await axios.post(
      "http://localhost:8080/api/farmer/login",
      null,
      { params: { username: userData.username, password: userData.password } }
    );
    console.log("login successfull ",response.data)
    return response.data;
  } catch (error) {
    console.error("Error during login:", error);
    throw new Error("Login failed");
  }
};

export const fetchProfile = async (token) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/farmer/profile`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    return response.data;
  } catch (error) {
    throw error.response ? error.response.data : "Failed to fetch profile";
  }
};
