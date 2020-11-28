import { CompatClient, IFrame, IMessage, Stomp } from "@stomp/stompjs";
import SockJS from "sockjs-client";

export interface ISocket {
  socketUrl: string;
  brockers: IBrocker[];
}

export interface IBrocker {
  brocker: string;
  receiveHandler: { (message: IMessage): void };
}

class SocketManager {
  private static stompClient: Map<string, CompatClient> = new Map();

  public static addSocket(key: string, socketInfo: ISocket) {
    if (SocketManager.stompClient.get(key) != null) {
      return false;
    }
    let socket = new SockJS(socketInfo.socketUrl);
    let client = Stomp.over(socket);

    client.onConnect = (frame: IFrame) => {
      for (let i = 0; i < socketInfo.brockers.length; i++) {
        client.subscribe(socketInfo.brockers[i].brocker, socketInfo.brockers[i].receiveHandler);
      }
    };
    SocketManager.stompClient.set(key, client);
    return true;
  }

  public static connect(key: string) {
    SocketManager.stompClient.get(key)?.activate();
  }

  public static publish(key: string, data: { destination: string; headers: any; content: string }) {
    SocketManager.stompClient.get(key)?.publish({
      destination: data.destination,
      headers: data.headers,
      body: data.content,
    });
  }
}

export default SocketManager;
