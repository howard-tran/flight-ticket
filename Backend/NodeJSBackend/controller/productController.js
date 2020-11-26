import mongoose from "mongoose";
import Product from "../models/productModel.js";
import asyncHandler from "express-async-handler";

export const createProduct = asyncHandler(async (req, res) => {
  try {
    const newProduct = new Product({
      _id: mongoose.Types.ObjectId(),
      ...req.body,
    });
    await Product.insertMany(newProduct);

    console.log("Added new product");
    res.status(201).json({
      message: "product created successfully!",
    });
  } catch (error) {
    throw new Error(error);
  }
});

export const getProducts = asyncHandler(async (req, res) => {
  let products;
  if (req.body.constructor === Object && Object.keys(req.body).length === 0) {
    products = await Product.find({});
  } else {
    products = await Product.find(req.body);
  }
  res.json(products);
});

export const getProductById = asyncHandler(async (req, res) => {
  const product = await Product.findById(req.params.id);

  if (product) {
    res.json(product);
  } else {
    res.status(404);
  }
});
