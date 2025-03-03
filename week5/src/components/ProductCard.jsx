import React from "react";
import { useCart } from "../context/CartContext";

const ProductCard = ({ product }) => {
  const { dispatch } = useCart();

  return (
    <div className="border p-4 m-2 shadow-lg">
      <h2 className="text-lg font-bold">{product.name}</h2>
      <p className="text-gray-700">${product.price}</p>
      <button
        className="mt-2 bg-green-500 text-white p-2"
        onClick={() => dispatch({ type: "ADD_TO_CART", payload: product })}
      >
        Add to Cart
      </button>
    </div>
  );
};

export default ProductCard;