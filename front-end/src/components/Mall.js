import React, { Component } from 'react';
import '../styles/Mall.css'
import Products from "./Products";

class Mall extends Component {
    constructor(props){
        super(props);
        this.state={
            products: []
        }
    }
    getData(){
        fetch('http://localhost:8080/product',{
            method:'GET',
            mode: 'cors',
        }).then(res=>res.json()).then(
            data=>{
                console.log(data);
                this.setState({
                    products :data})
                })

    }
    componentDidMount(){
        this.getData();
    }
    render() {
        return (
            <div className='mall'>
            <React.Fragment>
                <ul>
                    {this.state.products.map( item =>{
                        return (
                            <li key={item}>
                                <Products
                                    name={item.name}
                                    price={item.price}
                                    unit={item.unit}
                                    image={item.image}
                                />
                            </li>)
                    })}
                </ul>

            </React.Fragment>
            </div>
        )
    }
}

export default Mall;