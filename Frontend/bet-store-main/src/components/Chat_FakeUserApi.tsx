import Axios from "axios";

export interface IUser {
  _id: String;
  username: String;
  tel: String;
  avatar: String;
}

export function requestUserInfo(id: String) {
  let userList : IUser[] = [];
  userList.push(
    {
      _id: "5fbc0b21d32481017a769f16",
      username: "fuck",
      tel: "0938147189",
      avatar: `https://footwearnews.com/wp-content/uploads/2017/11/victorias-secret-fashion-show-redhead.jpg`
    },
    {
      _id: "5fbd3a3970cb4d0161112b1d",
      username: "mingkhoi",
      tel: "0938147189",
      avatar: `https://footwearnews.com/wp-content/uploads/2017/11/victorias-secret-fashion-show-redhead.jpg`
    },
    {
      _id: "5fbd43bb76af190165ad3f21",
      username: "MrSnoop",
      tel: "0947156720",
      avatar: `https://footwearnews.com/wp-content/uploads/2017/11/victorias-secret-fashion-show-redhead.jpg`
    },
    {
      _id: "5fbf6df930e7f6015fbdb1a1",
      username: "ert",
      tel: "0938147189",
      avatar: `https://footwearnews.com/wp-content/uploads/2017/11/victorias-secret-fashion-show-redhead.jpg`
    }
  );
  for (let i = 0; i < userList.length; i++) {
    if (userList[i]._id == id) {
      return userList[i];
    }
  }
  return null;
};
