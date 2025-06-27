import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";

const UpdateProject = () => {
  const { projectId } = useParams();
  console.log(projectId);

  const [formData, setFormData] = useState({
    name: "",
    totalSkeins: "",
    usedSkeins: "",
    occasion: "",
    targetDate: "",
    photoUrl: "",
    userId: "",
    // id: "",
  });


  useEffect(() => {
    const fetchProject = async () => {
      if (!projectId) {
        alert("Waiting for the project to load. Please try again.");
        return;
      }

      try {
        const response = await fetch(
          `http://localhost:8080/projects/update?projectId=${projectId}`,
          {
            method: "GET",
          }
        );

        if (!response.ok) {
          throw new Error("Project fetch failed");
        }
        const responseData = await response.json();
        console.log(responseData);
        setFormData({
          name: responseData.name,
          totalSkeins: responseData.totalSkeins,
          usedSkeins: responseData.usedSkeins,
          occasion: responseData.occasion,
          targetDate: responseData.targetDate,
          photoUrl: responseData.photoUrl,
          userId: responseData.user.id,
          // id: responseData.id,
        });
      } catch (error) {
        console.log("Something went wrong: ", error);
        alert("Something went wrong. Please try again.");
      } 
    };

    fetchProject();
  }, [projectId]);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (!formData.userId) {
      alert("User information has not loaded yet. Please try again.");
      return;
    }

    if (!formData.occasion || formData.occasion === "") {
      alert("Occasion is required.");
      return;
    }

    try {
      
      const updateData = {
        name: formData.name,
        photoUrl: formData.photoUrl,
        totalSkeins: parseInt(formData.totalSkeins),
        usedSkeins: parseInt(formData.usedSkeins),
        occasion: formData.occasion,
        targetDate: formData.targetDate,
        userId: formData.userId,
        // id: formData.id,
      };
      console.log(updateData);
      const updateProject = await fetch(`http://localhost:8080/projects/update/${projectId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(updateData),
      });

      const updateProjectData = await updateProject.json();

      if (updateProject.ok) {
        console.log("Success:", updateProjectData.message);
        alert("Project updated successfully");
        setFormData({
          name: "",
          totalSkeins: "",
          usedSkeins: "",
          occasion: "",
          targetDate: "",
        });
      } else {
        //Object.entries() converts to an array

        console.log(updateProjectData);
        Object.entries(updateProjectData).forEach(([field, message]) => {
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
    <div className="italic">
      <h1 className={`text-green-500 text-4xl font-bold`}>
        Update Project Page
      </h1>
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
            Update Project
          </button>
        </div>
      </form>
      <div>
        <Link to="/user-landing">Return to User Landing</Link>
      </div>
    </div>
  );
};
export default UpdateProject;
