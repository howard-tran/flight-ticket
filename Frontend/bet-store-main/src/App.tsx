import React from "react";
import { Route, Switch } from "react-router";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
//import Login from './components/Login';

import "../src/resource/font-awesome/css/font-awesome.min.css"
import "../node_modules/popper.js/dist/popper"
import "../node_modules/bootstrap/dist/js/bootstrap"
import "../node_modules/jquery/dist/jquery"
import "./App.css"
import Login from './components/Login';
import { BrowserRouter } from 'react-router-dom';
import Header from './components/header/Header';
import Home from './screen/home/home';
import FooterView from './components/footer/Footer';
import ProductScreen from './screen/ProductScreen';
import AddProductScreen from './screen/AddProductScreen';
import ChatBox from "./components/ChatBox";
import { Provider } from "react-redux";
import { combineReducers, createStore } from "redux";
import { accountInfoReducer, conversationControlReducer, messageControlReducer, socketInfoReducer, viewControlReducer } from "./reducers/chatBoxReducer";
import ProductListScreen from "./screen/ProductListScreen";


function App() {
  return (
    <div>
      <BrowserRouter>
        <div className="headermain">
          <Header></Header>
        </div>

          <Switch>          
            <Route path="/" exact>{Home}</Route>
            <Route path="/login" exact > <Login islogin = {true}/></Route> 
            <Route path="/signup" exact> <Login islogin = {false}/></Route> 
            <Route path="/product/:id" component={ProductScreen} exact></Route>
            <Route path="/addProduct" component={AddProductScreen} exact></Route>
          </Switch>

          <Provider
            store={createStore(
              combineReducers({
                conversationControl: conversationControlReducer,
                messageControl: messageControlReducer,
                chatAccountInfo: accountInfoReducer,
                viewControl: viewControlReducer,
                socketInfo: socketInfoReducer
              })
            )}
          >
            <ChatBox></ChatBox>
          </Provider>
        <div className = "footermain">
          <FooterView></FooterView>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
