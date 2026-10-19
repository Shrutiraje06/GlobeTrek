import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import wishlistService from "../services/wishlistService";

const PackageCard = ({ tour }) => {
  const [wishlisted, setWishlisted] = useState(false);

  useEffect(() => {
    wishlistService.checkWishlisted(tour.packageId)
      .then((res) => setWishlisted(res.data))
      .catch(() => {}); // not logged in / 401 — just leave it unfilled
  }, [tour.packageId]);

  const toggleWishlist = async () => {
    try {
      if (wishlisted) {
        await wishlistService.removeFromWishlist(tour.packageId);
      } else {
        await wishlistService.addToWishlist(tour.packageId);
      }
      setWishlisted(!wishlisted);
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="col-md-4 mb-4">
      <div className="card h-100 shadow position-relative">

        <button
          onClick={toggleWishlist}
          className="btn btn-light position-absolute"
          style={{ top: "10px", right: "10px", borderRadius: "50%" }}
        >
          {wishlisted ? "❤️" : "🤍"}
        </button>

        <img
          src={tour.imageUrl}
          className="card-img-top"
          alt={tour.packageName}
          style={{ height: "220px", objectFit: "cover" }}
        />

        <div className="card-body">
          <h5 className="card-title">{tour.packageName}</h5>
          <p className="card-text">{tour.destination}</p>
          <p><strong>Duration:</strong> {tour.duration}</p>
          <p><strong>Price:</strong> ₹{tour.price}</p>
          <Link to={`/package/${tour.packageId}`} className="btn btn-primary">
            View Details
          </Link>
        </div>
      </div>
    </div>
  );
};

export default PackageCard;