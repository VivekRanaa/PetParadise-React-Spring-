import React, {useState} from "react";
import "../App.css"
import "./Pool.css"
import {Link} from "react-router-dom";

function Pool() {




    return (
        <>
            <div className="Pool-text">
                <h2> Our four-legged friends could use a good pool party,
                    because they get the same amount of enjoyment from splashing around with friends,
                    soaking up the sun and enjoying sweet treats poolside.
                    (As a plus, they don’t have to clean up after themselves when the party is over!)</h2>
            </div>
            <div className="Pool-images">
                <div className="images">
                    <ul>
                        <li id="li1"> <img src="/Images/p1.jpg" alt="Image Not Loading" />   </li>
                        <li id="li2">  <img src="/Images/p2.jpg" alt="Image Not Loading"/>  </li>
                        <li id="li3">  <img src="/Images/poolparty.jpg" alt="Image Not Loading" />    </li>
                    </ul>
                </div>
            </div>
            <div className="fill-button" >
                ><Link to="/Details"> <button>Fill Details</button></Link>
            </div>

        </>
    )
}

export default Pool