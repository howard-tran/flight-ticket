import express from "express";
import {
  createProduct,
  getProducts,
  getProductById,
} from "../controller/productController.js";
const router = express.Router();

// @desc Fetch all products
// @route GET /api/products
// @access Public
router.get("/", getProducts);

// @desc Fetch single product
// @route GET /api/products/:id
// @access Public
router.get("/:id", getProductById);

// @desc Create single product
// @route POST /api/products/
// @access User only
router.post("/", createProduct);

export default router;
