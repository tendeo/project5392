import './App.css';
import LoginForm from './Components/LoginForm/LoginForm';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Mainpage from './Components/MainPage/MainPage';
import Operatorpage from './Components/MainPage/OperatorMainPage';

function App() {
  return (
    <div className='App'>
   
      <Router>
        <Routes>
       
        <Route path="/" element={<LoginForm />} />
        <Route path="/MainPage" element={<Mainpage />} />
        <Route path="/OperatorMainPage" element={<Operatorpage />} />
        <Route path="/SystemBuffer" element={<LoginForm />} />

        
        </Routes>
      
      </Router>
    </div>
  );
}

export default App;
