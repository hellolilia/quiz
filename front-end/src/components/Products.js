import React from 'react';
import '../styles/Products.css'
import productImagePlaceholder from '../assets/product_image_placeholder.png';
import addProductImage from '../assets/AddProduct.png'

const Products = ({name,price,unit,image}) => {

    return (
        <div className="product">
            <img className='productimg' src={productImagePlaceholder || {image}} alt='product'/>
            <p className="productName">{name}</p>
            <p className='productPrice'>单价：{price}元/{unit}</p>
            <button> <img src={addProductImage} alt='add'/></button>
        </div>
    )
}


export default Products;