import React, {useEffect, useState} from "react";
import "./PetShop.css"
import axios from 'axios';
import {FiShoppingCart} from "react-icons/fi";
function PetShop() {

    return (
        <>
           <div className="petshop">
               <div className="petshop-header">
                   <div className="header-search-bar">
                       <h1>Search Here</h1>
                       <input
                           type="text"
                           className="textbox"
                           placeholder="Search data..."
                        />
                   </div>
                   <div className="header-icons"><FiShoppingCart className="icons" /></div>

               </div>
               <div className="petshop-body">
                   <div className="petshop-categories">
                       <h1>Categories</h1>
                   </div>
                   <div className="petshop-cards">
                       <h1>cards</h1>
                   </div>
               </div>

           </div>
        </>
    )
}

export default PetShop