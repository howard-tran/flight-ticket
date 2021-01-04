import React, { useEffect } from "react";
import { Route, Switch } from "react-router";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../src/resource/font-awesome/css/font-awesome.min.css";
import "../node_modules/popper.js/dist/popper";
import "../node_modules/bootstrap/dist/js/bootstrap";
import "../node_modules/jquery/dist/jquery";
import { BrowserRouter } from "react-router-dom";
import { Provider } from "react-redux";
import { combineReducers, createStore } from "redux";
import { useDispatch } from "react-redux";
import Header from "./components/header/header";
import style from "./App.module.scss";
import Footer from "./components/footer/footer";
import SignIn from "./screen/signIn/signIn";
import { Ticket } from "./screen/ticket/ticket";
import { SubmitReceipt } from "./screen/receipt/submitReceipt";

function App() {
  const dispatch = useDispatch();
  return (
    <div className={style.app}>
      <BrowserRouter>
        <Header></Header>
        {/* header tag */}
        <Switch>
          {/* apply routing */}

          <Route path="/" exact>
            <Ticket></Ticket>
          </Route>

          <Route path="/login" exact>
            <SignIn></SignIn>
          </Route>

          <Route path="/ticket" exact>
            <Ticket></Ticket>
          </Route>

          <Route path="/submitReceipt/:flightId" component={SubmitReceipt} exact>
          </Route>
        </Switch>
        
        <Footer></Footer>
        {/* footer tag */}
      </BrowserRouter>
    </div>
  );
}

export default App;
