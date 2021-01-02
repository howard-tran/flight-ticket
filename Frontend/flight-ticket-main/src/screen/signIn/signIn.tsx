import React from "react";
import style from "./signIn.module.scss";

const SignIn: React.FC = () => {
  
  return (
    <div className={style.main + " text-center"}>
      <form className="form-signin">
        <img className="mb-4" src="/cdn/cdn/7f3c2238cb73ebb949c1535b33d1bb0701012021.png" alt="" width="72" height="72"></img>
        <h1 className="h3 mb-3 font-weight-normal">Please sign in</h1>

        <label htmlFor="inputUsername" className="sr-only">Username</label>
        <input type="email" id="inputUsername" className="form-control" placeholder="Username" 
          required={true} autoFocus={true}></input>

        <label htmlFor="inputPassword" className="sr-only">Password</label>
        <input type="password" id="inputPassword" className="form-control" placeholder="Password" required={true}></input>

        <div className="checkbox mb-3">
          <label>
            
            <input type="checkbox" value="remember-me"></input> Remember me
          </label>
        </div>
        <button className="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <p className="mt-5 mb-3 text-muted">© 2017-2018</p>
      </form>
    </div>
  )
};

export default SignIn;