import React from 'react';
import Switch from 'react-bootstrap/esm/Switch';
import { Route } from 'react-router';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
//import Login from './components/Login';
import "../src/resource/font-awesome/css/font-awesome.min.css"
import "./App.css"
import Login from './components/Login';
import { BrowserRouter } from 'react-router-dom';
import Header from './components/header/Header';
import Home from './screen/home';



function App() {
  return (
    <div>
      <BrowserRouter>
        <div className = "headermain">
          <Header></Header>
        </div>
        <div className="container">
          <Switch>
            <Route path="/login" exact component={Login} />
            <Route path="/" exact component={Home} />
            <Route path="/signup" exact component={Login} />
          </Switch>
        </div>
      </BrowserRouter>
    </div>

  );
}

export default App;
