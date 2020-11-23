import React, { FunctionComponent, ReactEventHandler } from 'react'
import { Link } from 'react-router-dom';
import '../../styles/Header.scss'

interface Props{
    HandleOffNav: () => void;
}

const HeaderLeft: React.FC<Props> = (props) => {
    
    var HandleOffNav = props.HandleOffNav;
    return (
        <div className= "menu-left-container">
            <div className="menu-left">
                <div className="account-controller">
                    <div className="item-list">
                        <Link to="/login">Đăng nhập</Link>
                    </div>
                    <div className = "item-list">
                        <Link to="/signup">Đăng kí</Link>
                    </div>
                </div>
                <hr/>
                <div className="account-controller">
                    <div className="item-list">
                        <Link to="/">Home</Link>
                    </div>
                    <div className = "item-list">
                        <Link to="/signup">Danh mục</Link>
                    </div>
                    <div className = "item-list">
                        <Link to="/signup">Tin tức</Link>
                    </div>
                    <div className = "item-list">
                        <Link to="/signup">Liên hệ</Link>
                    </div>
                </div>
            </div>
            <div className = "blur-container" onClick = {HandleOffNav}></div>
        </div>
    );
}


export default HeaderLeft;
