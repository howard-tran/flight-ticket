import { count } from "console";
import React, { Component, ReactElement, useCallback, useEffect, useMemo, useRef, useState } from "react";
import ReactDOM from "react-dom";
import { useDispatch } from "react-redux";
import { clearInterval } from "timers";
import { JsxAttribute, NumericLiteral } from "typescript";
import style from "../styles/ChatBox.module.scss";

const ChatBox = () => {
  const [showConversation, setShowConversation] = useState(true);
  const [isOpen, setIsOpen] = useState(false);
  let [intervalId, setIntervalId] = useState<NodeJS.Timeout>();

  useEffect(() => {
    intervalId = setInterval(() =>   {
      if (sessionStorage.getItem("token") != null) {
        clearInterval(intervalId);
      }
    }, 500);

  }, []);

  if (sessionStorage.getItem("token") == null) {
    return <div />
  }
  return (
    <div>
      {sessionStorage.getItem("token") == null && <div></div>}
      {isOpen === false &&
        <div className={style.chatBox} onClick={() => {
          setIsOpen(!isOpen);
        }}>
          <i style={{ color: '#ffffff' }} className="fas fa-comments fa-3x"></i>
        </div>
      }
      {isOpen === true &&
        <div>
          <div className={style.chatBox} onClick={() => {
            setIsOpen(!isOpen);
          }}>
            <i style={{ color: '#ffffff' }} className="fas fa-close fa-3x"></i>
          </div>
          <div className={style.mainPane}></div>
        </div>
      }
    </div>
  )
}

const Conversation = () => {

}

const Message = () => {

}

export default ChatBox;