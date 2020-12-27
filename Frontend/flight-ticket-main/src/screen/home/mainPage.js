import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Row, Col, Card, CardGroup, Container } from "react-bootstrap";
import Product from "../../components/Product";
import Category from "../../components/Category";
import { listProducts } from "../../actions/productActions";
import style from "../../styles/ProductDisplay.module.scss";
import { listCategories } from "../../actions/categoryActions";
import Carousel from "react-grid-carousel";
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
      <div className="container">
        <div className={style.categoryContainer}>
          <div className={style.category_header}>
            <h4 className={style.title}>Danh mục</h4>
          </div>

          {loadingCategories ? (
            <h2>Loading...</h2>
          ) : errorCategories ? (
            <h3>{errorCategories}</h3>
          ) : (
            <Carousel
              cols={5}
              rows={1}
              gap={10}
              responsiveLayout={[
                {
                  breakpoint: 1200,
                  cols: 3,
                },
                {
                  breakpoint: 990,
                  cols: 2,
                },
                {
                  breakpoint: 500,
                  cols: 1,
                },
              ]}
              loop
            >
              {categories.map((category) => (
                <Carousel.Item>
                  <Category category={category} />
                </Carousel.Item>
              ))}
            </Carousel>
          )}
        </div>
        <div className={`${style.productContainer} container`}>
          <h4 className={style.title}>Sản phẩm</h4>

          {loading ? (
            <h2>Loading...</h2>
          ) : error ? (
            <h3>{error}</h3>
          ) : (
            <Row>
              {products.map((product) => (
                <Product product={product} />
              ))}
            </Row>
          )}
        </div>
      </div>
    </>
  );
};

export default HomeScreen;
