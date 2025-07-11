import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const UserLanding = () => {
  const [username, setUsername] = useState("");
  const [userId, setUserId] = useState("");
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    try {
      const savedUserDTO = JSON.parse(localStorage.getItem("userDTO"));
      console.log(savedUserDTO);
      if (savedUserDTO && savedUserDTO.userDTOName && savedUserDTO.userDTOId) {
        setUsername(savedUserDTO.userDTOName);
        setUserId(savedUserDTO.userDTOId);
      }
    } catch (error) {
      console.log("Something went wrong: ", error);
      alert("Something went wrong pulling user information. Please try again.");
    }
  }, []);

  useEffect(() => {
    if (!userId) return;

    const fetchProjects = async () => {
      try {
        // setLoading(true);
        const response = await fetch(
          `http://localhost:8080/projects/user-landing?userId=${userId}`,
          {
            method: "GET",
          }
        );

        if (!response.ok) {
          throw new Error("Project fetch failed");
        }
        const responseData = await response.json();
        console.log(responseData);
        setProjects(responseData);
      } catch (error) {
        console.log("Something went wrong: ", error);
        alert("Something went wrong. Please try again.");
      }
      // } finally {
      //   setLoading(false);
      // }
    };

    fetchProjects();
  }, [userId]);

  const handleDelete = async (projectId) => {
    const confirmed = window.confirm(
      "Are you sure you want to delete this project?"
    );
    if (!confirmed) return;

    try {
      const response = await fetch(
        `http://localhost:8080/projects/delete/${projectId}`,
        {
          method: "DELETE",
        }
      );

      if (!response.ok) {
        throw new Error("Failed to delete project.");
      }
      alert("Project successfully deleted");
      //giving setProjects a function as an argument to clear the old state
      //the function receives previous state as argument and returns a filtered array
      setProjects((prevProjects) =>
        prevProjects.filter((project) => project.id !== projectId)
      );
    } catch (error) {
      console.log("Error deleting project: ", error);
      alert("An error occurred and the project was not deleted.");
    }
  };

  return (
    <div className="p-8">
      <h1 className="text-green-500 text-4xl font-bold text-center mb-2">
        User Landing Page
      </h1>
      <h2 className="text-center mb-6">Hello, {username}!</h2>
      <div>
        <Link to="/create-project">Create a new project!</Link>
      </div>
      <div className="mb-6">
        <Link to="/signout">Signout</Link>
      </div>
      <div>
        {projects.length === 0 ? (
          <div>
            <h3 className="text-center">No projects found.</h3>
          </div>
        ) : (
          projects.map((project) => (
            <div key={project.id}>
              <div className="mb-6">
                <h3 className="text-2xl font-semibold text-gray-500">
                  {project.name}
                </h3>
                <ul>
                  <li>Occasion: {project.occasion}</li>
                  <li>Used skeins: {project.usedSkeins}</li>
                  <li>Total skeins: {project.totalSkeins}</li>
                  <li>Target completion date: {project.targetDate}</li>
                  <li>
                    <img
                      className="rounded-full w-24 h-24 object-cover"
                      src={project.photoUrl}
                      alt="default image"
                    ></img>
                  </li>
                </ul>
                <div className="space-y-4 space-x-4">
                  <Link to={`/update-project/${project.id}`}>
                    <button className="bg-green-400">Update</button>
                  </Link>
                  <button
                    onClick={() => handleDelete(project.id)}
                    className="bg-red-500"
                  >
                    Delete
                  </button>
                </div>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};
export default UserLanding;
