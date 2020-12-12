import Axios from "axios";
import { Interface } from "readline"
import { ThunkAction } from "redux-thunk";
import { AccountInfo } from "../components/Utils";
import { Conversation } from "../reducers/chatBoxReducer";

export const SET_ACCOUNT_INFO = "SET_ACCOUNT_INFO";
export const GET_CONVERSATION = "GET_CONVERSATION";
export const GET_MESSAGE = "GET_MESSAGE";
export const ADD_CONVERSATION = "ADD_CONVERSATION";
export const ADD_MESSAGE = "ADD_MESSAGE";

export interface ChatActionType<T> {
  type: string;
  value: T;
}

export const getAccountInfoThunk: ThunkAction<void, AccountInfo, string, ChatActionType<AccountInfo>> 
  = async (dispatch, getState, token) => {
  
  let response = await Axios.get(`/java/api/account/jwt?token=${token}`);
  dispatch(setAccountInfo(response.data.data));
} 

export const setAccountInfo = (account: AccountInfo) : ChatActionType<AccountInfo> => {
  return {
    type: SET_ACCOUNT_INFO,
    value: account
  };
}

export const getConversation = (senderId: string, index: number) : ChatActionType<Conversation[]> => {
  let result: Conversation[] = [];

  Axios.get(`/java/api/conversation/get?senderid=${senderId}&index=${index}`)
    .then(res => {
      result = res.data.data;
    });

  return {
    type: GET_CONVERSATION,
    value: result
  }
}