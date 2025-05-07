import React, { useEffect, useState } from "react";
import {
  Routes,
  Route,
  useNavigate,
  BrowserRouter as Router,
} from "react-router-dom";
import UserLanding from "./UserLanding";
import Login from "./Login";
import CreateProject from "./CreateProject";
import "./index.css";
import UpdateProject from "./UpdateProject";

function App() {
  return (
    <>
      <Router>
        <Routes>
        <Route path="/update-project" element={<UpdateProject />}></Route>
          <Route path="/create-project" element={<CreateProject />}></Route>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/user-landing" element={<UserLanding />}></Route>
        </Routes>
      </Router>
    </>
  );
}

export default App;
