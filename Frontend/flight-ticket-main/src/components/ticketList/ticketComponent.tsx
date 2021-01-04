import React from "react";
import { FlightInfo } from "../../reducers/FlightReducer";
import style from "./ticketList.module.scss";

import jetStarSvg from "../../resource/images/svg/jetStar.png";
import vietJetSvg from "../../resource/images/svg/vietJet.png";
import vietnamAirlineSvg from "../../resource/images/svg/vietnamAirline.png";
import bambooAirlineSvg from "../../resource/images/svg/bamboo.png";


export const TicketComponent: React.FC<FlightInfo> = (par) => {
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

  return (
    <div className={style.ticketComponent}>
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
        
      </div>
    </div>
  )
}