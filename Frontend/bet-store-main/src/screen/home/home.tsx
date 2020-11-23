import classNames from "classnames";
import { defaultCoreCipherList } from "constants";
import React from "react";
import { Link } from "react-router-dom";
import style from "./home.module.scss";


export default function Home() {
    return (
        <div className="hello">
            <h1 className="hello">Hello World</h1>
            <Link className={style.loginCs} to="/login">Login</Link>
        </div>
    );
} 
  