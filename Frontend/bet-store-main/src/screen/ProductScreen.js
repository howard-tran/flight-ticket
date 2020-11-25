import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Row, Col, Image, ListGroup, Card, Button } from "react-bootstrap";
import axios from "axios";
import AwesomeSlider from "react-awesome-slider";
import "react-awesome-slider/dist/styles.css";

const nodeServer = process.env.REACT_APP_NODE_SERVER;

const ProductScreen = ({ match }) => {
  const [product, setProduct] = useState({});
  const [propertyLabel, setPropertyLabel] = useState([]);
  const [properties, setProperties] = useState([]);
  const [images, setImages] = useState([]);
  useEffect(() => {
    const fetchProduct = async () => {
      const { data } = await axios.get(
        `${nodeServer}/api/products/${match.params.id}`
      );
      setProduct(data);
      setProperties(data.properties);
      setImages(data.image);
    };
    fetchProduct();

    const fetchLabel = async () => {
      const { data } = await axios.get(
        `${nodeServer}/api/categories/${product.category}`
      );
      setPropertyLabel(data.properties);
    };
    fetchLabel();
  }, [match, product]);

  return (
    <>
      <Link className="btn btn-light my-3" to="/">
        Go Back
      </Link>
      <Row>
        <Col md={6}>
          <AwesomeSlider>
            {images.map((img) => (
              <div data-src={`http://localhost:8080/cdn/${img.link}`} />
            ))}
          </AwesomeSlider>
        </Col>
        <Col md={3}>
          <ListGroup variant="flush">
            <ListGroup.Item>
              <h3>{product.name}</h3>
              <h3>{product.category}</h3>
            </ListGroup.Item>
            <ListGroup.Item>Price: ${product.price}</ListGroup.Item>
            <ListGroup.Item>Description: {product.description}</ListGroup.Item>
          </ListGroup>
        </Col>
        <Col md={3}>
          <Card>
            <ListGroup variant="flush">
              <ListGroup.Item>
                <Row>
                  <Col>Price:</Col>
                  <Col>
                    <strong>${product.price}</strong>
                  </Col>
                </Row>
              </ListGroup.Item>
              <ListGroup.Item>
                <Row>
                  <Col>Status:</Col>
                  <Col>
                    {product.countInStock > 0 ? "In Stock" : "Out Of Stock"}
                  </Col>
                </Row>
              </ListGroup.Item>
              <ListGroup.Item>
                <Button
                  className="btn-block"
                  type="button"
                  disabled={product.countInStock === 0}
                >
                  Add To Cart
                </Button>
              </ListGroup.Item>
            </ListGroup>
          </Card>
        </Col>
      </Row>
      <Row>
        <ListGroup variant="flush">
          {propertyLabel.map((prop) => (
            <ListGroup.Item>
              <Col>{prop.name}</Col>
              <Col>{properties.find((x) => x.key === prop.key).value}</Col>
            </ListGroup.Item>
          ))}
        </ListGroup>
      </Row>
    </>
  );
};

export default ProductScreen;
