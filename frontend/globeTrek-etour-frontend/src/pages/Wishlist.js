import React, { useEffect, useState } from "react";
import wishlistService from "../services/wishlistService";
import PackageCard from "../components/PackageCard";
import Loader from "../components/Loader";

const Wishlist = () => {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    wishlistService.getWishlist()
      .then((res) => setItems(res.data))
      .catch((err) => console.log(err))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <Loader />;

  return (
    <div className="container mt-5">
      <h2 className="mb-4">My Wishlist</h2>
      {items.length === 0 ? (
        <p>No packages saved yet.</p>
      ) : (
        <div className="row">
          {items.map((item) => (
            <PackageCard key={item.wishlistId} tour={item.tourPackage} />
          ))}
        </div>
      )}
    </div>
  );
};

export default Wishlist;