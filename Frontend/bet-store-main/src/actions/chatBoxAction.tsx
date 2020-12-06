import Axios from "axios";
import { promises } from "dns";
import { Interface } from "readline";
import { ThunkAction } from "redux-thunk";
import { AccountInfo } from "../components/Utils";
import { Conversation, ConversationControl } from "../reducers/chatBoxReducer";

export const SET_ACCOUNT_INFO = "SET_ACCOUNT_INFO";
export const ADD_CONVERSATION = "ADD_CONVERSATION";
export const GET_MESSAGE = "GET_MESSAGE";
export const ADD_MESSAGE = "ADD_MESSAGE";

export interface ChatActionType<T> {
  type: string;
  value: T; 
}

export const getAccountInfoThunk: ThunkAction<
  void,
  AccountInfo,
  string,
  ChatActionType<AccountInfo>
> = async (dispatch, getState, token) => {
  //
  let response = await Axios.get(`/java/api/account/jwt?token=${token}`);
  dispatch(setAccountInfo(response.data.data));
};

export const setAccountInfo = (account: AccountInfo): ChatActionType<AccountInfo> => {
  return {
    type: SET_ACCOUNT_INFO,
    value: account,
  };
};

export const getConversationThunk: ThunkAction<
  void,
  ConversationControl,
  { senderId: string; index: number },
  ChatActionType<Conversation[]>
> = async (dispatch, getState, param) => {
  let result: Conversation[] = [];

  let response = await Axios.get(`/java/api/conversation/get?senderid=${param.senderId}&index=${param.index}`);
  result = response.data.data;

  dispatch(addConversation(result));
};

export const addConversation = (
  conversationList: Conversation[]
): ChatActionType<Conversation[]> => {

  return {
    type: ADD_CONVERSATION,
    value: conversationList,
  };
};
