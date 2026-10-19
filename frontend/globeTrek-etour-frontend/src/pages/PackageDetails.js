import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import packageService from "../services/packageService";
import aiService from "../services/aiService";
import Loader from "../components/Loader";

const PackageDetails = () => {

  const { id } = useParams();

  const [tour, setTour] = useState(null);

  const [loading, setLoading] = useState(true);

  const [itinerary, setItinerary] = useState("");

  const [itineraryLoading, setItineraryLoading] = useState(false);

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

  const handleGenerateItinerary = async () => {
    setItineraryLoading(true);
    try {
      const response = await aiService.getItinerary(tour.packageId);
      setItinerary(response.data.itinerary);
    } catch (error) {
      console.log(error);
      setItinerary("Sorry, couldn't generate an itinerary right now.");
    }
    setItineraryLoading(false);
  };

  if (loading) return <Loader />;

  if (!tour) return <p className="text-center mt-5">Package not found.</p>;

  return (
    <div className="container mt-5">

      <div className="row">

        <div className="col-md-6">
          <img
            src={tour.imageUrl}
            alt={tour.packageName}
            className="img-fluid rounded shadow"
          />
        </div>

        <div className="col-md-6">

          <h2>{tour.packageName}</h2>

          <h4 className="text-success">
            ₹ {tour.price}
          </h4>

          <p>
            <strong>Location :</strong> {tour.destination}
          </p>

          <p>
            <strong>Duration :</strong> {tour.duration}
          </p>

          <p>{tour.description}</p>

          <div className="d-flex gap-2">
            <Link
              to={`/booking/${tour.packageId}`}
              className="btn btn-primary"
            >
              Book Now
            </Link>

            <button
              className="btn btn-outline-primary"
              onClick={handleGenerateItinerary}
              disabled={itineraryLoading}
            >
              {itineraryLoading ? "Generating..." : "🤖 Generate Itinerary"}
            </button>
          </div>

          {itinerary && (
            <div className="card mt-4 shadow-sm">
              <div className="card-body">
                <h5 className="card-title">Suggested Day-by-Day Plan</h5>
                <p className="card-text" style={{ whiteSpace: "pre-line" }}>
                  {itinerary}
                </p>
              </div>
            </div>
          )}

        </div>

      </div>

    </div>
  );
};

export default PackageDetails;