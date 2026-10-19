import React, { useState } from "react";
import aiService from "../services/aiService";

const AIRecommendation = () => {

    const [interest, setInterest] = useState("");

    const [budget, setBudget] = useState("");

    const [days, setDays] = useState("");

    const [result, setResult] = useState("");

    const getRecommendation = async () => {

        try {

            const response = await aiService.getRecommendation({

                interest,

                budget,

                days

            });

            setResult(response.data);

        } catch (error) {

            console.log(error);

        }

    };

    return (

        <div className="container mt-5">

            <h2 className="text-center">

                AI Travel Planner

            </h2>

            <input
                className="form-control mb-3"
                placeholder="Interest"
                value={interest}
                onChange={(e) => setInterest(e.target.value)}
            />

            <input
                className="form-control mb-3"
                placeholder="Budget"
                value={budget}
                onChange={(e) => setBudget(e.target.value)}
            />

            <input
                className="form-control mb-3"
                placeholder="Days"
                value={days}
                onChange={(e) => setDays(e.target.value)}
            />

            <button
                className="btn btn-primary"
                onClick={getRecommendation}
            >
                Get Recommendation
            </button>

            <div className="card mt-4">

                <div className="card-body">

                    <h4>Recommendation</h4>

                    <p>{result}</p>

                </div>

            </div>

        </div>

    );

};

export default AIRecommendation;