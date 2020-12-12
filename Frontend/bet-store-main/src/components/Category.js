import React from "react";
import { Link } from "react-router-dom";
import { Card, Container, Image } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
import style from "../styles/Category.module.scss";
const Category = ({ category }) => {
  return (
    <Container className={style.category} fluid>
      <Link to={`/category/${category._id}`}>
        {/* <Card.Img src={`/cdn/cdn/${product.image[0].link}`} variant="top" /> */}
        <Image
          className={style.image}
          src="http://dummyimage.com/168x168.bmp/ff4444/ffffff"
          roundedCircle
        />
      </Link>

      <Link to={`/category/${category._id}`}>{category.name}</Link>
    </Container>
  );
};

export default Category;
