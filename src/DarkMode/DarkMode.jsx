import React from "react";
import { FaSun, FaMoon } from 'react-icons/fa';
import "./DarkMode.css";

const DarkMode = () => {
    const setDarkMode = () =>{
        document.documentElement.setAttribute("data-theme","dark")
        localStorage.setItem("SelectedTheme","dark")
    }
    const setLightMode = () =>{
        document.documentElement.setAttribute("data-theme","light")
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
        <div className='dark_mode' title={selectedTheme === "dark" ? "Switch to Light Mode" : "Switch to Dark Mode"}>
            <input
                className='dark_mode_input'
                type='checkbox'
                id='darkmode-toggle'
                onChange={toggleTheme}
                defaultChecked={selectedTheme==="dark"}
            />
          <label className='dark_mode_label' htmlFor='darkmode-toggle'>
                <FaSun className="sun-icon" />
                <FaMoon className="moon-icon" />
            </label>
        </div>
    );
};

export default DarkMode;
