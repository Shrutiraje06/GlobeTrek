import React, { useEffect, useState } from "react";
import bookingService from "../services/bookingService";
import Loader from "../components/Loader";

const BookingHistory = () => {

    const [bookings, setBookings] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadBookings();
    }, []);

    const loadBookings = async () => {
        try {
            const response = await bookingService.getBookingHistory();
            setBookings(response.data);
            setLoading(false);
        } catch (error) {
            console.log(error);
            setLoading(false);
        }
    };

    if (loading) return <Loader />;

    return (
        <div className="container mt-5">

            <h2 className="mb-4 text-center">
                Booking History
            </h2>

            {bookings.length === 0 ? (
                <p className="text-center">No bookings yet.</p>
            ) : (
                <table className="table table-bordered table-hover">

                    <thead className="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Package</th>
                            <th>Travel Date</th>
                            <th>Persons</th>
                            <th>Total Amount</th>
                            <th>Status</th>
                        </tr>
                    </thead>

                    <tbody>
                        {bookings.map((booking) => (
                            <tr key={booking.bookingId}>
                                <td>{booking.bookingId}</td>
                                <td>{booking.tourPackage?.packageName}</td>
                                <td>{booking.travelDate}</td>
                                <td>{booking.numberOfPersons}</td>
                                <td>₹{booking.totalAmount}</td>
                                <td>{booking.bookingStatus}</td>
                            </tr>
                        ))}
                    </tbody>

                </table>
            )}

        </div>
    );
};

export default BookingHistory;