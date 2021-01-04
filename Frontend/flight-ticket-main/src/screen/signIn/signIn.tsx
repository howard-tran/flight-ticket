import React, { useEffect, useReducer } from "react";
import { useDispatch, useSelector } from "react-redux";
import { ActionType } from "../../constants/ActionType";
import { AccountInfo } from "../../reducers/AccountReducer";
import Axios from "axios";
import style from "./signIn.module.scss";
import { ApiResponse } from "../../constants/ApiResponse";
import { agentId } from "../../constants/Agent";

const SignIn: React.FC = () => {
  const accountState = useSelector((state : {accountInfo: AccountInfo}) => state.accountInfo);
  const dispath = useDispatch();

  const changeAccountInfo = (propery: string, value: string): ActionType<String> => {
    return {
      type: propery,
      value: value
    }
  }
  
  const onSubmitHandle = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    
    Axios.post("/java/api/account/signin", {...accountState, agencyId: agentId})
      .then(res => {
        let res_t = res.data as ApiResponse<string>;

        window.localStorage.setItem("accountId", res_t.data);
        
        alert("ok");
        location.href = "/ticket";
      });
  }

  return (
    <div className={style.main + " text-center"}>
      <form className="form-signin" onSubmit={onSubmitHandle}>
        <i className="fa fa-user fa-5x"></i>
        <h1 className="h3 mb-3 font-weight-normal">Please sign in</h1>

        <label htmlFor="inputUsername" className="sr-only">Username</label>
        <input type="text" id="inputUsername" className="form-control" placeholder="Username" 
          required={true} autoFocus={true}
          onChange={(e) => {dispath(changeAccountInfo("username", e.target.value))}}>
          
        </input>

        <label htmlFor="inputPassword" className="sr-only">Password</label>
        <input type="password" id="inputPassword" className="form-control" 
          placeholder="Password" required={true}
          onChange={(e) => {dispath(changeAccountInfo("password", e.target.value))}}>

        </input>

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