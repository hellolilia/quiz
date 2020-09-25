import React from 'react';
import {BrowserRouter as Router, Link, NavLink, Route} from 'react-router-dom';
import './App.css';
import Mall from "./components/Mall";
import Order from "./components/Order";
import AddProduct from "./components/AddProduct";
import imgMallURL from './assets/Mall.png';
import imgAddProductURL from './assets/AddProduct.png';
import imgOrderURL from './assets/Order.png';

function App() {
  return (
    <div className="App">
      <Router>
        <header className="App-header">
          <ul>
            <li>
              <img src={imgMallURL} />
              <Link to='/'>商城</Link>
            </li>
            <li>
              <img src={imgOrderURL} />
              <Link to='/order'>订单</Link>
            </li>
            <li>
              <img src={imgAddProductURL} />
              <Link to='/add'>添加商品</Link>
            </li>
          </ul>
        </header>
        <switch>
          <Route exact path='/' component={Mall} />
          <Route path='/order' component={Order} />
          <Route path='/add' component={AddProduct} />
        </switch>
      </Router>
    </div>
  );
}

export default App;
