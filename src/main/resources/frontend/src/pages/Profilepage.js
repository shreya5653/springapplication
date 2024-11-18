import React, { useEffect, useState } from "react";
import "./Profilepage.css";
import { fetchProfile } from "../services/authService";

const ProfilePage = () => {
  const [profile, setProfile] = useState(null);
  const [error, setError] = useState("");
  const username = localStorage.getItem("username");

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      setError("No token found. Please login again.");
      return;
    }

    const fetchData = async () => {
      try {
        const data = await fetchProfile(username);
        setProfile(data);
      } catch (err) {
        setError(err.message || "Failed to fetch profile.");
      }
    };

    fetchData();
  }, [username]);

  if (error) return <div>{error}</div>;
  if (!profile) return <div>Loading profile...</div>;

  return (
    <div className="profile-container">
      <h2>Profile</h2>
      <p>Username: {profile.username}</p>
      <p>Email: {profile.email}</p>
      <p>Password: {profile.password}</p>
    </div>
  );
};

export default ProfilePage;
