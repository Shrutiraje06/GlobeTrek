import React, { useEffect, useState } from "react";
import Sidebar from "../components/Sidebar";
import userService from "../services/userService";

const ManageCustomer = () => {

  const [customers, setCustomers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadCustomers();
  }, []);

  const loadCustomers = async () => {
    try {
      const response = await userService.getAllUsers();
      setCustomers(response.data);
      setLoading(false);
    } catch (error) {
      console.log(error);
      setLoading(false);
    }
  };

  return (
    <div className="container-fluid">
      <div className="row">

        <div className="col-md-3">
          <Sidebar />
        </div>

        <div className="col-md-9">

          <h2 className="mt-4">Manage Customers</h2>

          {loading ? (
            <p>Loading...</p>
          ) : (
            <table className="table table-striped table-bordered">
              <thead className="table-dark">
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Mobile</th>
                </tr>
              </thead>
              <tbody>
                {customers.map((customer) => (
                  <tr key={customer.id}>
                    <td>{customer.id}</td>
                    <td>{customer.firstName} {customer.lastName}</td>
                    <td>{customer.email}</td>
                    <td>{customer.mobile}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}

        </div>

      </div>
    </div>
  );
};

export default ManageCustomer;