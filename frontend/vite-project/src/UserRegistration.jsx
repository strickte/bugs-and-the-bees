import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const UserRegistration = () => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const inputData = {
    username: formData.username,
    password: formData.password,
  };

  const handleSubmit = async (e) => {
    try {
      console.log("pre-saveUser");
      const saveUser = await fetch("http://localhost:8080/auth", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(inputData),
      });
    } catch (error) {
      console.error("Submission error: ", error);
      alert("Something went wrong. Please try again.");
    }
  };

  return (
    <div>
      <h1 className="text-green-500 text-4xl font-bold">User Registration</h1>
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
            autoComplete="off"
          ></input>
        </div>
        <div>
          <button type="submit" className="bg-green-600">
            Register
          </button>
        </div>
      </form>
    </div>
  );
};
export default UserRegistration;
