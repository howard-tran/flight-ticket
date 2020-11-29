import { count } from "console";
import React, { Component, ReactElement, useCallback, useEffect, useLayoutEffect, useMemo, useRef, useState } from "react";
import ReactDOM from "react-dom";
import { useDispatch } from "react-redux";
import { JsxAttribute, NumericLiteral } from "typescript";
import style from "../styles/ChatBox.module.scss";
import ChatConversation from "./ChatConversation";
import ChatMessage from "./ChatMessage";
import { AccountInfo } from "./Utils";

const ChatBox: React.FC = () => {
  const [showConversation, setShowConversation] = useState(true);
  const [isOpen, setIsOpen] = useState(false);

  let intervalId = useRef<NodeJS.Timeout>();
  let chatPannel = useRef();

  useLayoutEffect(() => {
    intervalId.current = setInterval(() =>   {
      if (sessionStorage.getItem("token") != null) {
        clearInterval(intervalId.current);
      }
    }, 500);
  }, [])

  if (sessionStorage.getItem("token") == null) {
    return <div /> 
  }
  return (
    <div>
      <div className={style.mainPane} ref={chatPannel}>
        <ChatConversation> 
          
        </ChatConversation>
      </div>

      {isOpen === false &&
        <div className={style.chatBox} onClick={() => {
          setIsOpen(!isOpen);
          (chatPannel.current as HTMLDivElement).style.display = "block";
        }}>
          <i style={{ color: '#ffffff' }} className="fas fa-comments fa-3x"></i>
        </div>
      }
      {isOpen === true &&
        <div>
          <div className={style.chatBox} onClick={() => {
            setIsOpen(!isOpen);
            (chatPannel.current as HTMLDivElement).style.display = "none";
          }}>
            <i style={{ color: '#ffffff' }} className="fas fa-close fa-3x"></i>
          </div>
        </div>
      }
    </div>
  )
}

export default ChatBox;