import {
  ChatActionType,
  SET_ACCOUNT_INFO,
  LOAD_CONVERSATION,
  SWITCH_TO_CONVERSATION,
  SWITCH_TO_MESSAGE,
  OPEN_CHAT_BOX,
  CLOSE_CHAT_BOX,
  REPLACE_CURRENT_RECEIVER,
  SET_SOCKET_INFO,
} from "../actions/chatBoxAction";
import { ISocket } from "../components/SocketManager";
import { AccountInfo } from "../components/Utils";

// view
export const EMPTY_VIEW = -1;
export const CONVERSATION_VIEW = 1;
export const MESSAGE_VIEW = 2;
export const WELCOME_VIEW = 3;

// socket controller
export const CHAT_HANDLER = "/chat/handle";

// ====================================

export interface ChatViewControl {
  viewId: number;
  isOpen: boolean;
  currentReceiver: string,
}

export interface Conversation {
  id: string;
  senderId: string;
  receiverId: string;
}

export interface ConversationControl {
  conversationList: Conversation[];
  requestIndex: number;
}

const initConversationControl: ConversationControl = {
  conversationList: [],
  requestIndex: 0,
};
const initAccountInfo: AccountInfo = {
  id: "",
  user: "",
  exp: 0,
  iat: 0,
  iss: "localhost",
  avatar: "",
};

export const accountInfoReducer: React.Reducer<AccountInfo, ChatActionType<any>> = (
  state = initAccountInfo,
  action
) => {
  switch (action.type) {
    case SET_ACCOUNT_INFO: {
      return {
        ...action.value,
      };
    }
    default:
      return state;
  }
};

export const conversationControlReducer: React.Reducer<ConversationControl, ChatActionType<any>> = (
  state = initConversationControl,
  action
) => {
  switch (action.type) {
    case LOAD_CONVERSATION: {
      let list = action.value as Conversation[];

      state.conversationList.push(...list);
      return {
        conversationList: state.conversationList,
        requestIndex: state.requestIndex + list.length,
      };
    }
    default:
      return state;
  }
};

export const viewControlReducer: React.Reducer<ChatViewControl, ChatActionType<any>> = (
  state = { viewId: EMPTY_VIEW, isOpen: false, currentReceiver: "" },
  action
) => {
  switch (action.type) {
    case SWITCH_TO_CONVERSATION:
      return {...state, viewId: CONVERSATION_VIEW};
    case SWITCH_TO_MESSAGE:
      return {...state, viewId: MESSAGE_VIEW };
    case OPEN_CHAT_BOX:
      return {...state, isOpen: true};
    case CLOSE_CHAT_BOX:
      return {...state, isOpen: false}
    case REPLACE_CURRENT_RECEIVER: 
      return {...state, currentReceiver: action.value};
    default:
      return state;
  }
};

export const socketInfoReducer: React.Reducer<ISocket, ChatActionType<any>> = (
  state = {key:"", socketUrl:"", brockers: null}, action
) => {
  switch (action.type) {
    case SET_SOCKET_INFO:
      return {
        key: action.value.key,
        socketUrl: action.value.socketUrl,
        brockers: action.value.brockers
      };
    default:
      return state;
  }
}


export default null;