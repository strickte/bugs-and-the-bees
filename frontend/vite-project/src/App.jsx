import {
  Routes,
  Route,
  BrowserRouter as Router,
} from "react-router-dom";
import UserLanding from "./UserLanding";
import Login from "./Login";
import CreateProject from "./CreateProject";
import "./index.css";
import UpdateProject from "./UpdateProject";
import Signout from "./Signout";
import UserRegistration from "./UserRegistration";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/update-project" element={<UpdateProject />}></Route>
          <Route path="/create-project" element={<CreateProject />}></Route>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/user-landing" element={<UserLanding />}></Route>
          <Route path="/signout" element={<Signout />}></Route>
          <Route path="/user-registration" element={<UserRegistration />}></Route>
        </Routes>
      </Router>
    </>
  );
}

export default App;
