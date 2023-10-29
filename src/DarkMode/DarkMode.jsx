import React from "react";
import Sun from "./Sun.svg";
import Moon from "./Moon.svg";
import "./DarkMode.css";

const DarkMode = () => {
    const setDarkMode = () =>{
        document.querySelector("body").setAttribute("data-theme","dark")
        localStorage.setItem("SelectedTheme","dark")
    }
    const setLightMode = () =>{
        document.querySelector("body").setAttribute("data-theme","light")
        localStorage.setItem("SelectedTheme","light")
    }
    const selectedTheme=localStorage.getItem("SelectedTheme")
    if(selectedTheme==="dark")
    {
            setDarkMode()
    }
    else setLightMode()
    const toggleTheme = (e)=>{
        if(e.target.checked) setDarkMode()
        else setLightMode()
    }

    return (
        <div className='dark_mode'>
            <input
                className='dark_mode_input'
                type='checkbox'
                id='darkmode-toggle'
                onChange={toggleTheme}
                defaultChecked={selectedTheme==="dark"}
            />
          <label className='dark_mode_label' htmlFor='darkmode-toggle'>
                {/*<img src={Sun} alt="" />
                <img src={Moon} alt="" />*/}
            </label>
        </div>
    );
};

export default DarkMode;
