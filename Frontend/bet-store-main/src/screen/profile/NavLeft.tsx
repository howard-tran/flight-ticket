import React from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { AppState } from '../../store';
import styles from "../../styles/Profile.module.scss"

export const NavLeft: React.FC = () => {
    const dispatch = useDispatch();
    const profile = useSelector((state: AppState) => state.profile)
    return (
        <div className={styles.NavLeftContainer}>
            <div className={styles.ProfileNav}>
                <span className = {styles.Avatar}>
                    <img src={"/cdn/cdn/" + profile.Payload.avatar} alt="avatar"></img>
                </span>
                <div className = {styles.Account}>
                    Tài khoản của
                    <strong>
                        {profile.Payload.surname + " " + profile.Payload.name}
                    </strong>
                </div>
            </div>

            <ul className={styles.NavLeft}>
                <li className={styles.ChildNav}>
                    <span className={styles.active}>
                        <i className="fas fa-user"></i>
                        <span>Thông tin tài khoản</span>
                    </span>
                </li>
                <li className={styles.ChildNav}>
                    <span>
                        <i className="fas fa-wallet"></i>
                        <span>Ví điện tử</span>
                    </span>
                </li>
                <li className={styles.ChildNav}>
                    <span>
                        <i className="fas fa-tasks"></i>
                        <span>Quản lý cửa hàng</span>
                    </span>
                </li>
                <li className={styles.ChildNav}>
                    <span>
                        <i className="fas fa-question-circle"></i>
                        <span>Hỗ trợ</span>
                    </span>
                </li>
            </ul>
        </div>
    );
}


