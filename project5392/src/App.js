import "./App.css";
import LoginForm from "./Components/LoginForm/LoginForm";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Mainpage from "./Components/MainPage/MainPage";
import Operatorpage from "./Components/MainPage/OperatorMainPage";
import SystemBuffer from "./Components/SystemBuffer/SystemBuffer";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<LoginForm />} />
          <Route path="/MainPage" element={<Mainpage />} />
          <Route path="/OperatorMainPage" element={<Operatorpage />} />
          <Route path="/SystemBuffer" element={<SystemBuffer />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
