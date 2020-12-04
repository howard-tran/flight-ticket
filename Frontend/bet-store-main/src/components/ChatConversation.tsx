import React, { useCallback, useEffect, useMemo, useRef, useState } from "react";
import reactElementToJSXString from "react-element-to-jsx-string";
import { useDispatch, useSelector } from "react-redux";
import { getAccountInfoThunk } from "../actions/chatBoxAction";
import { Conversation } from "../reducers/chatBoxReducer";
import { AccountInfo, toDomNode } from "./Utils";
import style from "../styles/ChatBox.module.scss";

const ChatConversation: React.FC = () => {
  const accountInfo = useSelector((state: { accountInfo: AccountInfo }) => state.accountInfo);
  const dispatch = useDispatch();

  useEffect(() => {
    console.log(sessionStorage.getItem("token"))
    getAccountInfoThunk(dispatch, () => null, sessionStorage.getItem("token"));
  }, []);

  return (
    <div className={style.conversationMain}>
      <div className={style.conversationHeader}>
        <h1>Message</h1>
        <div className={style.searchBar}>
          <i className="fas fa-search fa-2x"></i>
          <input type="text" placeholder="Search" spellCheck="false"></input>
        </div>
      </div>
      <div className={style.conversationBody}>
        
      </div>
    </div>
  );
}

export const ChatConversationBox: React.FC<Conversation> = (conversation) => {
  return (
    <div></div>
  )
}

export default ChatConversation;