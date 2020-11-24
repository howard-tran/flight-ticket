import React, { useState, useEffect } from "react";

import { Row, Col, Form, Button, FormControl } from "react-bootstrap";

import axios from "axios";

const nodeServer = process.env.REACT_APP_NODE_SERVER;
const cdnServer = process.env.REACT_APP_CDN_SERVER;

const ProductScreen = () => {
  const [categories, setCategories] = useState([]);
  const [categoryProperties, setPropertiesLabel] = useState([]);
  const [selectedFile, setFile] = useState(null);
  const [properties, setProperties] = useState([]);
  const [category, setCategory] = useState("");
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState(0);
  const [countInStock, setCountInStock] = useState(0);

  useEffect(() => {
    const fetchCategories = async () => {
      const { data } = await axios.get(`${nodeServer}/api/categories/`);
      setCategories(data);
    };
    fetchCategories();
  }, []);

  useEffect(() => {
    categoryProperties.map((catProp) => {
      properties.push({
        key: catProp.key,
        value: null,
      });
    });
  }, [categoryProperties]);

  const fetchProperties = async (catId) => {
    const { data } = await axios.get(`${nodeServer}/api/categories/${catId}`);
    setPropertiesLabel(data.properties);
    //console.log(data.properties);
  };

  const onCategoryChange = async (event) => {
    setCategory(event.target.value);
    fetchProperties(event.target.value);
  };

  const onFileChange = (event) => {
    setFile(event.target.files);
  };

  const onPropertyChange = (event) => {
    properties.find((prop) => prop.key === event.target.name).value =
      event.target.value;
    console.log(properties);
  };

  const onSubmit = async (event) => {
    event.preventDefault();

    const uploadFiledata = new FormData();
    for (var x = 0; x < selectedFile.length; x++) {
      uploadFiledata.append("files", selectedFile[x]);
    }
    const product = {
      name: name,
      description: description,
      price: price,
      countInStock: countInStock,
      image: [],
      category: category,
      user: "5fa7fb0a62083e11ace57490",
      properties: properties,
    };
    await axios.post(`${cdnServer}/upload`, uploadFiledata, {}).then((res) => {
      Object.values(res.data).map((filename) => {
        product.image.push({
          link: filename,
          alt: null,
        });
      });
    });

    console.log(product);
    axios
      .post(`${nodeServer}/api/products`, product, {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      })
      .then((res) => console.log(res.data));
  };

  return (
    <>
      <Form>
        <Row>
          <Col>Tên sản phẩm</Col>
          <Col>
            <Form.Control
              type="text"
              name="name"
              onChange={(e) => setName(e.target.value)}
            ></Form.Control>
          </Col>
        </Row>
        <Row>
          <Col>Mô tả sản phẩm sản phẩm</Col>
          <Col>
            <Form.Control
              as="textarea"
              name="description"
              onChange={(e) => setDescription(e.target.value)}
            ></Form.Control>
          </Col>
        </Row>
        <Row>
          <Col>Giá</Col>
          <Col>
            <Form.Control
              type="text"
              name="price"
              onChange={(e) => setPrice(e.target.value)}
            ></Form.Control>
          </Col>
        </Row>
        <Row>
          <Col>Số lượng</Col>
          <Col>
            <Form.Control
              type="text"
              name="countInStock"
              onChange={(e) => setCountInStock(e.target.value)}
            ></Form.Control>
          </Col>
        </Row>
        <Row>
          <Col>Loại sản phẩm</Col>
          <Col>
            <Form.Control
              as="select"
              defaultValue="2"
              name="category"
              onChange={(e) => onCategoryChange(e)}
            >
              <option>Default select</option>
              {categories.map((x) => (
                <option key={x._id} value={x._id}>
                  {x.name}
                </option>
              ))}
            </Form.Control>
          </Col>
        </Row>

        {categoryProperties.map((prop) => (
          <Row>
            <Col>
              <Form.Label>{prop.name}</Form.Label>
            </Col>
            <Col>
              <Form.Control
                type="text"
                name={prop.key}
                placeholder={prop.name}
                onChange={(e) => onPropertyChange(e)}
              ></Form.Control>
            </Col>
          </Row>
        ))}
        <Form.File
          className="position-relative"
          required
          name="files"
          label="File"
          onChange={onFileChange}
          feedbackTooltip
          multiple
        />
      </Form>
      <Button variant="primary" type="submit" onClick={onSubmit}>
        Submit
      </Button>
    </>
  );
};

export default ProductScreen;
