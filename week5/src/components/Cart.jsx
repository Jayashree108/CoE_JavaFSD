import React from "react";
import { useCart } from "../context/CartContext";

const Cart = () => {
  const { cart, dispatch } = useCart();
  const total = cart.reduce((sum, item) => sum + item.price, 0);

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold">Cart</h2>
      {cart.length === 0 ? (
        <p>No items in cart.</p>
      ) : (
        cart.map((item) => (
          <div key={item.id} className="flex justify-between p-2 border">
            <p>{item.name}</p>
            <button
              className="bg-red-500 text-white p-1"
              onClick={() => dispatch({ type: "REMOVE_FROM_CART", payload: item.id })}
            >
              Remove
            </button>
          </div>
        ))
      )}
      <h3 className="mt-4">Total: ${total}</h3>
    </div>
  );
};

export default Cart;
