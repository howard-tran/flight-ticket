import Axios from "axios";
import { AccountInfo } from "./Utils";

export interface IUser {
  _id: String;
  username: String;
  tel: String;
  avatar: String;
}

export class FakeUserApi {
  static randomImg = () => {
    let imglist: string[] = [];
    imglist.push(
      `https://footwearnews.com/wp-content/uploads/2017/11/victorias-secret-fashion-show-redhead.jpg`,
      `https://media.glamour.com/photos/5a0deeca081e4d296f18fb5a/master/pass/gigi-hadid-victorias-secret-fashion-show-missing.jpg`,
      `https://i.pinimg.com/originals/15/49/97/154997c4ea88f7baa3f95d9c607d63b7.jpg`,
      `https://upload.wikimedia.org/wikipedia/commons/3/3a/Bella_Hadid_Cannes_2018_2.jpg`
    );
    let index = Math.floor(Math.random() * imglist.length);
    return imglist[index];
  };

  static requestUser = (id: string) => {
    let userList: AccountInfo[] = [];
    userList.push(
      {
        id: "5fbc0b21d32481017a769f16",
        avatar: FakeUserApi.randomImg(),
        user: "fuck",
        exp: 0,
        iat: 0,
        iss: "",
      },
      {
        id: "5fbd3a3970cb4d0161112b1d",
        avatar: FakeUserApi.randomImg(),
        user: "mingkhoi",
        exp: 0,
        iat: 0,
        iss: "",
      },
      {
        id: "5fbd43bb76af190165ad3f21",
        avatar: FakeUserApi.randomImg(),
        user: "MrSnoop",
        exp: 0,
        iat: 0,
        iss: "",
      },
      {
        id: "5fbf6df930e7f6015fbdb1a1",
        avatar: FakeUserApi.randomImg(),
        user: "ert",
        exp: 0,
        iat: 0,
        iss: ""
      }
    );
    return userList.find(x => x.id === id);
  };
}
