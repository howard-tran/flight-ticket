import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Row, Col, Card, CardGroup, Container } from "react-bootstrap";
import Product from "../../components/Product";
import Category from "../../components/Category";
import { listProducts } from "../../actions/productActions";
import style from "../../styles/ProductDisplay.module.scss";
import { listCategories } from "../../actions/categoryActions";
const HomeScreen = () => {
  const dispatch = useDispatch();

  const productList = useSelector((state) => state.productList);
  const { loading, error, products } = productList;

  const categoryList = useSelector((state) => state.categoryList);
  const {
    loading: loadingCategories,
    error: errorCategories,
    categories,
  } = categoryList;

  useEffect(() => {
    dispatch(listProducts());
    dispatch(listCategories());
  }, [dispatch]);

  return (
    <>
      <Container className={style.categoryContainer}>
        <Row>
          <h4>Danh mục</h4>
        </Row>
        <Row>
          {loadingCategories ? (
            <h2>Loading...</h2>
          ) : errorCategories ? (
            <h3>{errorCategories}</h3>
          ) : (
            <Container>
              <Row className={style.categoryDisplay}>
                {categories.map((category) => (
                  <Category className={style.category} category={category} />
                ))}
              </Row>
            </Container>
          )}
        </Row>
      </Container>
      <h4>Sản phẩm</h4>
      {loading ? (
        <h2>Loading...</h2>
      ) : error ? (
        <h3>{error}</h3>
      ) : (
        <Container className={style.productContainer}>
          <Row>
            {products.map((product) => (
              <Product className={style.product} product={product} />
            ))}
          </Row>
        </Container>
      )}
    </>
  );
};

export default HomeScreen;
