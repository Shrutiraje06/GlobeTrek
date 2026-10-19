import React from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import "../styles/Home.css";
function Home() {
  const navigate = useNavigate();
const [destination, setDestination] = React.useState("");
const [budget, setBudget] = React.useState("");

const handleSearch = () => {
  const params = new URLSearchParams();
  if (destination) params.set("destination", destination);
  if (budget) params.set("budget", budget);
  navigate(`/packages?${params.toString()}`);
};
  return (
    <>

      {/* Hero Section */}
      <section className="hero text-white text-center">
        <div className="container">

          <h1 className="display-3 fw-bold">
            Explore Incredible India
          </h1>

          <p className="lead mt-3">
            AI-Based Smart Travel Planner
          </p>

          <Link to="/packages" className="btn btn-warning btn-lg mt-3 me-3">
            Explore Packages
          </Link>

          <Link to="/ai" className="btn btn-light btn-lg mt-3">
            AI Planner
          </Link>

        </div>
      </section>

      {/* Search */}
      <section className="container mt-5">

        <div className="card shadow p-4">

          <div className="row">

            <div className="col-md-3">
  <input
    className="form-control"
    placeholder="Destination"
    value={destination}
    onChange={(e) => setDestination(e.target.value)}
  />
</div>

            <div className="col-md-3">
              <input
                type="date"
                className="form-control"
              />
            </div>

           <div className="col-md-3">
  <select
    className="form-control"
    value={budget}
    onChange={(e) => setBudget(e.target.value)}
  >
    <option value="">Budget</option>
    <option value="10000">Up to ₹10,000</option>
    <option value="20000">Up to ₹20,000</option>
    <option value="30000">Up to ₹30,000</option>
  </select>
</div>
            <div className="col-md-3">

              <button className="btn btn-success w-100" onClick={handleSearch}>
  Search
</button>
            </div>

          </div>

        </div>

      </section>

      {/* Popular Destinations */}

      <section className="container mt-5">

        <h2 className="text-center mb-4">

          Popular Destinations

        </h2>

        <div className="row">

          {[
            {
              name: "Goa",
              img: "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2"
            },
            {
              name: "Kashmir",
              img: "https://images.unsplash.com/photo-1605640840605-14ac1855827b"
            },
            {
              name: "Kerala",
              img: "https://images.unsplash.com/photo-1593693397690-362cb9666fc2"
            }
          ].map((place, index) => (

            <div className="col-md-4 mb-4" key={index}>

              <div className="card shadow">

                <img
                  src={place.img}
                  className="card-img-top"
                  style={{ height: "220px", objectFit: "cover" }}
                  alt={place.name}
                />

                <div className="card-body text-center">

                  <h4>{place.name}</h4>

                  <Link
                    className="btn btn-primary"
                    to="/packages"
                  >
                    Explore
                  </Link>

                </div>

              </div>

            </div>

          ))}

        </div>

      </section>

      {/* Why Choose Us */}

      <section className="bg-light py-5 mt-5">

        <div className="container">

          <h2 className="text-center mb-5">

            Why Choose IndiaTour?

          </h2>

          <div className="row text-center">

            <div className="col-md-3">
              <h1>🤖</h1>
              <h5>AI Planner</h5>
            </div>

            <div className="col-md-3">
              <Link to="/packages" className="text-decoration-none text-dark">
                <h1>🏨</h1>
                <h5>Hotels</h5>
              </Link>
            </div>

            <div className="col-md-3">
              <h1>💳</h1>
              <h5>Secure Payment</h5>
            </div>

            <div className="col-md-3">
              <Link to="/contact" className="text-decoration-none text-dark">
              <h1>📞</h1>
              <h5>24×7 Support</h5>
              </Link>
            </div>

          </div>

        </div>

      </section>

      {/* Statistics */}

      <section className="container py-5">

        <div className="row text-center">

          <div className="col-md-3">
            <h1 className="text-primary">1000+</h1>
            <h5>Happy Customers</h5>
          </div>

          <div className="col-md-3">
            <h1 className="text-success">150+</h1>
            <h5>Packages</h5>
          </div>

          <div className="col-md-3">
            <h1 className="text-danger">50+</h1>
            <h5>Destinations</h5>
          </div>

          <div className="col-md-3">
            <h1 className="text-warning">24/7</h1>
            <h5>Support</h5>
          </div>

        </div>

      </section>

    </>
  );
}

export default Home;