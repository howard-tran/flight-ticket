import Axios from "axios";
import { promises } from "dns";
import { sep } from "path";
import { Interface } from "readline";
import { ThunkAction } from "redux-thunk";
import SocketManager, { ISocket } from "../components/SocketManager";
import { AccountInfo } from "../components/Utils";
import { Conversation, ConversationControl, CONVERSATION_VIEW, socketInfoReducer } from "../reducers/chatBoxReducer";

export const OPEN_CHAT_BOX = "OPEN_CHAT_BOX";
export const CLOSE_CHAT_BOX = "CLOSE_CHAT_BOX";
export const SET_ACCOUNT_INFO = "SET_ACCOUNT_INFO";
export const LOAD_CONVERSATION = "LOAD_CONVERSATION";
export const GET_MESSAGE = "GET_MESSAGE";
export const ADD_MESSAGE = "ADD_MESSAGE";
export const SWITCH_TO_CONVERSATION = "SWITCH_TO_CONVERSATION";
export const SWITCH_TO_MESSAGE = "SWITCH_TO_MESSAGE";
export const REPLACE_CURRENT_RECEIVER = "REPLACE_CURRENT_RECEIVER";
export const SET_SOCKET_INFO = "SET_SOCKET_INFO";

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

  dispatch(loadConversation(result));
};

export const loadConversation = (
  conversationList: Conversation[]
): ChatActionType<Conversation[]> => {

  return {
    type: LOAD_CONVERSATION,
    value: conversationList,
  };
};

export const openChatBox = (flag: boolean) : ChatActionType<boolean> => {
  if (flag == true) {
    return {
      type: OPEN_CHAT_BOX,
      value: null
    }
  } else {
    return {
      type: CLOSE_CHAT_BOX,
      value: null
    }
  }
}

export const switchToConversation = () : ChatActionType<any> => {
  return {
    type: SWITCH_TO_CONVERSATION,
    value: null
  }
}

export const switchToMessage = () : ChatActionType<any> => {
  return {
    type: SWITCH_TO_MESSAGE,
    value: null
  }
}

export const repalceCurrentReceiver = (accountId: string) : ChatActionType<any> => {
  return {
    type: REPLACE_CURRENT_RECEIVER,
    value: accountId
  }
}

export const setSocketInfo = (socketInfo: ISocket) : ChatActionType<ISocket> => {
  SocketManager.addSocket(socketInfo.key, socketInfo);
  SocketManager.connect(socketInfo.key);
  return {
    type: SET_SOCKET_INFO,
    value: socketInfo
  }
}