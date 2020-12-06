import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { switchToConversation } from "../actions/chatBoxAction";
import style from "../styles/ChatBox.module.scss";
import { FakeUserApi } from "./Chat_FakeUserApi";
import { AccountInfo } from "./Utils";

const ChatMessage: React.FC<{senderId: string, receiverId: string}> = ({senderId, receiverId}) => {
  const [userInfo, setUserInfo] = useState<AccountInfo>(null);
  const dispatch = useDispatch();

  useEffect(() => {
    setUserInfo(FakeUserApi.requestUser(receiverId));
  }, []);

  if (userInfo == null) {
    return <div></div>
  }
  return (
    <div>
      <div className={style.messageHeader}>
        <i className="fas fa-arrow-left fa-2x" onClick={() => {
          dispatch(switchToConversation());
        }}></i>
        <img src={userInfo.avatar}></img>
      </div>
      <div className={style.messageBody}>

      </div>
      <div className={style.messageToolBar}>

      </div>
    </div>
  )
}

export default ChatMessage;