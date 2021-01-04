import Axios from "axios";
import React, { useEffect, useState } from "react"
import { useSelector } from "react-redux";
import { RouteComponentProps } from "react-router"
import { FlightInfo, FlightSearch } from "../../reducers/FlightReducer";
import jetStarSvg from "../../resource/images/svg/jetStar.png";
import vietJetSvg from "../../resource/images/svg/vietJet.png";
import vietnamAirlineSvg from "../../resource/images/svg/vietnamAirline.png";
import bambooAirlineSvg from "../../resource/images/svg/bamboo.png";
import "../../index.css";
import { string } from "prop-types";
import { webDomain } from "../../constants/Domain";

export const SubmitReceipt: React.FC<RouteComponentProps<{ flightId: string }>> = ({ match }) => {
  const flightSearchState = useSelector((state: { flightSearch: FlightSearch }) => state.flightSearch);
  const flightInfoState = useSelector((state: { flightInfo: FlightInfo }) => state.flightInfo);

  const [heading, setHeading] = useState<string>("Loading...");
  const [airplane, setAirplane] = useState<string>("Loading...");
  const [contact, setContact] = useState<{
    fullname: string;
    phone: string;
    email: string;}>({
      email: "", fullname: "", phone: ""
    });

  const requestHeading = () => {
    if (flightInfoState.airlineStart.length == 0)
      return;

    Axios.get(`/java/api/ticket/getAirline?id=${flightInfoState.airlineStart}`).then(a => {
      Axios.get(`/java/api/ticket/getAirline?id=${flightInfoState.airlineEnd}`).then(b => {
        setHeading(a.data.data.name + " ➟ " + b.data.data.name);
      });
    });
  }

  const requestAirplaneModel = () => {
    if (flightInfoState.airlineStart.length == 0)
      return;

    Axios.get(`/java/api/ticket/getAirplaneModel?id=${flightInfoState.airplaneId}`).then(a => {
      setAirplane(a.data.data.model);
    });
  }

  const supplierRender = () => {
    if (flightInfoState.supplierId == "VN-JP") {
      return <img src={jetStarSvg} style={{ marginRight: "5px" }} width={100} height={30} alt="" />
    } else if (flightInfoState.supplierId == "VN-VJ") {
      return <img src={vietJetSvg} style={{ marginRight: "5px" }} width={100} height={30} alt="" />
    } else if (flightInfoState.supplierId == "VN-VA") {
      return <img src={vietnamAirlineSvg} style={{ marginRight: "5px" }} width={150} height={30} alt="" />
    } else return <img src={bambooAirlineSvg} style={{ marginRight: "5px" }} width={100} height={30} alt="" />
  }

  const localFlightDate = () => {
    let days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    let months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

    const date = new Date(flightInfoState.flightDate);
    return `${days[date.getDay()]} ${date.toLocaleDateString("en-GB")}`;
  }

  const timeFlightLocal = () => {
    let date = new Date(flightInfoState.flightDate);
    let delay = new Date(date.getTime() + (135 * 60 * 1000));
    return `${date.getHours()}:${date.getMinutes()} - ${delay.getHours()}:${delay.getMinutes()}`
  }

  const renderTicketLabel = () => {
    let arr: JSX.Element[] = [];

    if (flightSearchState.adult > 0) {
      arr.push(<h5>Adult tickets</h5>)
    }
    if (flightSearchState.minor > 0) {
      arr.push(<h5>Minor tickets</h5>)
    }
    if (flightSearchState.baby > 0) {
      arr.push(<h5>Baby tickets</h5>)
    }
    return arr;
  }

  const renderTicketCount = () => {
    let arr: JSX.Element[] = [];
    if (flightSearchState.adult > 0) {
      arr.push(<h5>x {flightSearchState.adult}</h5>)
    }
    if (flightSearchState.minor > 0) {
      arr.push(<h5>x {flightSearchState.minor}</h5>)
    }
    if (flightSearchState.baby > 0) {
      arr.push(<h5>x {flightSearchState.baby}</h5>)
    }
    return arr;
  }

  const getMoneyFormat = (money: number) => {
    let str = money.toFixed(1).replace(/\d(?=(\d{3})+\.)/g, '$&,');
    let index = str.lastIndexOf(".");

    return str.substr(0, index);
  }

  const renderMoney = () => {
    let arr: JSX.Element[] = [];
    let total = 0;
    if (flightSearchState.adult > 0) {
      let money = Math.round(flightInfoState.price) * flightSearchState.adult;
      total += money;

      arr.push(<h5>{getMoneyFormat(money)} VND</h5>)
    }
    if (flightSearchState.minor > 0) {
      let money = Math.round(flightInfoState.price * 0.8) * flightSearchState.minor;
      total += money;
      arr.push(<h5>{getMoneyFormat(money)} VND</h5>)
    }
    if (flightSearchState.baby > 0) {
      let money = Math.round(flightInfoState.price * 0.1) * flightSearchState.baby;
      total += money;
      arr.push(<h5>{getMoneyFormat(money)} VND</h5>)
    }
    arr.push(<br></br>)
    arr.push(<h5>10%</h5>)
    arr.push(<h5>{getMoneyFormat(total * 1.1)} VND</h5>)

    return arr;
  }

  const checkPhonenumber = (num: string) => {
    var reg = /^[0-9]{10,11}$/;
    var checking = reg.test(num);

    return checking;
  }

  const checkSubmitInfo = () => {
    if (!checkPhonenumber(contact.phone)) {
      alert("invalid phone number");
      return false;
    }
    if (contact.fullname.length <= 0) {
      alert("invalid name");
      return false;
    }
    return true;
  }

  useEffect(() => {
    requestHeading();
    requestAirplaneModel();
  }, [flightInfoState]);

  return (
    <div style={{ marginTop: "10px" }}>
      <div style={{ padding: "5px", marginBottom: "5px", backgroundColor: "whitesmoke" }}>
        <div style={{ display: "flex", height: "150px" }}>
          <div style={{ marginRight: "20px" }}>
            <h3 style={{ fontWeight: "bold" }}>{heading}</h3>
            {supplierRender()}
            <h5 style={{ padding: "0px", transform: "translateY(15px)" }}>Flight: {flightInfoState.airplaneId}</h5>
            <h5 style={{ padding: "0px", transform: "translateY(5px)" }}>Model: {airplane}</h5>
          </div>

          <div style={{ marginRight: "50px" }}>
            <h5>{localFlightDate()}</h5>
            <h5>{timeFlightLocal()}</h5>
          </div>

          <div style={{ marginRight: "45px" }}>
            {[renderTicketLabel()]}
            <br></br>
            <h5>VAT-Tax + Additionals</h5>
            <h5>Total</h5>
          </div>

          <div style={{ marginRight: "20px" }}>
            {[renderTicketCount()]}
          </div>

          <div style={{ marginRight: "20px" }}>
            {[renderMoney()]}
          </div>
        </div>

      </div>

      <div style={{ padding: "5px", marginBottom: "5px", backgroundColor: "whitesmoke" }}>
        <h3 style={{ fontWeight: "bold" }}>Contact information</h3>
        <br></br>

        <label style={{ width: "120px" }} className="required">Full Name</label>
        <input type="text" onChange={e => {setContact({...contact, fullname: e.target.value})}} required></input>
        <br></br>
        <label style={{ width: "120px" }} className="required">Phone</label>
        <input type="text" onChange={e => {setContact({...contact, phone: e.target.value})}} required></input>
        <br></br>
        <label style={{ width: "120px" }}>Email</label>
        <input type="text" onChange={e => {setContact({...contact, email: e.target.value})}}></input>
        <br></br>
      </div>

      <div style={{ padding: "5px", marginBottom: "5px", backgroundColor: "whitesmoke" }}>
      <h3 style={{ fontWeight: "bold" }}>Additional information</h3>
        <h5>+ Our staff will reach-out to you as soon as posible</h5>
        <h5>+ Please refer to <a href={webDomain}>this address</a> to pay for your receipt</h5>
        <h5>+ There will be an SMS include E-Ticket sended to your phone after payment</h5>
        <h5>+ Much love and please dont die before you make the payment</h5>
      </div>

      <button style={{fontSize:"24px", marginTop:"10px", marginBottom:"50px"}} type="submit" className="btn btn-danger" 
        onClick={e => {
          if(checkSubmitInfo()) {
            // submit the receipt
          }
        }}>
          
        Submit
      </button>
    </div>
  )
} 