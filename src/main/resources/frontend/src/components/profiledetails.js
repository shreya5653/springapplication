// src/components/ProfileDetails.js
import React from "react";
import "./profiledetails.css";

function ProfileDetails({ fullName, email, password }) {
  return (
    <div className="profile-details">
      <h2>User Profile</h2>
      <div className="profile-info">
        <p>
          <strong>Full Name:</strong> {fullName}
        </p>
        <p>
          <strong>Email:</strong> {email}
        </p>
        <p>
          <strong>Password:</strong> {password}
        </p>
      </div>
    </div>
  );
}

export default ProfileDetails;
