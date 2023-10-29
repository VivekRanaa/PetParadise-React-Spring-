
import './App.css'
import {BrowserRouter as Router, Routes , Route} from "react-router-dom";
import Navbar from "./Navbar/Navbar.jsx";
function App() {


  return (
    <>
        <Router>
          <Navbar />
            <Routes>
            <Route path='/' exact />
            </Routes>
        </Router>
    </>
  )
}

export default App
