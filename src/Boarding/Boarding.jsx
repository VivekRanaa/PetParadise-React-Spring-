import React, {useState} from "react";
import "../App.css"
import "./Boarding.css"
import {Link} from "react-router-dom";

function Boarding() {




    return (
        <>
                <div className="Boarding-text">
                    <h2> Yes, often leaving your pooch behind when you are going out on a vacay can be heartbreaking. But it doesn't have to be a lonely time for them; they can be ecstatic and make new memories and friends at these boarding shelters in Bangalore! These are places that are run by dog lovers for the dogs to have an absolute blast when you are away and be returned to you happy and healthy.</h2>
                </div>
                <div className="Boarding-images">
                    <div className="images">
                        <ul>
                        <li id="li1"> <img src="../../public/Images/b1.jpeg" alt="Image Not Loading" />   </li>
                        <li id="li2">  <img src="../../public/Images/b2.jpeg" alt="Image Not Loading"/>  </li>
                        <li id="li3">  <img src="../../public/Images/t2.jpeg" alt="Image Not Loading" />    </li>
                    </ul>
                    </div>
                </div>
            <div className="fill-button" >
                ><Link to="/Details"> <button>Fill Details</button></Link>
            </div>

        </>
    )
}

export default Boarding