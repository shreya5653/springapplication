// src/pages/ProfilePage.js
import React, { useState } from 'react';
import ProfileDetails from '../components/profiledetails';
import AuthForm from '../components/AuthForm';

function ProfilePage() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [user, setUser] = useState(null);

  const handleAuthSuccess = (userData) => {
    setUser(userData);  
    setIsAuthenticated(true); 
  };

  return (
    <div>
      {!isAuthenticated ? (
        <AuthForm onAuthSuccess={handleAuthSuccess} />
      ) : (
        <ProfileDetails 
          fullName={user.fullName} 
          email={user.email} 
          password={user.password} 
        />
      )}

      
    </div>
  );
}

export default ProfilePage;
