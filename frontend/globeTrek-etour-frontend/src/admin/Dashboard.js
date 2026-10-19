import React, { useEffect, useState } from "react";
import Sidebar from "../components/Sidebar";
import dashboardService from "../services/dashboardService";

const Dashboard = () => {

  const [stats, setStats] = useState(null);

  useEffect(() => {
    loadDashboard();
  }, []);

  const loadDashboard = async () => {
    try {
      const response = await dashboardService.getDashboard();
      setStats(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="container-fluid">
      <div className="row">

        <div className="col-md-3">
          <Sidebar />
        </div>

        <div className="col-md-9">

          <h2 className="mt-4">Admin Dashboard</h2>

          {!stats ? (
            <p className="mt-4">Loading...</p>
          ) : (
            <div className="row mt-4">

              <div className="col-md-3">
                <div className="card bg-primary text-white">
                  <div className="card-body">
                    <h5>Total Packages</h5>
                    <h2>{stats.totalPackages}</h2>
                  </div>
                </div>
              </div>

              <div className="col-md-3">
                <div className="card bg-success text-white">
                  <div className="card-body">
                    <h5>Total Bookings</h5>
                    <h2>{stats.totalBookings}</h2>
                  </div>
                </div>
              </div>

              <div className="col-md-3">
                <div className="card bg-warning text-dark">
                  <div className="card-body">
                    <h5>Total Customers</h5>
                    <h2>{stats.totalCustomers}</h2>
                  </div>
                </div>
              </div>

              <div className="col-md-3">
                <div className="card bg-dark text-white">
                  <div className="card-body">
                    <h5>Total Revenue</h5>
                    <h2>₹{stats.totalRevenue}</h2>
                  </div>
                </div>
              </div>

            </div>
          )}

        </div>

      </div>
    </div>
  );
};

export default Dashboard;