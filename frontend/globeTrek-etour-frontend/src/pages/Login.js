import { useState } from "react";
import AuthService from "../services/AuthService";
import "./Login.css";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await AuthService.login(email, password);

     localStorage.setItem("token", response.data.token);
localStorage.setItem("roles", JSON.stringify(response.data.roles));
localStorage.setItem("email", response.data.email);
localStorage.setItem("userId", response.data.id);
alert("Login Successful");

window.location.href = "/";
    } catch (error) {
      alert("Invalid Email or Password");
    }
  };

  return (
    <div className="container mt-5">
      <div className="card p-4 mx-auto" style={{ maxWidth: "400px" }}>
        <h2 className="text-center mb-3">Login</h2>

        <form onSubmit={handleLogin}>
          <input
            type="email"
            className="form-control mb-3"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <input
            type="password"
            className="form-control mb-3"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button className="btn btn-primary w-100">
            Login
          </button>
        </form>
      </div>
    </div>
  );
}

export default Login;