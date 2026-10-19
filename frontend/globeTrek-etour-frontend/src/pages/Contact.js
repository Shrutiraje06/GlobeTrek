import React from "react";

const Contact = () => {

    return (

        <div className="container mt-5">

            <h2 className="text-center mb-4">

                Contact Us

            </h2>

            <div className="row">

                <div className="col-md-6">

                    <h4>Office Address</h4>

                    <p>IndiaTour Pvt. Ltd.</p>

                    <p>Pune, Maharashtra</p>

                    <p>Email : support@indiatour.com</p>

                    <p>Phone : +91 9876543210</p>

                </div>

                <div className="col-md-6">

                    <form>

                        <input
                            className="form-control mb-3"
                            placeholder="Your Name"
                        />

                        <input
                            className="form-control mb-3"
                            placeholder="Email"
                        />

                        <textarea
                            rows="5"
                            className="form-control mb-3"
                            placeholder="Message"
                        />

                        <button className="btn btn-success">

                            Send Message

                        </button>

                    </form>

                </div>

            </div>

        </div>

    );

};

export default Contact;