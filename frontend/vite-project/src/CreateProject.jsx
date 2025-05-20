import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const CreateProject = () => {
  const [project, setProject] = useState([]);
  const [formData, setFormData] = useState({
    totalSkeins: "",
    usedSkeins: "",
    occasion: "",
    targetDate: "",
    //  photoUrl: "",
    // user: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

const handleSubmit = async (event) => {


  
}

  return (
    <div>
      <h1 className="text-green-500 text-4xl font-bold">Create a Project</h1>
      <h2 className="italic">Type your project details below</h2>
    </div>
  );
};
export default CreateProject;
