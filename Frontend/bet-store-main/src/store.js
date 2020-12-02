import { createStore, combineReducers, applyMiddleware } from "redux";
import thunk from "redux-thunk";
import { composeWithDevTools } from "redux-devtools-extension";
import {
  productListReducer,
  productDetailsReducer,
} from "./reducers/productReducer";
import {
  categoryListReducer,
  categoryDetailsReducer,
} from "./reducers/categoryReducer";

const reducer = combineReducers({
  productList: productListReducer,
  productDetails: productDetailsReducer,
  categoryList: categoryListReducer,
  categoryDetails: categoryDetailsReducer,
});

const initialState = {};

const middleWare = [thunk];

const store = createStore(
  reducer,
  initialState,
  composeWithDevTools(applyMiddleware(...middleWare))
);

export default store;
