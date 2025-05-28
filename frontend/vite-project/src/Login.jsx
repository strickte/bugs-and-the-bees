import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    confirmPassword: "",
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      alert("Passwords do not match");
    } else {
      // try {
      //   const inputData = {
      //     username: formData.username,
      //     password: formData.password,
      //   };
      //   const saveUser = await fetch("http://localhost:8080/auth", {
      //     method: "POST",
      //     headers: {
      //       "Content-Type": "application/json",
      //     },
      //     body: JSON.stringify(inputData),
      //   });
      // } catch (error) {
      //   console.log("catch");
      //   console.log(error);
      //   console.error("Submission error: ", error);
      //   alert("Something went wrong. Please try again.");
      // }
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
        </div>
      </form>
    </div>
  );
};
export default Login;
