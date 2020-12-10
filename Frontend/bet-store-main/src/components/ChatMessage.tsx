import React, { ReactEventHandler, useCallback, useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { switchToConversation } from "../actions/chatBoxAction";
import { ChatViewControl } from "../reducers/chatBoxReducer";
import style from "../styles/ChatBox.module.scss";
import { ChatApiUtils } from "./ChatApiUtils";
import { AccountInfo, getTextWidth, toDomNode } from "./Utils";

const TEXT_EDITOR_MAX_ROW = 5;
const INPUT_TEXT_HANDLER_DELAY = 5;

const ChatMessage = React.memo(() => {
  const accountState = useSelector((state: { accountInfo: AccountInfo }) => state.accountInfo);
  const view = useSelector((state: { viewControl: ChatViewControl }) => state.viewControl);

  const [userInfo, setUserInfo] = useState<AccountInfo>(null);
  const dispatch = useDispatch();

  let inputArea = useRef<HTMLElement>();
  const setInputArea = useCallback((node) => {
    inputArea.current = node;

    // casting
    let node_t = inputArea.current as HTMLTextAreaElement;

    // change row as typing
    node_t.onkeydown = (e: KeyboardEvent) => {
      if (e.key == "Enter") {
        e.preventDefault();
      }
      if (e.shiftKey && e.key == "Enter") {
        node_t.textContent += "\n";
        // event handler go after keydown
        window.setTimeout(() => {
          if (node_t.rows < TEXT_EDITOR_MAX_ROW) {
            node_t.rows++;
          }
        }, INPUT_TEXT_HANDLER_DELAY);
      }

      node_t.oninput = (e: Event) => {
        window.setTimeout(() => {
          let lineCount = (() => {
            let lines: String[] = node_t.value.split(/\r*\n/);

            let res = lines.length;
            for (let i = 0; i < lines.length; i++) {
              let t = lines[i].length / node_t.cols;

              if (t >= 1) {
                res += Math.trunc(t);

                if (lines[i].length % node_t.cols == 0) {
                  res -= 1;
                }
              }
            }
            return res;
          })();

          if (lineCount <= TEXT_EDITOR_MAX_ROW) {
            node_t.rows = lineCount;
          }
        }, INPUT_TEXT_HANDLER_DELAY);
      };
    };
  }, []);

  let toolBar = useRef<HTMLElement>();
  let setToolBar = useCallback((node) => {
    toolBar.current = node;
  }, []);

  useEffect(() => {
    setUserInfo(ChatApiUtils.requestUser(view.currentReceiver));
  }, [view]);

  if (userInfo == null) {
    return <div></div>;
  }
  return (
    <div style={{ width: "100%", height: "100%" }}>
      <div className={style.textLenghtMeasure}></div>
      <div className={style.messageHeader}>
        <div>
          <i
            className="fa fa-arrow-left fa-2x"
            onClick={() => {
              dispatch(switchToConversation());
            }}
          ></i>
          <img src={userInfo.avatar}></img>
          <div>
            <h3>{userInfo.user}</h3>
            <br></br>
            <p>Đang hoạt động</p>
          </div>
        </div>
      </div>

      <div className={style.messageMainPanel}>
        <div className={style.messageBody}></div>

        <div className={style.messageToolBar}>
          <div className={style.toolBar} ref={setToolBar}>
            <div className={style.tool}>
              <i className="fa fa-image fa-2x"></i>
            </div>
            <div className={style.tool}>
              <div style={{borderRadius:"5px", border:"2px solid black", padding: "0 5px 0 5px"}}>
                <p style={{fontWeight:"bold", fontSize:"smaller", transform: "translateY(7px)"}}>GIF</p>
              </div>
            </div>
          </div>
          <div className={style.textEditor}>
            <i className="fa fa-plus-circle fa-2x" onClick={() => {
              if (toolBar.current == null) return;

              if (toolBar.current.style.display == "none") {
                toolBar.current.style.display = "flex";
              } else {
                toolBar.current.style.display = "none";
              }
            }}></i>
            <div className={style.inputArea}>
              <textarea
                rows={1}
                cols={29}
                ref={setInputArea}
                placeholder="Aa"
                style={{ whiteSpace: "pre-wrap" }}
              ></textarea>
              <i className="fa fa-smile-o fa-2x"></i>
            </div>
            <i className="fa fa-send fa-2x"></i>
          </div>
        </div>
      </div>
    </div>
  );
});

export default ChatMessage;
