import React from "react";
import "../App.css";
import "./Pool.css";
import { Link } from "react-router-dom";
import { FaSwimmingPool, FaSun, FaHeart, FaUsers, FaWater, FaStar } from "react-icons/fa";

function Pool() {
    return (
        <>
            <div className="Pool-text">
                <h2>Our four-legged friends could use a good pool party,
                    because they get the same amount of enjoyment from splashing around with friends,
                    soaking up the sun and enjoying sweet treats poolside.
                    (As a plus, they don't have to clean up after themselves when the party is over!)</h2>
            </div>
            <div className="Pool-images">
                <div className="images">
                    <ul>
                        <li id="li1"> <img src="/Images/pool-underwater-therapy.jpg" alt="Underwater Pet Therapy Session" />   </li>
                        <li id="li2">  <img src="/Images/pool-surface-swimming.jpg" alt="Dogs Enjoying Pool Time on Surface"/>  </li>
                        <li id="li3">  <img src="/Images/pool-group-activity.jpg" alt="Group Pool Activities and Socialization" />    </li>
                    </ul>
                </div>
            </div>
            <div className="fill-button" >
                <Link to="/Details"> <button>Fill Details</button></Link>
            </div>
        </>
    )
}

export default Pool
