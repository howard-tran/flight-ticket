import React from "react";
import { Link } from "react-router-dom";
import { Card } from "react-bootstrap";

const Product = ({ product }) => {
  return (
    <Card>
      <Link to={`/product/${product._id}`}>
        {/* <Card.Img src={`/cdn/cdn/${product.image[0].link}`} variant="top" /> */}
        <Card.Img
          src="https://dummyimage.com/250x250.jpg/5fa2dd/ffffff"
          variant="top"
        />
      </Link>
      <Card.Body>
        <Link to={`/product/${product._id}`}>
          <Card.Title as="div">
            <strong>{product.name}</strong>
          </Card.Title>
        </Link>

        <Card.Text as="h6">{product.price} ₫</Card.Text>
      </Card.Body>
    </Card>
  );
};

export default Product;
