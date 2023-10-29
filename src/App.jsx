
import './App.css'
import {BrowserRouter as Router, Routes , Route} from "react-router-dom";
import Navbar from "./Navbar/Navbar.jsx";
import Home from "./Pages/Home.jsx";
function App() {


  return (
    <>
        <Router>
          <Navbar />
            <Routes>
                <Route exact path='/' element={<Home />} />
            </Routes>
        </Router>
    </>
  )
}

export default App
