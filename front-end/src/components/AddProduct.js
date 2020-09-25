import React, { Component } from 'react';
import '../styles/AddProduct.css'

class AddProduct extends Component {


    state = {
        name: '',
        price: '',
        unit: '',
        picture: ''
    }

    handleChange = (event) => {
        const target = event.target;
        const value =  target.value;
        const name = target.name;
        this.setState({
            [name]: value
        });
    }


    handleSubmit = (event) => {
        event.preventDefault();
        console.log(JSON.stringify(this.state))
    }


    render() {
        return <form onSubmit={this.handleSubmit}>
            <h1>添加商品</h1>
            <label className='name'>
                名称：
                <input type='text'
                       name='name'
                       placeholder='名称'
                       onChange={this.handleChange}
                       value={this.state.name}/>
            </label>
            <label className='price'>
                价格：
                <input type='text'
                       name='price'
                       placeholder='价格'
                       pattern="(?!^0*(\.0{1,2})?$)^\d{1,13}(\.\d{1,2})?$"
                       onChange={this.handleChange}
                       value={this.state.price}/>
            </label>
            <label className='unit'>
                单位：
                <input type='text'
                       name='unit'
                       placeholder='单位'
                       onChange={this.handleChange}
                       value={this.state.unit}/>
            </label>
            <label className='picture'>
                图片：
                <input type='text'
                       name='picture'
                       placeholder='URL'
                       pattern="https?://.+"
                       onChange={this.handleChange}
                       value={this.state.picture}/>
            </label>
            <input className='submit'
                   type='submit'
                   name='submit'
                   value='提交'
                   disabled={!this.state.name || !this.state.price || !this.state.unit || !this.state.picture}/>
        </form>

    }
}
export default AddProduct;