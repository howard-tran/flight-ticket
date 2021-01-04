import { stat } from "fs";
import { ActionType } from "../constants/ActionType";

export const ACCOUNT_TYPE_MANAGER = "MANAGER";
export const ACCOUNT_TYPE_STAFF = "STAFF";
export const ACCOUNT_TYPE_CUSTOMER = "CUSTOMER";

export const ACCOUNT_STATUS_ACTIVE ="ACTIVE";
export const ACCOUNT_STATUS_DISABLED= "DISABLED";

export interface AccountInfo {
  username: string;
  password: string;
  email: string;
  phoneNumber: string;
  agencyId: string;
  type: string;
  status: string;
}

const initAccount : AccountInfo = {
  username: "", password: "", email: "",
  phoneNumber: "", agencyId: "", type: "", status: ""
}

export let AccountInfoReducer: React.Reducer<AccountInfo, ActionType<any>> = (
  state = initAccount, action
) => {
  switch (action.type) {
    case "username": return {...state, username: action.value};
    case "password": return {...state, password: action.value};
    case "email": return {...state, email: action.value};
    case "phoneNumber": return {...state, phoneNumber: action.value};
    case "agencyId": return {...state, agencyId: action.value};
    case "type": return {...state, type: action.value};
    case "status": return {...state, status: action.value};
    default: return state;
  }
}