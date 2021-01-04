import { applyMiddleware, combineReducers, createStore } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import thunk from "redux-thunk";
import { AccountInfoReducer } from "./reducers/AccountReducer";
import { FlightInfoReducer, FlightSearchReducer } from "./reducers/FlightReducer";

const reducer = combineReducers({
  accountInfo: AccountInfoReducer,
  flightSearch: FlightSearchReducer,
  flightInfo: FlightInfoReducer
});
export type AppState = ReturnType<typeof reducer>;

const initialState = {};

const middleWare = [thunk];

const Store = createStore(
  reducer,
  initialState,
  composeWithDevTools(applyMiddleware(...middleWare))
);

export default Store;
