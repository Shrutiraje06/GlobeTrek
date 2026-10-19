import React, { useEffect, useState } from "react";
import PackageCard from "../components/PackageCard";
import SearchBar from "../components/SearchBar";
import Loader from "../components/Loader";
import packageService from "../services/packageService";
import { useSearchParams } from "react-router-dom";

const Packages = () => {

  const [searchParams] = useSearchParams();

  const [packages, setPackages] = useState([]);

  const [filteredPackages, setFilteredPackages] = useState([]);

  const [loading, setLoading] = useState(true);

  useEffect(() => {

    loadPackages();

  }, []);

  useEffect(() => {

    if (packages.length === 0) return;

    const destination = searchParams.get("destination");
    const budget = searchParams.get("budget");

    let result = packages;

    if (destination) {
      result = result.filter((pkg) =>
        pkg.packageName.toLowerCase().includes(destination.toLowerCase()) ||
        pkg.destination.toLowerCase().includes(destination.toLowerCase())
      );
    }

    if (budget) {
      result = result.filter((pkg) => pkg.price <= Number(budget));
    }

    setFilteredPackages(result);

  }, [packages, searchParams]);

  const loadPackages = async () => {

    try {

      const response = await packageService.getAllPackages();

      setPackages(response.data);

      setFilteredPackages(response.data);

      setLoading(false);

    } catch (error) {

      console.log(error);

      setLoading(false);

    }

  };

  const searchPackage = (keyword) => {

    const result = packages.filter((pkg) =>
  pkg.packageName.toLowerCase().includes(keyword.toLowerCase())
);

    setFilteredPackages(result);

  };

  if (loading) return <Loader />;

  return (

    <div className="container mt-5">

      <h2 className="text-center mb-4">

        Tour Packages

      </h2>

      <SearchBar onSearch={searchPackage} />

      <div className="row">

        {filteredPackages.map((pkg) => (

          <PackageCard
  key={pkg.packageId}
  tour={pkg}
/>

        ))}

      </div>

    </div>

  );

};

export default Packages;