
import './App.css'
import {BrowserRouter as Router, Routes , Route} from "react-router-dom";
import Navbar from "./Navbar/Navbar.jsx";
import Home from "./Pages/Home.jsx";
import Boarding from "./Boarding/Boarding.jsx";
import Details from "./Details/Details.jsx";
import Pool from "./Pool Sessions/Pool.jsx";
function App() {


  return (
    <>
        <Router>
          <Navbar />
            <Routes>
                <Route exact path='/' element={<Home />} />
                <Route exact path='/Boarding' element={<Boarding />} />
                <Route exact path="/Details"  element={<Details />}/>
                <Route exact path="/PoolSessions" element={<Pool />} />
             </Routes>
        </Router>
    </>
  )
}

export default App
