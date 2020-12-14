import Axios, { AxiosRequestConfig } from "axios";
import { useDispatch } from "react-redux";
import { CHANGE_AVATAR, CHANGE_AVATAR_FAIL, CHANGE_AVATAR_SUCCESS, GET_PROFILE, GET_PROFILE_FAIL, GET_PROFILE_SUCCESS, REMOVE_PROFILE, SAVE_CHANGE_AVATAR } from "../constants/profileConstants";
import { ActionType } from "../types/actionType";
import { Profile } from "../types/profile";
import { ReponseAPI } from "../types/ReponseAPI";
import FormData from 'form-data'
import fs from 'fs'



export const GetProfile = () =>async (dispatch: React.Dispatch<ActionType<Profile>>) => {
    dispatch(setStateGetProfile())
    Axios.defaults.headers.common['Authentication'] = 'Bearer ' + sessionStorage.getItem("token")// for all requests
    let response = await Axios.get<ReponseAPI<Profile>>(
        `/go/profile/`
    );
    console.log(response);
    if (response.status === 200) {
        if (response.data.status === 200) {
            dispatch(setProfile(response.data.data));
        }
        else {
            dispatch(setStateErrorProfile(response.data.error))
        }
    } else {
        dispatch(setStateErrorProfile(response.statusText))
    }
}

const setProfile = (profile: Profile): ActionType<Profile> => {
    return {
        type: GET_PROFILE_SUCCESS,
        payload: profile
    }
}

const setStateGetProfile = (): ActionType<any> => {
    return {
        type: GET_PROFILE,
        payload: null
    }
}

const setStateErrorProfile = (profile: any): ActionType<any> => {
    return {
        type: GET_PROFILE_FAIL,
        payload: profile,
    }
}


export const ProfileRemove = () => {
    const dispatch = useDispatch();
    dispatch({
        type: REMOVE_PROFILE,
        payload: {},
    });
}



export const ChangeAvatar = (file: File) => async (dispatch: React.Dispatch<ActionType<Profile>>) => {
    dispatch(ChangeAvatar_Request());
    var data = new FormData();
    // console.log(path);
    // fs.createReadStream(path);
    data.append('files', file);

    var config: AxiosRequestConfig = {
        method: 'post',
        url: '/cdn/upload',
        // headers: {
        //     ...data.getHeaders()
        // },
        data: data
    };

    Axios(config)
        .then(
            res1 => {
                //console.log(res.data[file.name]);

                console.log("teststeste");
                var data = JSON.stringify({ "avatar": res1.data[file.name] });
                Axios.post<ReponseAPI<string>>(
                    '/go/profile/',
                    data
                ).then(
                    res => {
                        if (res.data.status === 200) {
                            dispatch(ChangeAvatar_Success(res1.data[file.name]))
                            return true;
                        } else {
                            dispatch(ChangeAvatar_Fail(res1.data[file.name]))
                            return false;
                        }
                    }
                ).catch(
                    res => {
                        console.log(res);
                        dispatch(ChangeAvatar_Fail(res.data))
                    }
                )
            }
        )
        .catch(
            res => {
                console.log(res);
                dispatch(ChangeAvatar_Fail(res.data))
            }
        );

}

const ChangeAvatar_Request = (): ActionType<any> => {
    return {
        type: CHANGE_AVATAR,
        payload: null,
    }
}


const ChangeAvatar_Success = (link: string): ActionType<any> => {
    return {
        type: CHANGE_AVATAR_SUCCESS,
        payload: link,
    }
}


const ChangeAvatar_Fail = (err: string): ActionType<any> => {
    return {
        type: CHANGE_AVATAR_FAIL,
        payload: err,
    }
}

export const SaveChangeAvatarBackend = (avatar: string) => (dispatch: React.Dispatch<ActionType<Profile>>) => {
    dispatch(SaveChangeAvatar())
    console.log("teststeste");
    var data = JSON.stringify({ "avatar": avatar });
    Axios.post<ReponseAPI<string>>(
        '/go/profile',
        data
    ).then(
        res => {
            if (res.data.status === 200) {
                dispatch(SaveChangeAvatarSuccess());
                return true;
            } else {
                dispatch(SaveChangeAvatarFail());
                return false;
            }
        }
    ).catch(
        res => {
            console.log(res);
            dispatch(SaveChangeAvatarFail())
        }
    )
}

const SaveChangeAvatar = (): ActionType<any> => {
    return {
        type: SAVE_CHANGE_AVATAR,
        payload: null
    }
}

const SaveChangeAvatarSuccess = (): ActionType<any> => {
    return {
        type: SAVE_CHANGE_AVATAR,
        payload: null
    }
}
const SaveChangeAvatarFail = (): ActionType<any> => {
    return {
        type: SAVE_CHANGE_AVATAR,
        payload: null
    }
}