import { ChatActionType, SET_ACCOUNT_INFO, ADD_CONVERSATION } from "../actions/chatBoxAction";
import { AccountInfo } from "../components/Utils";

export interface Conversation {
  id: string;
  senderId: string,
  receiverId: string
}

export interface ConversationControl {
  conversationList: Conversation[];
  requestIndex: number;
}

const initConversationControl: ConversationControl = {
  conversationList: [],
  requestIndex: 0
}
const initAccountInfo: AccountInfo = {
  id: "", user: "", exp: 0, iat: 0, iss: "localhost", avatar: ""
}

export const accountInfoReducer: React.Reducer<AccountInfo, ChatActionType<any>> = (
  state = initAccountInfo, action) => {

  switch (action.type) {
    case SET_ACCOUNT_INFO: {
      return {
        ...action.value
      }
    }
    default: return state;
  }
}

export const conversationControlReducer: React.Reducer<ConversationControl, ChatActionType<any>> = (
  state = initConversationControl, action) => {

  switch (action.type) {
    case ADD_CONVERSATION: {
      let list = action.value as Conversation[];      
      
      state.conversationList.push(...list);
      return {
        conversationList: state.conversationList,
        requestIndex: state.requestIndex + list.length
      };
    }
    default: return state;
  }
}

export default null;