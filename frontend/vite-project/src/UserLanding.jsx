import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const UserLanding = () => {
  const [username, setUsername] = useState("");

  useEffect(() => {
    try {
      const savedUserDTO = JSON.parse(localStorage.getItem("userDTO"));
      console.log(savedUserDTO);
      if (savedUserDTO && savedUserDTO.userDTOName) {
        setUsername(savedUserDTO.userDTOName);
      }
    } catch (error) {
      console.log("Something went wrong: ", error);
      alert("Something went wrong. Please try again.");
    }
  }, []);
  return (
    <div>
      <h1 className="text-green-500 text-4xl font-bold">User Landing Page</h1>
      <h2>Hello, {username}!</h2>
      <div>
        <Link to="/signout">Signout</Link>
      </div>
    </div>
  );
};
export default UserLanding;
