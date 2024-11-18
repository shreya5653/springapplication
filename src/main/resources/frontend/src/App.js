import React, { useState } from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/navbar";
import HomePage from "./pages/HomePage";
import ProfilePage from "./pages/Profilepage";
import LoginOrRegister from "./components/loginorregister";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [Profile, setProfile] = useState(null);
  const [token, setToken] = useState(null);
  return (
    <div>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route
          path="/profile"
          element={
            isLoggedIn ? (
              <ProfilePage profile={Profile} token={token} />
            ) : (
              <Navigate to="/login-or-register" replace />
            )
          }
        />
        <Route
          path="/login-or-register"
          element={
            <LoginOrRegister
              setIsLoggedIn={setIsLoggedIn}
              setToken={setToken}
              setProfile={setProfile}
            />
          }
        />
      </Routes>
    </div>
  );
}

export default App;
