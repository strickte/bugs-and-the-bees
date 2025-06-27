import React, { useEffect, useState } from "react";
import { useNavigate, Link } from "react-router-dom";

const CreateProject = () => {
  const [formData, setFormData] = useState({
    name: "",
    totalSkeins: "",
    usedSkeins: "",
    occasion: "",
    targetDate: "",
  });

  const [userId, setUserId] = useState("");

  useEffect(() => {
    try {
      const savedUserDTO = JSON.parse(localStorage.getItem("userDTO"));
      console.log(savedUserDTO);
      if (savedUserDTO && savedUserDTO.userDTOId) {
        setUserId(savedUserDTO.userDTOId);
      }
    } catch (error) {
      console.log("Something went wrong: ", error);
      alert("Something went wrong. Please try again.");
    }
  }, []);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (!userId) {
      alert("User information has not loaded yet. Please try again.");
      return;
    }

    if (!formData.occasion || formData.occasion === "") {
      alert("Occasion is required.");
      return;
    }

    try {
      const apiKeyUnsplash = import.meta.env.VITE_APP_API_KEY;

      const fetchedPhoto = await fetch(
        `https://api.unsplash.com/search/photos?page=1&query=${formData.occasion}&client_id=${apiKeyUnsplash}`,
        {
          method: "GET",
        }
      );

      if (!fetchedPhoto.ok) {
        throw new Error("Photo fetch failed");
      }
      const photoData = await fetchedPhoto.json();
      const photoResult = photoData.results[0].urls.thumb;
      console.log(photoResult);

      const inputData = {
        name: formData.name,
        photoUrl: photoResult,
        totalSkeins: formData.totalSkeins
          ? parseInt(formData.totalSkeins)
          : null,
        usedSkeins: formData.usedSkeins ? parseInt(formData.usedSkeins) : null,
        occasion: formData.occasion,
        targetDate: formData.targetDate,
        userId: userId,
      };
      console.log(inputData);
      const saveProject = await fetch("http://localhost:8080/projects", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(inputData),
      });

      const saveProjectData = await saveProject.json();

      if (saveProject.ok) {
        console.log("Success:", saveProjectData.message);
        alert("Project created successfully");
        setFormData({
          name: "",
          totalSkeins: "",
          usedSkeins: "",
          occasion: "",
          targetDate: "",
        });
      } else {
        //Object.entries() converts to an arra
        console.log(saveProjectData);
        Object.entries(saveProjectData).forEach(([field, message]) => {
          console.log(`${field}: ${message}`);
          alert(`${message}`);
        });
      }
    } catch (error) {
      console.error("Submission Error:", error);
      alert("Something went wrong. Please try again.");
    }
  };

  return (
    <div>
      <h1 className="text-green-500 text-4xl font-bold">Create a Project</h1>
      <h2 className="italic">Type your project details below</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Project Name: </label>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
          ></input>
        </div>
        <div>
          <label>Total Skeins: </label>
          <input
            type="number"
            name="totalSkeins"
            value={formData.totalSkeins}
            onChange={handleChange}
          ></input>
        </div>
        <div>
          <label>Used Skeins: </label>
          <input
            type="number"
            name="usedSkeins"
            value={formData.usedSkeins}
            onChange={handleChange}
          ></input>
        </div>
        <div>
          <label>Occasion: </label>
          <input
            type="text"
            name="occasion"
            value={formData.occasion}
            onChange={handleChange}
          ></input>
        </div>
        <label>Target Completion Date: </label>
        <input
          type="date"
          name="targetDate"
          value={formData.targetDate}
          onChange={handleChange}
        ></input>
        <div>
          <button type="submit" className="bg-green-600">
            Save Project
          </button>
        </div>
      </form>
      <div>
        <Link to="/user-landing">Return to User Landing</Link>
      </div>
    </div>
  );
};
export default CreateProject;
