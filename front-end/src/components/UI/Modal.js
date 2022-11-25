import React from "react";
import classes from "./Modal.module.css";
import ReactDOM  from "react-dom";

const Modal = (props) => {
  const phrase = "Please select a valid player!";
  return ReactDOM.createPortal(
    <>
    <div className={classes.modal}>
      <div className={classes.overlay} onClick={props.changeModal}></div>
      <div className={classes.content}>
        <h2>{phrase}</h2>
        <button onClick={props.changeModal}>Close</button>
      </div>
    </div>
    </>,
    document.getElementById('portal')
  );
};
export default Modal;
