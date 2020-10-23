import React, { Component } from 'react';
import './Order.css'

class Order extends Component {


    render() {
        return (
            <div className='order'>
                <ul>
                    <li>名字</li>
                    <li>单价</li>
                    <li>数量</li>
                    <li>单位</li>
                    <li>操作</li>

                </ul>
            </div>
        );
    }

}

export default Order;