import React from 'react';
import {BrowserRouter as Router, Link, Switch, Route} from 'react-router-dom';
import './App.css';
import Mall from "./components/Mall/Mall";
import Order from "./components/Order/Order";
import AddProduct from "./components/AddProduct/AddProduct";
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
              <img src={imgMallURL} alt={'mall'}/>
              <Link to='/'>商城</Link>
            </li>
            <li>
              <img src={imgOrderURL} alt={'order'}/>
              <Link to='/order'>订单</Link>
            </li>
            <li>
              <img src={imgAddProductURL} alt={'add product'}/>
              <Link to='/add'>添加商品</Link>
            </li>
          </ul>
        </header>
        <Switch>
          <Route exact path='/' component={Mall} />
          <Route path='/order' component={Order} />
          <Route path='/add' component={AddProduct} />
        </Switch>
        <footer>
          <p>TW Mall ©2018 Created by ForCheng</p>
        </footer>
      </Router>
    </div>
  );
}

export default App;
