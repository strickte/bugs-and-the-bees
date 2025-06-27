import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const UserRegistration = () => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    confirmPassword: "",
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
        confirmPassword: formData.confirmPassword,
      };

      const saveUser = await fetch("http://localhost:8080/api/signup", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(inputData),
      });

      const saveUserData = await saveUser.json();

      if (saveUser.ok) {
        const userDTO = saveUserData.userDTO;
        localStorage.setItem("userDTO", JSON.stringify(userDTO));
        const message = saveUserData.message;
        console.log("Success: ", message, "User: ", userDTO.userDTOName);
        alert(message);
        // navigate("/user-landing");
      } else {
        //Object.entries() converts to array
        Object.entries(saveUserData).forEach(([field, message]) => {
          console.log(`${field}: ${message}`);
          alert(`${message}`);
        });
        console.log(saveUserData);
      }
    } catch (error) {
      console.log("Submission error: ", error);
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
          ></input>
        </div>
        <div>
          <label> Confirm Your Password: </label>
          <input
            type="password"
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleChange}
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
