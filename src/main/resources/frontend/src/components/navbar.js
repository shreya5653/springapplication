// src/components/Navbar.js
import React from 'react';
import { Link } from 'react-router-dom';
import './navbar.css';

function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-links">
        <Link to="/">Home</Link>
        <Link to="/profile">Profile</Link>
      </div>
    </nav>
  );
}

export default Navbar;
