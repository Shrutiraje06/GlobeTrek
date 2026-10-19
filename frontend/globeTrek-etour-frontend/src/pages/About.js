import React from "react";

function About() {
  return (
    <div>

      {/* Hero Section */}
      <section className="bg-primary text-white text-center py-5">
        <div className="container">
          <h1 className="display-4 fw-bold">About IndiaTour</h1>
          <p className="lead">
            AI-Based Smart Travel Planning Platform
          </p>

          <button className="btn btn-warning btn-lg mt-3">
            Explore Packages
          </button>
        </div>
      </section>

      {/* About */}
      <section className="container my-5">

        <div className="row align-items-center">

          <div className="col-md-6">

            <img
              src="https://images.unsplash.com/photo-1507525428034-b723cf961d3e"
              alt="Travel"
              className="img-fluid rounded shadow"
            />

          </div>

          <div className="col-md-6">

            <h2>Who We Are</h2>

            <p>
              IndiaTour is an AI-powered Smart Travel Planning System
              developed using React, Spring Boot, MySQL, JWT Authentication,
              and REST APIs.
            </p>

            <p>
              Our platform helps travelers discover amazing destinations,
              compare packages, book trips online, and receive AI-powered
              travel recommendations.
            </p>

          </div>

        </div>

      </section>

      {/* Features */}

      <section className="container my-5">

        <h2 className="text-center mb-5">

          Why Choose IndiaTour?

        </h2>

        <div className="row">

          <div className="col-md-3">

            <div className="card text-center shadow">

              <div className="card-body">

                <h1>🤖</h1>

                <h5>AI Planner</h5>

                <p>
                  Personalized travel itinerary recommendations.
                </p>

              </div>

            </div>

          </div>

          <div className="col-md-3">

            <div className="card text-center shadow">

              <div className="card-body">

                <h1>🏨</h1>

                <h5>Hotel Booking</h5>

                <p>
                  Find the best hotels at affordable prices.
                </p>

              </div>

            </div>

          </div>

          <div className="col-md-3">

            <div className="card text-center shadow">

              <div className="card-body">

                <h1>💳</h1>

                <h5>Secure Payment</h5>

                <p>
                  Fast and secure online payment system.
                </p>

              </div>

            </div>

          </div>

          <div className="col-md-3">

            <div className="card text-center shadow">

              <div className="card-body">

                <h1>📞</h1>

                <h5>24×7 Support</h5>

                <p>
                  Dedicated customer support for every traveler.
                </p>

              </div>

            </div>

          </div>

        </div>

      </section>

      {/* Statistics */}

      <section className="bg-light py-5">

        <div className="container">

          <div className="row text-center">

            <div className="col-md-3">

              <h1 className="text-primary">1000+</h1>

              <h5>Happy Travelers</h5>

            </div>

            <div className="col-md-3">

              <h1 className="text-success">150+</h1>

              <h5>Tour Packages</h5>

            </div>

            <div className="col-md-3">

              <h1 className="text-danger">50+</h1>

              <h5>Destinations</h5>

            </div>

            <div className="col-md-3">

              <h1 className="text-warning">24/7</h1>

              <h5>Customer Support</h5>

            </div>

          </div>

        </div>

      </section>

      {/* Technologies */}

      <section className="container my-5">

        <h2 className="text-center mb-4">

          Technologies Used

        </h2>

        <div className="row">

          <div className="col-md-3">

            <div className="card text-center">

              <div className="card-body">

                <h3>React</h3>

                <p>Frontend Development</p>

              </div>

            </div>

          </div>

          <div className="col-md-3">

            <div className="card text-center">

              <div className="card-body">

                <h3>Spring Boot</h3>

                <p>Backend REST APIs</p>

              </div>

            </div>

          </div>

          <div className="col-md-3">

            <div className="card text-center">

              <div className="card-body">

                <h3>MySQL</h3>

                <p>Database</p>

              </div>

            </div>

          </div>

          <div className="col-md-3">

            <div className="card text-center">

              <div className="card-body">

                <h3>JWT</h3>

                <p>Secure Authentication</p>

              </div>

            </div>

          </div>

        </div>

      </section>

      {/* Reviews */}

      <section className="bg-light py-5">

        <div className="container">

          <h2 className="text-center mb-5">

            What Our Customers Say

          </h2>

          <div className="row">

            <div className="col-md-4">

              <div className="card shadow">

                <div className="card-body">

                  ⭐⭐⭐⭐⭐

                  <p className="mt-3">
                    Amazing experience. AI suggestions helped us plan
                    the perfect vacation.
                  </p>

                  <strong>Rahul Sharma</strong>

                </div>

              </div>

            </div>

            <div className="col-md-4">

              <div className="card shadow">

                <div className="card-body">

                  ⭐⭐⭐⭐⭐

                  <p className="mt-3">
                    Easy booking process and excellent customer support.
                  </p>

                  <strong>Priya Patel</strong>

                </div>

              </div>

            </div>

            <div className="col-md-4">

              <div className="card shadow">

                <div className="card-body">

                  ⭐⭐⭐⭐⭐

                  <p className="mt-3">
                    Beautiful UI and affordable tour packages.
                  </p>

                  <strong>Amit Verma</strong>

                </div>

              </div>

            </div>

          </div>

        </div>

      </section>

      {/* Footer */}

      <section className="bg-dark text-white text-center py-4">

        <h5>IndiaTour</h5>

        <p>Email : support@indiatour.com</p>

        <p>Phone : +91 9876543210</p>

        <p>© 2026 IndiaTour. All Rights Reserved.</p>

      </section>

    </div>
  );
}

export default About;