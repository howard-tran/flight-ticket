import React, { useState } from "react";
import { FlightInfo } from "../../reducers/FlightReducer";
import style from "./ticketList.module.scss";

import jetStarSvg from "../../resource/images/svg/jetStar.png";
import vietJetSvg from "../../resource/images/svg/vietJet.png";
import vietnamAirlineSvg from "../../resource/images/svg/vietnamAirline.png";
import bambooAirlineSvg from "../../resource/images/svg/bamboo.png";
import { useDispatch, useSelector } from "react-redux";
import { FlightSearch } from "../../reducers/FlightReducer";
import locale from "javascript-time-ago/locale/vi";
import { ActionType } from "../../constants/ActionType";
import { Redirect } from "react-router";


export const TicketComponent: React.FC<{
  id: string;
  airplaneId: string;
  supplierId: string;
  isExpired: number;
  carryOn: number;
  checkInBaggage: number;
  airlineStart: string;
  airlineEnd: string;
  price: number;
  flightDate: number;
  seatClass: string
}> = (par) => {  
  const [redirectToSubmit, setRedirectToSubmit] = useState<boolean>(false);
  const dispath = useDispatch();

  const supplierRender = () => {
    if (par.supplierId == "VN-JP") {
      return <img src={jetStarSvg} width={100} height={30} alt=""/>
    } else if (par.supplierId == "VN-VJ") {
      return <img src={vietJetSvg} width={100} height={30} alt=""/>
    } else if (par.supplierId == "VN-VA") {
      return <img src={vietnamAirlineSvg} width={150} height={30} alt=""/>
    } else return <img src={bambooAirlineSvg} width={100} height={30} alt=""/>
  }

  const timeFlightLocal = () => {
    let date = new Date(par.flightDate);
    let delay = new Date(date.getTime() + (135*60*1000));
    return `${date.getHours()}:${date.getMinutes()} - ${delay.getHours()}:${delay.getMinutes()}`
  }

  const localMoney = () => {
    let money = par.price;
    if (par.seatClass == "BUSINESS") {
      money *= 3.0;
    } else if (par.seatClass == "PRENIUM") {
      money *= 1.5;
    }
    let str = money.toFixed(1).replace(/\d(?=(\d{3})+\.)/g, '$&,');
    let index = str.lastIndexOf(".");

    return str.substr(0, index) + " VND";
  }

  const localWeight = () => {
    return par.checkInBaggage.toFixed(0) + " kg";
  }

  const setFlightInfo = (data: FlightInfo): ActionType<any> => {
    return {
      type: "submitReceipt",
      value: data
    }
  }


  if (redirectToSubmit) {
    return <Redirect push to={`/submitReceipt/${par.id}`}/>;
  }

  return (
    <div className={style.ticketComponent} onClick={e => {
      dispath(setFlightInfo({
        id: par.id,
        airplaneId: par.airplaneId,
        supplierId: par.supplierId,
        isExpired: par.isExpired,
        carryOn: par.carryOn,
        checkInBaggage: par.checkInBaggage,
        airlineStart: par.airlineStart,
        airlineEnd: par.airlineEnd,
        price: par.price,
        flightDate: par.flightDate
      }))

      setRedirectToSubmit(true);
    }}>
      <div className={style.supplier}>
        {supplierRender()}
      </div>
      <div className={style.airplane}>
        <h5 style={{margin:"0", padding:"0"}}>{par.airplaneId}</h5>
      </div>
      <div className={style.timeFlight}>
        <h5>{timeFlightLocal()}</h5>
      </div>
      <div className={style.price}>
        <h5>{localMoney()}</h5>
      </div>
      <div className={style.weight}>
        <i className="fa fa-suitcase fa-2x"></i>
        <h5>{localWeight()}</h5>
      </div>
    </div>
  )
}