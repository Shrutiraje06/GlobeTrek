import React from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
  const token = localStorage.getItem("token");
  const rolesStr = localStorage.getItem("roles");
  const roles = rolesStr ? JSON.parse(rolesStr) : [];
  const isAdmin = roles.includes("ROLE_ADMIN");

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("roles");
    localStorage.removeItem("email");
    window.location.href = "/";
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <div className="container">

        <Link className="navbar-brand fw-bold" to="/">
          IndiaTour
        </Link>

        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbar"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbar">

          <ul className="navbar-nav ms-auto">

            <li className="nav-item">
              <Link className="nav-link" to="/">Home</Link>
            </li>

            <li className="nav-item">
              <Link className="nav-link" to="/packages">Packages</Link>
            </li>

            <li className="nav-item">
              <Link className="nav-link" to="/booking-history">My Booking</Link>
            </li>

            <li className="nav-item">
              <Link className="nav-link" to="/wishlist">Wishlist</Link>
            </li>

            <li className="nav-item">
  <Link className="nav-link" to="/ai">AI Planner</Link>
</li>

<li className="nav-item">
  <Link className="nav-link" to="/about">About</Link>
</li>
            <li className="nav-item">
              <Link className="nav-link" to="/contact">Contact</Link>
            </li>

            {isAdmin && (
              <li className="nav-item">
                <Link className="nav-link" to="/admin/packages">Admin</Link>
              </li>
            )}

            {token ? (
              <li className="nav-item">
                <button
                  className="btn btn-outline-light ms-2"
                  onClick={handleLogout}
                >
                  Logout
                </button>
              </li>
            ) : (
              <>
                <li className="nav-item">
                  <Link className="btn btn-warning ms-2" to="/login">Login</Link>
                </li>
                <li className="nav-item">
                  <Link className="btn btn-success ms-2" to="/register">Sign Up</Link>
                </li>
              </>
            )}

          </ul>

        </div>

      </div>
    </nav>
  );
}

export default Navbar;