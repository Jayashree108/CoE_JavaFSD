import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav className="p-4 bg-blue-500 text-white flex justify-between">
      <Link to="/" className="text-lg font-bold">E-Shop</Link>
      <Link to="/cart" className="text-lg">Cart</Link>
    </nav>
  );
};

export default Navbar;