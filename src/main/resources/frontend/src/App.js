import React, { useState } from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/navbar";
import HomePage from "./pages/HomePage";
import ProfilePage from "./pages/Profilepage";
import LoginOrRegister from "./components/loginorregister";
import WorkerList from "./components/WorkerList";
import WorkerForm from "./components/WorkerForm";
import WorkerUpdate from "./components/WorkerUpdate";
import WageList from "./components/WageList";
import WageForm from "./components/WageForm";

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
        <Route
          path="/workers"
          element={
            isLoggedIn ? (
              <WorkerList token={token} />
            ) : (
              <Navigate to="/login-or-register" replace />
            )
          }
        />
        <Route
          path="/add-worker"
          element={
            isLoggedIn ? (
              <WorkerForm token={token} />
            ) : (
              <Navigate to="/login-or-register" replace />
            )
          }
        />
        <Route
          path="/update-worker/:id"
          element={
            isLoggedIn ? (
              <WorkerUpdate token={token} />
            ) : (
              <Navigate to="/login-or-register" replace />
            )
          }
        />
        <Route
          path="/wages/:workerId"
          element={
            isLoggedIn ? (
              <WageList token={token} />
            ) : (
              <Navigate to="/login-or-register" replace />
            )
          }
        />
        <Route
          path="/add-wage/:workerId"
          element={
            isLoggedIn ? (
              <WageForm token={token} />
            ) : (
              <Navigate to="/login-or-register" replace />
            )
          }
        />
        <Route
          path="/update-wage/:workerId/:wageId"
          element={
            isLoggedIn ? (
              <WageForm token={token} />
            ) : (
              <Navigate to="/login-or-register" replace />
            )
          }
        />
      </Routes>
    </div>
  );
}

export default App;
