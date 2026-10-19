import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import bookingService from "../services/bookingService";
import Loader from "../components/Loader";

const Payment = () => {

  const { bookingId } = useParams();
  const navigate = useNavigate();

  const [booking, setBooking] = useState(null);
  const [loading, setLoading] = useState(true);
  const [processing, setProcessing] = useState(false);

  useEffect(() => {
    loadBooking();
  }, []);

  const loadBooking = async () => {
    try {
      const response = await bookingService.getBookingById(bookingId);
      setBooking(response.data);
      setLoading(false);
    } catch (error) {
      console.log(error);
      setLoading(false);
    }
  };

  const handlePayNow = async () => {
    setProcessing(true);
    try {
      // 1. Ask backend to create a Razorpay order
      const orderRes = await bookingService.createRazorpayOrder(booking.bookingId);
      const { orderId, keyId, amount, currency } = orderRes.data;

      // 2. Open Razorpay's Checkout popup
      const options = {
        key: keyId,
        amount: Math.round(amount * 100),
        currency: currency,
        name: "GlobeTrek",
        description: `Payment for Booking #${booking.bookingId}`,
        order_id: orderId,
        handler: async function (response) {
          try {
            // 3. Send back the three values Razorpay returns, for backend verification
            await bookingService.verifyRazorpayPayment({
              razorpayOrderId: response.razorpay_order_id,
              razorpayPaymentId: response.razorpay_payment_id,
              razorpaySignature: response.razorpay_signature,
              bookingId: booking.bookingId,
            });

            alert("Payment Successful");
            navigate("/booking-history");
          } catch (err) {
            console.log(err);
            alert("Payment verification failed");
          }
        },
        modal: {
          ondismiss: function () {
            setProcessing(false);
          },
        },
        theme: { color: "#0d6efd" },
      };

      const rzp = new window.Razorpay(options);
      rzp.open();
    } catch (error) {
      console.log(error);
      alert("Could not start payment");
      setProcessing(false);
    }
  };

  if (loading) return <Loader />;

  if (!booking) return <p className="text-center mt-5">Booking not found.</p>;

  return (
    <div className="container mt-5">
      <div className="card shadow">
        <div className="card-body text-center">
          <h2 className="mb-3">Payment</h2>
          <p>
            Amount to pay: <strong>₹{booking.totalAmount}</strong>
          </p>
          <button
            className="btn btn-primary w-100"
            onClick={handlePayNow}
            disabled={processing}
          >
            {processing ? "Processing..." : "Pay Now"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default Payment;