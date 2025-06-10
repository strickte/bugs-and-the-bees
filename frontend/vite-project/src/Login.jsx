import React, { useEffect, useState } from "react";
import { useNavigate, Link } from "react-router-dom";

const Login = () => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const navigate = useNavigate();

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const inputData = {
        username: formData.username,
        password: formData.password,
      };
      const loginUser = await fetch("http://localhost:8080/api/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(inputData),
      });

      const loginUserData = await loginUser.json();

      if (loginUser.ok) {
        const userDTO = loginUserData.userDTO;
        localStorage.setItem("userDTO", JSON.stringify(userDTO));
        const message = loginUserData.message;
        console.log("Success: ", message, " User: ", userDTO.userDTOName);
        alert(message);
        // navigate("/user-landing");
      } else {
        //Object.entries() converts to array
        Object.entries(loginUserData).forEach(([field, message]) => {
          console.log(`${field}: ${message}`);
          alert(`${message}`);
        });
        console.log(loginUserData);
      }
    } catch (error) {
      console.log("Submission error: ", error);
      alert("Something went wrong. Please try again.");
    }
  };

  return (
    <div>
      <h1 className="text-green-500 text-4xl font-bold">User Login</h1>
      <h2>Enter your user information below</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>User Name: </label>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
          ></input>
        </div>
        <div>
          <label>Password: </label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
          ></input>
        </div>
        <div>
          <button type="submit" className="bg-green-600">
            Login
          </button>
          {/* <div>
            <Link to="/user-landing">Go to User Landing</Link>
          </div> */}
        </div>
      </form>
    </div>
  );
};
export default Login;
