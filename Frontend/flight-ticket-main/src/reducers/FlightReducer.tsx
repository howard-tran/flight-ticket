import { ActionType } from "../constants/ActionType";

export interface ArrayData<T> {
  array: T;
  dtbSize: number;
}

export interface FlightInfo {
  id: string;
  airplaneId: string;
  supplierId: string;
  isExpired: number;
  carryOn: number;
  checkInBaggage: number;
  airlineStart: string;
  airlineEnd: string;
  price: number;
  flightDate: number;
}

export interface FlightSearch {
  index: number;
  supplierId: string;
  airlineStart: string;
  airlineEnd: string;
  seatClass: string;
  dateFlight: number[]; // size == 2
  adult: number;
  minor: number;
  baby: number;
}

const initFlightSearch: FlightSearch = {
  index: 0, supplierId: "", airlineStart: "", airlineEnd: "",
  seatClass: "", dateFlight: [0, 0], adult: 0, minor: 0, baby: 0
}

export let FlightSearchReducer: React.Reducer<FlightSearch, ActionType<any>> = (
  state = initFlightSearch, action
) => {
  switch (action.type) {
    case "search": return {...action.value};
    default: return state;
  }
}