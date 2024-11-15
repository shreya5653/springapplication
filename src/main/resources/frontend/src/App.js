// src/App.js
import React, {useState} from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/navbar";
import HomePage from "./pages/HomePage";
import ProfilePage from "./pages/Profilepage";
import LoginOrRegister from "./components/loginorregister";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <div>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route
          path="/profile"
          element={
            isLoggedIn ? (
              <ProfilePage />
            ) : (
              <Navigate to="/login-or-register" replace />
            )
          }
        />
        <Route
          path="/login-or-register"
          element={<LoginOrRegister setIsLoggedIn={setIsLoggedIn} />}
        />
      </Routes>
    </div>
  );
}

export default App;
