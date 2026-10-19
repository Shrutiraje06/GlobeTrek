import React from "react";
import { Routes, Route } from "react-router-dom";

import Home from "../pages/Home";
import Login from "../pages/Login";
import Register from "../pages/Register";
import About from "../pages/About";
import Contact from "../pages/Contact";
import Packages from "../pages/Packages";
import Booking from "../pages/Booking";
import BookingHistory from "../pages/BookingHistory";
import Payment from "../pages/Payment";
import PackageDetails from "../pages/PackageDetails";
import Dashboard from "../admin/Dashboard";
import ManagePackages from "../admin/ManagePackages";
import ManageBookings from "../admin/ManageBookings";
import ManageCustomer from "../admin/ManageCustomer";
import AIPlanner from "../pages/AIPlanner";
import Wishlist from "../pages/Wishlist";

function AppRoutes() {
  return (
    <Routes>

      <Route path="/" element={<Home />} />

      <Route path="/login" element={<Login />} />

      <Route path="/register" element={<Register />} />

      <Route path="/packages" element={<Packages />} />

      <Route path="/package/:id" element={<PackageDetails />} />

      <Route path="/booking/:id" element={<Booking />} />

      <Route path="/payment/:bookingId" element={<Payment />} />

      <Route path="/booking-history" element={<BookingHistory />} />

      <Route path="/about" element={<About />} />

      <Route path="/contact" element={<Contact />} />
      <Route path="/admin/dashboard" element={<Dashboard />} />

<Route path="/admin/packages" element={<ManagePackages />} />

<Route path="/admin/bookings" element={<ManageBookings />} />

<Route path="/admin/customers" element={<ManageCustomer />} />
<Route path="/ai" element={<AIPlanner />} />
<Route path="/wishlist" element={<Wishlist />} />

    </Routes>
  );
}

export default AppRoutes;