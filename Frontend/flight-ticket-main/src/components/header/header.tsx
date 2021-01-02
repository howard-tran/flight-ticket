import React from "react";

const Header: React.FC = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <img className="mb-4" src="/cdn/cdn/7f3c2238cb73ebb949c1535b33d1bb0701012021.png" alt="" width="42" height="42"
        style={{transform:"translateY(10px)", marginRight:"5px"}}></img>
      <a className="navbar-brand" href="#">AirBooking</a>
      <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>

      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <ul className="navbar-nav mr-auto">

          <li className="nav-item dropdown">
            <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Account
            </a>
            <div className="dropdown-menu" aria-labelledby="navbarDropdown">
              <a className="dropdown-item" href="/login">Sign in</a>
              <a className="dropdown-item" href="#">Sign up</a>
            </div>
          </li>

          <li className="nav-item active">
            <a className="nav-link" href="#">DashBoard <span className="sr-only">(current)</span></a>
          </li>

          <li className="nav-item">
            <a className="nav-link" href="#">Ticket</a>
          </li>

          <li className="nav-item">
            <a className="nav-link" href="#">Report</a>
          </li>
        </ul>
        
      </div>
    </nav>
  );
}

export default Header;