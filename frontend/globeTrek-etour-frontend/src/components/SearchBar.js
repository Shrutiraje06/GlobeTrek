import React, { useState } from "react";

const SearchBar = ({ onSearch }) => {

  const [keyword, setKeyword] = useState("");

  const handleSearch = (e) => {
    e.preventDefault();
    onSearch(keyword);
  };

  return (
    <form
      className="d-flex mb-4"
      onSubmit={handleSearch}
    >

      <input
        type="text"
        className="form-control me-2"
        placeholder="Search Tour Package..."
        value={keyword}
        onChange={(e) => setKeyword(e.target.value)}
      />

      <button
        className="btn btn-primary"
        type="submit"
      >
        Search
      </button>

    </form>
  );
};

export default SearchBar;