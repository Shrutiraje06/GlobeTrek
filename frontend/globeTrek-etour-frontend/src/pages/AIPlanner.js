import React, { useState } from "react";
import aiService from "../services/aiService";
import Loader from "../components/Loader";

const AIPlanner = () => {

  const [userQuery, setUserQuery] = useState("");
  const [recommendation, setRecommendation] = useState("");
  const [loading, setLoading] = useState(false);
  const [hasAsked, setHasAsked] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!userQuery.trim()) return;

    setLoading(true);
    setHasAsked(true);

    try {
      const response = await aiService.getRecommendation({ userQuery });
      setRecommendation(response.data.recommendation);
    } catch (error) {
      console.log(error);
      setRecommendation(
        "Sorry, something went wrong getting a recommendation. Please try again."
      );
    }

    setLoading(false);
  };

  const examplePrompts = [
    "A relaxing beach trip under ₹20,000",
    "Adventure trip for 2 people, budget ₹15,000",
    "A short weekend getaway from a hill station",
  ];

  return (
    <div className="container mt-5" style={{ maxWidth: "700px" }}>

      <h2 className="text-center mb-2">🤖 AI Travel Planner</h2>

      <p className="text-center text-muted mb-4">
        Tell us what you're looking for, and we'll recommend the best matching packages.
      </p>

      <form onSubmit={handleSubmit}>

        <textarea
          className="form-control mb-3"
          rows="3"
          placeholder="e.g. A relaxing beach trip under ₹20,000 for 2 people"
          value={userQuery}
          onChange={(e) => setUserQuery(e.target.value)}
        />

        <button className="btn btn-primary w-100 mb-3" disabled={loading}>
          {loading ? "Thinking..." : "Get Recommendation"}
        </button>

      </form>

      {!hasAsked && (
        <div className="mt-3">
          <p className="text-muted mb-2">Try asking:</p>
          {examplePrompts.map((prompt, i) => (
            <button
              key={i}
              className="btn btn-outline-secondary btn-sm me-2 mb-2"
              onClick={() => setUserQuery(prompt)}
            >
              {prompt}
            </button>
          ))}
        </div>
      )}

      {loading && <Loader />}

      {!loading && recommendation && (
        <div className="card mt-4 shadow-sm">
          <div className="card-body">
            <h5 className="card-title">Our Recommendation</h5>
            <p className="card-text" style={{ whiteSpace: "pre-line" }}>
              {recommendation}
            </p>
          </div>
        </div>
      )}

    </div>
  );
};

export default AIPlanner;