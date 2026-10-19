import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import packageService from "../services/packageService";
import bookingService from "../services/bookingService";
import Loader from "../components/Loader";

function Booking() {

  const { id } = useParams(); // this is the packageId
  const navigate = useNavigate();

  const [tour, setTour] = useState(null);
  const [loading, setLoading] = useState(true);

  const [travelDate, setTravelDate] = useState("");
  const [numberOfPersons, setNumberOfPersons] = useState(1);

  useEffect(() => {
    loadPackage();
  }, []);

  const loadPackage = async () => {
    try {
      const response = await packageService.getPackageById(id);
      setTour(response.data);
      setLoading(false);
    } catch (error) {
      console.log(error);
      setLoading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const userId = localStorage.getItem("userId");

    if (!userId) {
      alert("Please log in before booking.");
      navigate("/login");
      return;
    }

    const totalAmount = tour.price * numberOfPersons;

    const bookingPayload = {
      customer: { id: userId },
      tourPackage: { packageId: tour.packageId },
      bookingDate: new Date().toISOString().split("T")[0], // today
      travelDate: travelDate,
      numberOfPersons: numberOfPersons,
      bookingStatus: "PENDING",
      totalAmount: totalAmount,
    };

    try {
      const response = await bookingService.createBooking(bookingPayload);
      alert("Booking created! Proceeding to payment.");
      navigate(`/payment/${response.data.bookingId}`);
    } catch (error) {
      console.log(error);
      alert("Booking failed. Please try again.");
    }
  };

  if (loading) return <Loader />;

  if (!tour) return <p className="text-center mt-5">Package not found.</p>;

  const totalAmount = tour.price * numberOfPersons;

  return (
    <div className="container my-5">

      <div className="row">

        {/* LEFT SIDE — FORM */}
        <div className="col-lg-8">

          <div className="card shadow">

            <div className="card-header bg-primary text-white">
              <h3>Book: {tour.packageName}</h3>
            </div>

            <div className="card-body">

              <form onSubmit={handleSubmit}>

                <div className="mb-3">
                  <label>Travel Date</label>
                  <input
                    type="date"
                    className="form-control"
                    value={travelDate}
                    onChange={(e) => setTravelDate(e.target.value)}
                    required
                  />
                </div>

                <div className="mb-3">
                  <label>No. of Travellers</label>
                  <input
                    type="number"
                    min="1"
                    className="form-control"
                    value={numberOfPersons}
                    onChange={(e) => setNumberOfPersons(Number(e.target.value))}
                    required
                  />
                </div>

                <button className="btn btn-success btn-lg mt-3 w-100">
                  Proceed To Payment
                </button>

              </form>

            </div>

          </div>

        </div>

        {/* RIGHT SIDE — SUMMARY */}
        <div className="col-lg-4">

          <div className="card shadow">

            <img
              src={tour.imageUrl}
              className="card-img-top"
              alt={tour.packageName}
            />

            <div className="card-body">

              <h4>{tour.packageName}</h4>
              <p>📍 {tour.destination}</p>
              <p>🗓 {tour.duration}</p>

              <hr />

              <h5>Booking Summary</h5>

              <table className="table">
                <tbody>
                  <tr>
                    <td>Price per person</td>
                    <td>₹{tour.price}</td>
                  </tr>
                  <tr>
                    <td>Travellers</td>
                    <td>{numberOfPersons}</td>
                  </tr>
                  <tr>
                    <th>Total</th>
                    <th>₹{totalAmount}</th>
                  </tr>
                </tbody>
              </table>

            </div>

          </div>

        </div>

      </div>

    </div>
  );
}

export default Booking;