import React from 'react';
import { Link } from 'react-router-dom';
import '../../styles/Header.scss'
import HeaderLeft from './HeaderLeft';
import $ from 'jquery'

const Header: React.FC = () => {

    var [isLeftNavBar, setLeftNavBar] = React.useState<boolean>(false);

    const handleOffNav=()=>{
        setLeftNavBar(false);
    }
    const handleOnNavLeft=()=>{
        setLeftNavBar(true);
        // var ele = $('left-header-nav');
        // console.log(ele);
        // if(ele.hasClass('active-left-nav')){
        //     ele.addClass('active-left-nav');
        // }
    }

    return (
        <div>
            {isLeftNavBar && <div className = "left-header-nav" id = "left-header-nav">
                <HeaderLeft HandleOffNav = {handleOffNav}/>
            </div>}
            <nav className="white-nav-container">

                <div className="btn-nav-left" >
                    <a href="#" onClick = {handleOnNavLeft} >
                        <i className="fas fa-caret-square-right"></i>
                    </a>
                </div>

                <a href="/">
                    <div className="logo-container">
                        <img src="/static/media/logo.png" alt="logo-betstore" />
                        <h2>BetStore</h2>
                    </div>
                </a>
                <div className="search-container">
                    <i className="fas fa-search"></i>
                    <input type="text" placeholder="Bạn muốn tìm gì?" spellCheck="false"></input>
                </div>

                <div className="buttons">
                    <div className="button">
                        <button className="noti-btn">
                            <i className="fas fa-envelope"></i>
                            <h4>
                                Tin nhắn
                            </h4>
                        </button>
                    </div>

                    <div className="button">
                        <button className="noti-btn">
                            <i className="fas fa-bell"></i>
                            <h4>
                                Thông báo
                            </h4>
                        </button>
                    </div>

                    <div className="button dropdown-hover">
                        <div className=" noti-btn drop-btn">
                            <i className="fas fa-user"></i>
                            <h4>
                                Tài khoản
                            </h4>
                        </div>
                        <div className="dropdown-list">
                            <div>
                                <a href="/login">Đăng nhập</a>
                            </div>
                            <div>
                                <a href="/signup">Đăng kí</a>
                            </div>
                        </div>

                    </div>
                </div>
            </nav>
        </div>
    );
};

export default Header;


// <div className= "page-wrap">
        //     <div className = "nav-style">
        //         <nav className="navbar navbar-expand-lg navbar-light">
        //             <a className="navbar-brand" href="#">Navbar</a>

        //             <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        //                 <span className="navbar-toggler-icon"/>
        //             </button>
        //             <div className="collapse navbar-collapse" id="navbarNav">
        //                 <ul className="navbar-nav ml-auto">
        //                     <li className="nav-item active">
        //                         <a className="nav-link" href="#">Home <span className="sr-only">(current)</span></a>
        //                     </li>
        //                     <li className="nav-item">
        //                         <a className="nav-link" href="#">Features</a>
        //                     </li>
        //                     <li className="nav-item">
        //                         <a className="nav-link" href="#">Pricing</a>
        //                     </li>
        //                     <li className="nav-item">
        //                         <a className="nav-link disabled" href="#" tabIndex={-1} aria-disabled="true">Disabled</a>
        //                     </li>
        //                 </ul>
        //             </div>
        //             {/* <div className = "btn-search">
        //                 <a href = "#"><img width='20px' height = '20px' src = {searchicon} alt = "search-icon"/></a>
        //             </div> */}
        //         </nav>

        //     </div>
        // </div>