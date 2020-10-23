import React from 'react';
import './Products.css'
import addProductImage from '../../assets/AddProduct.png'

const Products = ({name,price,unit,image}) => {

    return (
        <div className="product">
            <img className='productImg' src={image} alt='product'/>
            <p className="productName">{name}</p>
            <p className='productPrice'>单价：{price}元/{unit}</p>
            <button> <img src={addProductImage} alt='add'/></button>
        </div>
    )
}


export default Products;