import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const Signout = () => {
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();

    console.log("logout submit");
    try {
      const signout = await fetch("http://localhost:8080/api/signout", {
        method: "GET",
        credentials: "include",
      });

      const signoutData = await signout.json();
      const message = signoutData.message;

      if (signout.ok) {
        localStorage.removeItem("userDTO");
        console.log("Success:", signoutData);
        alert("Signout Succesful");
        navigate("/login");
      } else {
        console.log("Error:", message);
        alert(message);
      }
    } catch (error) {
      console.log("catch");
      console.log(error);
      console.error("Submission error: ", error);
      alert("Something went wrong. Please try again.");
    }
  };

  return (
    <div>
      <h1 className={`text-green-500 text-4xl font-bold`}>
        Are you sure you want to sign out?
      </h1>
      <div>
        <button onClick={handleSubmit} type="button" className="bg-green-600">
          Signout
        </button>
      </div>
    </div>
  );
};
export default Signout;
