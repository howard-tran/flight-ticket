import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Row, Col, Image, ListGroup, Card, Button } from "react-bootstrap";
import AwesomeSlider from "react-awesome-slider";
import "react-awesome-slider/dist/styles.css";
import { useDispatch, useSelector } from "react-redux";
import { listProductDetails } from "../actions/productActions";
import { listCategoryDetails } from "../actions/categoryActions";

const ProductScreen = ({ match }) => {
  const dispatch = useDispatch();

  const [properties, setProperties] = useState([]);
  const [images, setImages] = useState([]);

  const productDetails = useSelector((state) => state.productDetails);
  const { product } = productDetails;

  const categoryDetails = useSelector((state) => state.categoryDetails);
  const { category } = categoryDetails;

  useEffect(async () => {
    const getProductDetails = () => {
      return dispatch(listProductDetails(match.params.id)).then(() =>
        console.log(product.category)
      );
    };
    getProductDetails();
  }, [dispatch, match]);

  useEffect(() => {
    if (product) {
      setImages(product.image);
      setProperties(product.properties);
      if (product.category) {
        dispatch(listCategoryDetails(product.category));
      }
    }
  }, [dispatch, productDetails.loading]);

  return (
    <>
      {/*<Link className="btn btn-light my-3" to="/">
        Go Back
  </Link>*/}
      {productDetails.loading || categoryDetails.loading ? (
        <h2>Loading...</h2>
      ) : productDetails.error ? (
        <h3>{productDetails.error}</h3>
      ) : (
        <Row>
          <Col md={6}>
            <AwesomeSlider>
              {images.map((img) => (
                <div data-src={`/cdn/cdn/${img.link}`} />
              ))}
            </AwesomeSlider>
          </Col>
          <Col md={3}>
            <ListGroup variant="flush">
              <ListGroup.Item>
                <h3>{product.name}</h3>
              </ListGroup.Item>
              <ListGroup.Item>Price: ${product.price}</ListGroup.Item>
              <ListGroup.Item>
                Description: {product.description}
              </ListGroup.Item>
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
              <ListGroup variant="flush">
                {category.properties.map((prop) => (
                  <ListGroup.Item>
                    <Row>
                      <Col>{prop.name}</Col>
                      <Col>
                        {properties.find((x) => x.key === prop.key).value}
                      </Col>
                    </Row>
                  </ListGroup.Item>
                ))}
              </ListGroup>
            </Card>
          </Col>
        </Row>
      )}
    </>
  );
};

export default ProductScreen;
