import React, { useEffect, useState } from "react";
import { fetchProfile } from "../services/authService";
import "./Profilepage.css";

const Profile = () => {
  const [profile, setProfile] = useState(null);
  const [error, setError] = useState("");
  const username = localStorage.getItem("username");

  useEffect(() => {
    const fetchdata = async () => {
      try {
        const data = await fetchProfile(username);
        setProfile(data);
      } catch (err) {
        setError(err.response?.data || "An error occurred while fetching profile.");
      }
    };

    if (username) {
      fetchdata();
    } else {
      setError("Username is missing. Please log in again.");
    }
  }, [username]);

  return (
    <div className="profile-container">
      <h2>Profile Page</h2>
      {error && <p style={{ color: "red" }}>Error: {error}</p>}
      {profile ? (
        <div>
          <p><strong>Username:</strong> {profile.username}</p>
          <p><strong>Email:</strong> {profile.email}</p>
          <p><strong>Password:</strong> {profile.password}</p>
        </div>
      ) : (
        <p>Loading profile...</p>
      )}
    </div>
  );  
};

export default Profile;
