import React from "react";
import { Link, useLocation } from "react-router-dom";

const Sidebar = () => {

  const location = useLocation();

  const isActive = (path) => location.pathname === path;

  return (
    <div className="bg-dark text-white p-3" style={{ minHeight: "100vh" }}>

      <h4 className="mb-4">Admin Panel</h4>

      <ul className="nav flex-column">

        <li className="nav-item mb-2">
          <Link
            className={`nav-link text-white ${isActive("/admin/dashboard") ? "fw-bold text-warning" : ""}`}
            to="/admin/dashboard"
          >
            Dashboard
          </Link>
        </li>

        <li className="nav-item mb-2">
          <Link
            className={`nav-link text-white ${isActive("/admin/packages") ? "fw-bold text-warning" : ""}`}
            to="/admin/packages"
          >
            Manage Packages
          </Link>
        </li>

        <li className="nav-item mb-2">
          <Link
            className={`nav-link text-white ${isActive("/admin/bookings") ? "fw-bold text-warning" : ""}`}
            to="/admin/bookings"
          >
            Manage Bookings
          </Link>
        </li>

        <li className="nav-item mb-2">
          <Link
            className={`nav-link text-white ${isActive("/admin/customers") ? "fw-bold text-warning" : ""}`}
            to="/admin/customers"
          >
            Manage Customers
          </Link>
        </li>

      </ul>

    </div>
  );
};

export default Sidebar;