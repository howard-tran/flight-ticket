import Axios from "axios";

export interface IUser {
  _id: String;
  username: String;
  tel: String;
  avatar: String;
}

export function requestUserInfo(id: String) {
  let userList : IUser[] = [];
  userList.push({
    _id: "mingkhoi",
    username: "mingkhoi",
    tel: "0938147189",
    avatar: `https://www.telegraph.co.uk/content/dam/beauty/2017/10/16/VictoriaSecrets_trans_
      NvBQzQNjv4Bqu6xIqhFH_HXhAThv0oG1A1lBnvQo6z5ZEvL4kLwTmGA.jpg`
  });
  userList.push({
    _id: "MrSnoop",
    username: "MrSnoop",
    tel: "0947156720",
    avatar: `https://footwearnews.com/wp-content/uploads/2017/11/victorias-secret-fashion-show-redhead.jpg`
  })

  for (let i = 0; i < userList.length; i++) {
    if (userList[i]._id == id) {
      return userList[i];
    }
  }
  return null;
};

export function getUserInfo(fuckToken: String) {
  Axios.get('/java/api/account/jwt-id', {params: {token: fuckToken}})
    .then(
      res => {
        let id = res.data.data.id;
        let user: IUser = requestUserInfo(id);
        return user;
      }
    )
}