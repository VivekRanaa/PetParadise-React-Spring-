import React from "react";
import "../App.css";
import "./Grooming.css";
import { Link } from "react-router-dom";
import { FaCut, FaBath, FaRegGem, FaPaw, FaStar, FaHeart } from "react-icons/fa";

function Groom() {
    return (
        <>
            <div className="Groom-text">
                <h2>Pet Paradise is the leading pet grooming home service provider in Bangalore, we provide convenient, flexible and cost-effective pet grooming home services. Pet Paradise's pet grooming packages include a bath with shampoo and conditioner, full body hair trimming, nail cutting, ear cleaning, tick and flea treatment, and much more. Whether you are looking for a basic dog bath or a stylish dog hair cut, Pet Paradise's dog groomers have got you covered. For cats, we have specialized teams of professional cat groomers that can make your cat fall in love with their grooming sessions.</h2>
            </div>
            <div className="Groom-images">
                <div className="images">
                    <ul>
                        <li id="li1"> <img src="/Images/grooming-professional-bathing.jpg" alt="Professional Dog Bathing Service" />   </li>
                        <li id="li2">  <img src="/Images/grooming-spa-treatment.jpg" alt="Luxury Pet Spa and Drying Treatment"/>  </li>
                        <li id="li3">  <img src="/Images/grooming-styling-service.jpg" alt="Professional Pet Styling and Grooming" />    </li>
                    </ul>
                </div>
            </div>
            <div className="fill-button" >
                <Link to="/Details"> <button>Fill Details</button></Link>
            </div>
        </>
    )
}

export default Groom
