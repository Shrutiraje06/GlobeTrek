import React, { useEffect, useState } from "react";
import packageService from "../services/packageService";
import Sidebar from "../components/Sidebar";

const emptyForm = {
  packageId: null,
  packageName: "",
  destination: "",
  duration: "",
  price: "",
  description: "",
  imageUrl: "",
};

const ManagePackages = () => {

  const [packages, setPackages] = useState([]);
  const [loading, setLoading] = useState(true);
  const [form, setForm] = useState(emptyForm);

  useEffect(() => {
    loadPackages();
  }, []);

  const loadPackages = async () => {
    try {
      const response = await packageService.getAllPackages();
      setPackages(response.data);
      setLoading(false);
    } catch (error) {
      console.log(error);
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      packageName: form.packageName,
      destination: form.destination,
      duration: form.duration,
      price: Number(form.price),
      description: form.description,
      imageUrl: form.imageUrl,
    };

    try {
      if (form.packageId) {
        // editing an existing package
        await packageService.updatePackage(form.packageId, payload);
        alert("Package updated!");
      } else {
        // adding a new package
        await packageService.addPackage(payload);
        alert("Package added!");
      }

      setForm(emptyForm);
      loadPackages();
    } catch (error) {
      console.log(error);
      alert("Something went wrong saving this package.");
    }
  };

  const startEdit = (pkg) => {
    setForm({
      packageId: pkg.packageId,
      packageName: pkg.packageName,
      destination: pkg.destination,
      duration: pkg.duration,
      price: pkg.price,
      description: pkg.description,
      imageUrl: pkg.imageUrl,
    });
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const cancelEdit = () => {
    setForm(emptyForm);
  };

  const deletePackage = async (id) => {
    if (window.confirm("Delete this package?")) {
      await packageService.deletePackage(id);
      loadPackages();
    }
  };

  return (
    <div className="container-fluid">
      <div className="row">

        <div className="col-md-3">
          <Sidebar />
        </div>

        <div className="col-md-9">

          <h2 className="mt-4">Manage Packages</h2>

          {/* ADD / EDIT FORM */}
          <div className="card mt-3 mb-4">
            <div className="card-body">

              <h5>{form.packageId ? "Edit Package" : "Add New Package"}</h5>

              <form onSubmit={handleSubmit}>

                <div className="row">

                  <div className="col-md-6 mb-3">
                    <label>Package Name</label>
                    <input
                      type="text"
                      className="form-control"
                      name="packageName"
                      value={form.packageName}
                      onChange={handleChange}
                      required
                    />
                  </div>

                  <div className="col-md-6 mb-3">
                    <label>Destination</label>
                    <input
                      type="text"
                      className="form-control"
                      name="destination"
                      value={form.destination}
                      onChange={handleChange}
                      required
                    />
                  </div>

                  <div className="col-md-4 mb-3">
                    <label>Duration</label>
                    <input
                      type="text"
                      className="form-control"
                      name="duration"
                      placeholder="e.g. 5 Days / 4 Nights"
                      value={form.duration}
                      onChange={handleChange}
                      required
                    />
                  </div>

                  <div className="col-md-4 mb-3">
                    <label>Price (₹)</label>
                    <input
                      type="number"
                      className="form-control"
                      name="price"
                      value={form.price}
                      onChange={handleChange}
                      required
                    />
                  </div>

                  <div className="col-md-4 mb-3">
                    <label>Image URL</label>
                    <input
                      type="text"
                      className="form-control"
                      name="imageUrl"
                      value={form.imageUrl}
                      onChange={handleChange}
                    />
                  </div>

                  <div className="col-12 mb-3">
                    <label>Description</label>
                    <textarea
                      className="form-control"
                      name="description"
                      rows="2"
                      value={form.description}
                      onChange={handleChange}
                    />
                  </div>

                </div>

                <button type="submit" className="btn btn-primary me-2">
                  {form.packageId ? "Update Package" : "Add Package"}
                </button>

                {form.packageId && (
                  <button
                    type="button"
                    className="btn btn-secondary"
                    onClick={cancelEdit}
                  >
                    Cancel Edit
                  </button>
                )}

              </form>

            </div>
          </div>

          {/* LIST */}
          {loading ? (
            <p>Loading...</p>
          ) : (
            <table className="table table-bordered table-striped mt-3">

              <thead className="table-dark">
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Destination</th>
                  <th>Price</th>
                  <th>Action</th>
                </tr>
              </thead>

              <tbody>
                {packages.map((pkg) => (
                  <tr key={pkg.packageId}>

                    <td>{pkg.packageId}</td>

                    <td>{pkg.packageName}</td>

                    <td>{pkg.destination}</td>

                    <td>₹{pkg.price}</td>

                    <td>
                      <button
                        className="btn btn-warning btn-sm me-2"
                        onClick={() => startEdit(pkg)}
                      >
                        Edit
                      </button>

                      <button
                        className="btn btn-danger btn-sm"
                        onClick={() => deletePackage(pkg.packageId)}
                      >
                        Delete
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

export default ManagePackages;