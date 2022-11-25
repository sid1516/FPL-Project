import React from "react";

import fplImage from '../images/fpl-image.jpg'


import classes from './Header.module.css'

const Header = () => {
  return <React.Fragment>
    <header className = {classes.header}>
        <h1>FPL Draft Simulator</h1>
    </header>
    <div className = {classes['main-image']}>
        <img src ={fplImage} alt="A lot of Soccer Balls"/>
    </div>
  </React.Fragment>;
};

export default Header;