import { IMessage } from "@stomp/stompjs";
import { count } from "console";
import React, {
  useCallback,
  useEffect,
  useRef,
  useState,
} from "react";
import { useDispatch, useSelector } from "react-redux";
import { openChatBox, setSocketInfo } from "../actions/chatBoxAction";
import { javaSocket } from "../globalConstraint";
import { ChatViewControl, CONVERSATION_VIEW, MESSAGE_VIEW } from "../reducers/chatBoxReducer";
import style from "../styles/ChatBox.module.scss";
import ChatConversation from "./ChatConversation";
import ChatMessage from "./ChatMessage";
import { ISocket } from "./SocketManager";
import { AccountInfo } from "./Utils";

const ChatBox: React.FC = () => {
  const accountState = useSelector((state: { accountInfo: AccountInfo }) => state.accountInfo);
  const socketState = useSelector((state: {socketInfo: ISocket}) => state.socketInfo);
  const view = useSelector((state: { viewControl: ChatViewControl }) => state.viewControl);
  const [intervalId, setIntervalId] = useState<NodeJS.Timeout>();

  const dispatch = useDispatch();

  let pannel = useRef<HTMLElement[]>(new Array(3));

  // 0 = main pannel, include all chat component
  const setMainPannel = useCallback((node) => (pannel.current[0] = node), []);
  const setConversationPannel = useCallback((node) => (pannel.current[1] = node), []);
  const setMessagePannel = useCallback((node) => (pannel.current[2] = node), []);

  const messageReceiveHandler = (payload: IMessage) => {};
  const conversationReceiveHandler = (payload: IMessage) => {};
  const testReceiveHandler = (payload: IMessage) => {
    alert(payload);
  };

  const initSocketConnectionString = (): ISocket => {
    return {
      key: "chat-box",
      socketUrl: `${javaSocket}chat-socket`,
      brockers: [
        {
          brocker: `/chat/message/${accountState.id}`,
          receiveHandler: messageReceiveHandler,
        },
        {
          brocker: `/chat/room/${accountState.id}`,
          receiveHandler: conversationReceiveHandler,
        },
        {
          brocker: `/testChannel`,
          receiveHandler: testReceiveHandler,
        }
      ],
    };
  };

  useEffect(() => {
    setIntervalId(
      setInterval(() => {
        if (sessionStorage.getItem("token") != null) {
          clearInterval(intervalId);
          setInterval(null);
        }
      }, 500)
    );
  }, []);

  useEffect(() => {
    if (accountState.id == "") return;
    dispatch(setSocketInfo(initSocketConnectionString()));
  }, [accountState]);

  useEffect(() => {
    if (view.viewId == CONVERSATION_VIEW) {
      (pannel.current[1] as HTMLDivElement).style.display = "block";
      (pannel.current[2] as HTMLDivElement).style.display = "none";
    } else if (view.viewId == MESSAGE_VIEW) {
      (pannel.current[1] as HTMLDivElement).style.display = "none";
      (pannel.current[2] as HTMLDivElement).style.display = "block";
    }
  }, [view])

  if (sessionStorage.getItem("token") == null) {
    return <div />;
  }
  return (
    <div>
      <div className={style.mainPane} ref={setMainPannel}>
        <div className={style.conversationPannel} ref={setConversationPannel}>
          <ChatConversation></ChatConversation>
        </div>

        <div className={style.messagePannel} ref={setMessagePannel}>
          <ChatMessage senderId={accountState.id} receiverId={view.currentReceiver}></ChatMessage>
        </div>
      </div>
      {view.isOpen === false && (
        <div
          className={style.chatBox}
          onClick={() => {
            dispatch(openChatBox(true));
            (pannel.current[0] as HTMLDivElement).style.display = "block";
          }}
        >
          <i style={{ color: "#ffffff" }} className="fas fa-comments fa-3x"></i>
        </div>
      )}
      {view.isOpen === true && (
        <div>
          <div
            className={style.chatBox}
            onClick={() => {
              dispatch(openChatBox(false));
              (pannel.current[0] as HTMLDivElement).style.display = "none";
            }}
          >
            <i style={{ color: "#ffffff" }} className="fas fa-close fa-3x"></i>
          </div>
        </div>
      )}
    </div>
  );
};

export default ChatBox;
