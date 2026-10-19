import React, { useEffect, useState } from "react";
import bookingService from "../services/bookingService";
import Sidebar from "../components/Sidebar";

const ManageBookings = () => {

  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadBookings();
  }, []);

  const loadBookings = async () => {
    try {
      const response = await bookingService.getAllBookings();
      setBookings(response.data);
      setLoading(false);
    } catch (error) {
      console.log(error);
      setLoading(false);
    }
  };

  const changeStatus = async (booking, newStatus) => {
    try {
      const updatedBooking = { ...booking, bookingStatus: newStatus };
      await bookingService.updateBooking(booking.bookingId, updatedBooking);
      loadBookings(); // refresh the table with the new status
    } catch (error) {
      console.log(error);
      alert("Failed to update booking status.");
    }
  };

  return (

    <div className="container-fluid">

      <div className="row">

        <div className="col-md-3">
          <Sidebar />
        </div>

        <div className="col-md-9">

          <h2 className="mt-4">Manage Bookings</h2>

          {loading ? (
            <p>Loading...</p>
          ) : bookings.length === 0 ? (
            <p>No bookings yet.</p>
          ) : (
            <table className="table table-bordered">

              <thead className="table-dark">
                <tr>
                  <th>ID</th>
                  <th>Customer</th>
                  <th>Package</th>
                  <th>Travel Date</th>
                  <th>Persons</th>
                  <th>Amount</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
              </thead>

              <tbody>
                {bookings.map((booking) => (
                  <tr key={booking.bookingId}>

                    <td>{booking.bookingId}</td>

                    <td>
                      {booking.customer?.firstName} {booking.customer?.lastName}
                      <br />
                      <small className="text-muted">{booking.customer?.email}</small>
                    </td>

                    <td>{booking.tourPackage?.packageName}</td>

                    <td>{booking.travelDate}</td>

                    <td>{booking.numberOfPersons}</td>

                    <td>₹{booking.totalAmount}</td>

                    <td>{booking.bookingStatus}</td>

                    <td>
                      <button
                        className="btn btn-sm btn-success me-2"
                        onClick={() => changeStatus(booking, "CONFIRMED")}
                        disabled={booking.bookingStatus === "CONFIRMED"}
                      >
                        Confirm
                      </button>
                      <button
                        className="btn btn-sm btn-danger"
                        onClick={() => changeStatus(booking, "CANCELLED")}
                        disabled={booking.bookingStatus === "CANCELLED"}
                      >
                        Cancel
                      </button>
                    </td>

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

export default ManageBookings;