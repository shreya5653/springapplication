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
    throw new Error(error.message || "Registration failed"); // Return the error message
  }
};

export const login = async (userData) => {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/farmer/login",
      null,
      { params: { username: userData.username, password: userData.password } }
    );

    const token = response.data; // Assuming the response contains a token
    localStorage.setItem("token", token); // Save token to localStorage
    console.log("Login successful", token);
    return token; // Return the token for further use
  } catch (error) {
    console.error("Error during login:", error.message || error);
    throw new Error(error.response?.data || "Login failed");
  }
};

export const fetchProfile = async () => {
  try {
    const token = localStorage.getItem("token");
    const username = localStorage.getItem("username"); // Make sure username is set
    
    if (!username) {
      throw new Error("Username not found");
    }

    const response = await axios.get(`http://localhost:8080/api/farmer/profile`, {
      params: {
        username: username, // Pass username as a query parameter
      },
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    console.log("Profile data:", response.data);
    return response.data; // Return the profile data
  } catch (error) {
    console.error("Error fetching profile:", error.message || error);
    throw new Error(error.response?.data || "Error fetching profile");
  }
};
