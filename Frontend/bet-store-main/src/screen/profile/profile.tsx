import React from "react";

import styles from "../../styles/Profile.module.scss";
import InfoProfile from "./InfoProfile";
import { NavLeft } from "./NavLeft";

export default function Profile() {
    return (
        <div className = {styles.ProfileContainer}>
            <div className = {"container " + styles.Profile}>
                <div className = {styles.NavLeftContainer}>
                    <NavLeft></NavLeft>
                </div>
                <div className = {styles.InfoProfileContainer}>
                    <InfoProfile/>
                </div>
            </div>
            
        </div>
    );
}
