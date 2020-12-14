import React, { createRef } from "react";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { ChangeAvatar } from "../../actions/profileAction";
import { AppState } from "../../store";
import styles from "../../styles/Profile.module.scss";
import { Profile } from "../../types/profile";
import $ from 'jquery'
import { AddNotify } from "../../actions/notifyAction";

interface SetProfileAction {
    type: string,
    payload: any
}
const USERNAME = "USERNAME";
const SURNAME = "SURNAME";
const NAME = "NAME";
const AVATAR = "AVATAR";
const FULL = "FULL";

const profilereducer: React.Reducer<Profile, SetProfileAction> = (s, a) => {
    switch (a.type) {
        case USERNAME:
            return {
                ...s,
                username: a.payload
            }
        case NAME:
            return {
                ...s,
                name: a.payload
            }
        case AVATAR:
            return {
                ...s,
                avatar: a.payload
            }

        case SURNAME:
            return {
                ...s,
                surname: a.payload
            }
        case FULL:
            return a.payload
    }
    return { ...s }
}

const SetName = (name: string): SetProfileAction => {
    return {
        type: NAME,
        payload: name
    }
}


const SetSurName = (name: string): SetProfileAction => {
    return {
        type: SURNAME,
        payload: name
    }
}

const CHANGE_PASSWORD = "CHANGE_PASSWORD",
    NOT_CHANGE_PASSWORD = "NOT_CHANGE_PASSWORD"

const   WOMAN = "woman",
        MAN = "man",
        OTHER = "other"

export default function InfoProfile() {
    const profile = useSelector((state: AppState) => state.profile)
    var [profileState, setProfileState] = React.useReducer<React.Reducer<Profile, SetProfileAction>>(
        profilereducer,
        profile.Payload
    );
    useEffect(() => {
        if (profile.IsFetching === true ) return;
        setProfileState({
            type: FULL,
            payload: profile.Payload
        })
        setSexuser(profile.Payload.sex);
        if(profile.Payload.sex!=="")
            $(`input[name=SelectSexForm][value=${profile.Payload.sex}]`).attr("checked","true");

    }, [profile])

    const [IsChangePassword, setIsChangePassword] = React.useState<string>(NOT_CHANGE_PASSWORD);
    const [sexuser, setSexuser] = React.useState<string>();
    const dispatch = useDispatch();


    const onChangeFile = (evt: React.ChangeEvent<HTMLInputElement>): any => {
        //alert("Minh");
        //evt.stopPropagation();
        //evt.preventDefault();
        var file = evt.target.files[0];
        dispatch(ChangeAvatar(file));

        //this.setState({file}); /// if you want to upload latter
    }
    const HandleChangeAvatar = () => {
        //console.log(fileSelector);//.click();
        var fileSelector = document.getElementById("InputChangeAvatar");
        fileSelector.click();
        //fileSelector.click();
    }

    const HandleInputChangePass = () => {
        if (IsChangePassword === CHANGE_PASSWORD) {
            setIsChangePassword(NOT_CHANGE_PASSWORD);
        } else {
            setIsChangePassword(CHANGE_PASSWORD);
        }

    }

    var SelectSexForm: React.RefObject<HTMLInputElement> = createRef();

    const HandleSubmit = () => {
        dispatch(AddNotify({path:"",destination:"Push thông báo thành công!!!",title:"BetStore"}));        
    }

    const ChangeSelectSexForm = (evt: React.ChangeEvent<any>) => {
        //alert(evt.target.value);
        //console.log($(`input[name=SelectSexForm][value=woman]`).attr("checked","true"));
    }

    return (
        <div className={styles.InfoProfile}>
            <h4>Thông tin tài khoản</h4>

            <div className={styles.ContentProfile}>
                <div className="">
                    <div className={styles.FormControl} >
                        <label>Họ</label>
                        <input
                            placeholder="Họ, tên đệm của bạn"
                            type="text"
                            value={profileState.surname}
                            onChange={(evt) => { setProfileState(SetSurName(evt.target.value)) }} />
                    </div>
                    <div className={styles.FormControl} >
                        <label>Tên</label>
                        <input
                            placeholder="Tên của bạn"
                            type="text"
                            value={profileState.name}
                            onChange={(evt) => { setProfileState(SetName(evt.target.value)) }} />
                    </div>
                    <div className={styles.FormControl} >
                        <label>Số điện thoại</label>
                        <input
                            placeholder="Nhập số số điện thoại của bạn"
                            type="text"
                            value={profileState.tel} />
                    </div>
                    <div className={styles.FormControl} >
                        <label>Giới Tính</label>
                        <div className={styles.SelectSexForm} ref={SelectSexForm} onChange={ChangeSelectSexForm}>
                            <input className="form-check-input" type="radio" name="SelectSexForm" id="womansex" defaultValue="woman" />
                            <label className="form-check-label" htmlFor="womansex">Nữ</label>
                            <input className="form-check-input" type="radio" name="SelectSexForm" id="mansex" defaultValue="man" />
                            <label className="form-check-label" htmlFor="mansex">Nam</label>
                            <input className="form-check-input" type="radio" name="SelectSexForm" id="othersex" defaultValue="other" />
                            <label className="form-check-label" htmlFor="othersex">Khác</label>
                        </div>
                    </div>

                    <div className={styles.FormControl}>
                        <div className={styles.TrackChange}>
                            <input type="checkbox" id="inputTrack" value={IsChangePassword} onChange={HandleInputChangePass} />
                            <label htmlFor="inputTrack">Đổi mật khẩu</label>
                        </div>
                    </div>
                    {IsChangePassword == CHANGE_PASSWORD && <div className={styles.ChangePassArea}>
                        <div className={styles.FormControl} >
                            <label>Mật khẩu cũ</label>
                            <input
                                placeholder="Nhập mật khẩu cũ"
                                type="text" />
                        </div>
                        <div className={styles.FormControl} >
                            <label>Mật khẩu mới</label>
                            <input
                                placeholder="Nhập mật khẩu từ 8 đến 32 kí tự"
                                type="text" />
                        </div>
                        <div className={styles.FormControl} >
                            <label>Nhập lại</label>
                            <input
                                placeholder="Xác nhận lại mật khẩu"
                                type="text" />
                        </div>
                    </div>}
                    <div className={styles.FormControl}>
                        <button className={styles.BtnSubmit} onClick={HandleSubmit}>
                            Cập nhật
                    </button>
                    </div>
                </div>

                <div className={styles.AvatarContainer}>
                    <input id="InputChangeAvatar" type="file" onChange={onChangeFile} ></input>
                    <span className={styles.BtnChangeAvatar} onClick={HandleChangeAvatar}>
                        <i className="fas fa-edit"></i>
                    </span>

                    <div className={styles.Avatar}>
                        <img src={`/cdn/cdn/${profile.Payload.avatar}`} alt="avatar" />
                    </div>

                </div>
            </div>
        </div>
    );
}
