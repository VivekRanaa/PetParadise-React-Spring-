
import './App.css'
import {BrowserRouter as Router, Routes , Route} from "react-router-dom";
import Navbar from "./Navbar/Navbar.jsx";
import Home from "./Pages/Home.jsx";
import Boarding from "./Boarding/Boarding.jsx";
import Details from "./Details/Details.jsx";
import Pool from "./Pool Sessions/Pool_fixed.jsx";
import Groom from "./Grooming/Grooming_fixed.jsx";
import PetShop from "./PetShop/PetShop.jsx";
import Login from './components/Auth/Login.jsx';
import Register from './components/Auth/Register.jsx';
import Cart from './components/Cart/Cart.jsx';
import Checkout from './components/Checkout/Checkout.jsx';
import OrderConfirmation from './components/OrderConfirmation/OrderConfirmation.jsx';
import Orders from './components/Orders/Orders.jsx';
import { AuthProvider } from './context/AuthContext.jsx';
import { CartProvider } from './context/CartContext.jsx';

function App() {

  return (
    <>
        <AuthProvider>
          <CartProvider>
            <Router>
              <Navbar />
                <Routes>
                    <Route exact path='/' element={<Home />} />
                    <Route exact path='/Boarding' element={<Boarding />} />
                    <Route exact path="/Details"  element={<Details />}/>
                    <Route exact path="/PoolSessions" element={<Pool />} />
                    <Route exact path="/Grooming" element={<Groom />} />
                    <Route exact path="/PetShop" element={<PetShop/>} />
                    <Route exact path="/login" element={<Login />} />
                    <Route exact path="/register" element={<Register />} />
                    <Route exact path="/cart" element={<Cart />} />
                    <Route exact path="/checkout" element={<Checkout />} />
                    <Route exact path="/order-confirmation" element={<OrderConfirmation />} />
                    <Route exact path="/orders" element={<Orders />} />
                 </Routes>
            </Router>
          </CartProvider>
        </AuthProvider>
    </>
  )
}

export default App
