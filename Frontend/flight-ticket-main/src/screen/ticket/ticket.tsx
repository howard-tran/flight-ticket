import Axios from "axios";
import React, { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux";
import { TicketList } from "../../components/ticketList/ticketList";
import { ActionType } from "../../constants/ActionType";
import { FlightSearch } from "../../reducers/FlightReducer";
import style from "./ticket.module.scss";

export const Ticket: React.FC = () => {

  let defaultDate_t = new Date();
  defaultDate_t.setDate(defaultDate_t.getDate() + 1);
  let defaultDate = defaultDate_t.toISOString().substr(0,10);

  const flightSearchState = useSelector((state: {flightSearch: FlightSearch}) => state.flightSearch);

  const [flightSearch, setFlightSearch] = useState<FlightSearch>({
    index: 0,
    airlineStart: "VN-TSN",
    airlineEnd: "VN-CD",
    dateFlight: [defaultDate_t.getTime(), 0],
    seatClass: "ECONOMY",
    supplierId: "",
    adult: 1,
    minor: 0,
    baby: 0
  });

  const keepArrButIndex = (arr : any[], indexes: number[], values: any[]) => {
    let res = arr;
    
    for (let i = 0; i < indexes.length; i++) {
      res[indexes[i]] = values[i];
    }
    return res;
  }

  // action
  const flightSearchAction = (data : FlightSearch) : ActionType<any> => {
    return {
      type: "search",
      value: data
    }
  }

  const dispatch = useDispatch();

  const createWeatherWidget = () => {
    const s = document.createElement('script');
    s.type = 'text/javascript';
    s.async = true;
    s.innerHTML = `!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src='https://weatherwidget.io/js/widget.min.js';fjs.parentNode.insertBefore(js,fjs);}}(document,'script','weatherwidget-io-js');`;
    let node = document.getElementsByClassName("weatherwidget-io")[0];
    node.parentNode.append(s);
  }

  useEffect(() => {
    createWeatherWidget();
  }, []);

  return (
    <div style={{ marginBottom: "10px" }}>
      <form className={style.searchMain} style={{ backgroundImage: 'url("/cdn/cdn/049aacab8f22c9bdeb18184e15b565c903012021.jpg?width=1200&height=700")' }}>

        <div style={{ display: "flex" }}>
          <div className={style.searchWindow}>
            <div className={style.header}>
              <i className="fa fa-plane fa-3x"></i>
              <h3>Search Flight</h3>
            </div>

            <hr></hr>

            <div className={style.flightChoose}>
              <div>
                <label>Departure</label>
                <br></br>
                <select 
                  onChange={e => {setFlightSearch({...flightSearch, airlineStart: e.target.value})}} 
                  style={{ width: "200px" }} id="depart"
                >
                  <option value="VN-TSN">Tân Sơn Nhất</option>
                  <option value="VN-CD">Côn Đảo</option>
                  <option value="VN-PC">Phù Cát</option>
                  <option value="VN-CM">Cà Mau</option>
                  <option value="VN-CT">Cần Thơ</option>
                  <option value="VN-BMT">Buôn Ma Thuột</option>
                  <option value="VN-DBP">Điện Biên Phủ</option>
                  <option value="VN-PLKU">Pleiku</option>
                  <option value="VN-NB">Nội Bài</option>
                  <option value="VN-CR">Cam Ranh</option>
                  <option value="VN-RG">Rạch Giá</option>
                  <option value="VN-PQ">Phú Quốc</option>
                  <option value="VN-LK">Liên Khương</option>
                  <option value="VN-VI">Vinh</option>
                  <option value="VN-TH">Tuy Hòa</option>
                  <option value="VN-DH">Đồng Hới</option>
                  <option value="VN-CL">Chu Lai</option>
                  <option value="VN-PB">Phú Bài</option>
                  <option value="VN-TX">Thọ Xuân</option>
                  <option value="VN-VD">Vân Đồn</option>
                </select>
              </div>

              <div>
                <label>Arrival</label>
                <br></br>
                <select
                  onChange={e => {setFlightSearch({...flightSearch, airlineEnd: e.target.value})}}  
                  style={{ width: "200px" }} id="arrive"
                >
                  <option value="VN-CD">Côn Đảo</option>
                  <option value="VN-PC">Phù Cát</option>
                  <option value="VN-CM">Cà Mau</option>
                  <option value="VN-CT">Cần Thơ</option>
                  <option value="VN-BMT">Buôn Ma Thuột</option>
                  <option value="VN-DBP">Điện Biên Phủ</option>
                  <option value="VN-PLKU">Pleiku</option>
                  <option value="VN-NB">Nội Bài</option>
                  <option value="VN-TSN">Tân Sơn Nhất</option>
                  <option value="VN-CR">Cam Ranh</option>
                  <option value="VN-RG">Rạch Giá</option>
                  <option value="VN-PQ">Phú Quốc</option>
                  <option value="VN-LK">Liên Khương</option>
                  <option value="VN-VI">Vinh</option>
                  <option value="VN-TH">Tuy Hòa</option>
                  <option value="VN-DH">Đồng Hới</option>
                  <option value="VN-CL">Chu Lai</option>
                  <option value="VN-PB">Phú Bài</option>
                  <option value="VN-TX">Thọ Xuân</option>
                  <option value="VN-VD">Vân Đồn</option>
                </select>
              </div>
            </div>

            <div className={style.flightChoose}>
              <div>
                <label>Departure Date</label>
                <br></br>
                <input 
                  onChange={e => {
                    let unix = new Date(e.target.value);
                    setFlightSearch({...flightSearch, 
                      dateFlight: keepArrButIndex(flightSearch.dateFlight, [0], [unix.getTime()])})}}   
                  type="date" defaultValue={defaultDate}>
                  
                </input>
              </div>

              <div>
                <label>Return Date</label>
                <br></br>
                <input 
                  onChange={e => {
                    let unix = new Date(e.target.value);
                    setFlightSearch({...flightSearch, 
                      dateFlight: keepArrButIndex(flightSearch.dateFlight, [1], [unix.getTime()])})}}
                  type="date" defaultValue={null}>

                </input>
              </div>
            </div>

            <div className={style.flightChoose}>
              <div>
                <label>Seat class</label>
                <br></br>
                <select
                  onChange={e => {setFlightSearch({...flightSearch, seatClass: e.target.value})}}   
                  style={{ width: "200px" }} id="arrive"
                >
                  <option value="ECONOMY">ECONOMY</option>
                  <option value="BUSINESS">BUSINESS</option>
                  <option value="PRENIUM">PRENIUM</option>
                </select>
              </div>

              <div>
                <label>Supplier</label>
                <br></br>
                <select 
                  onChange={e => {setFlightSearch({...flightSearch, supplierId: e.target.value})}}  
                  style={{ width: "200px" }} id="arrive"
                >
                  <option value="">All supplier</option>
                  <option value="VN-VJ">VietJet Air</option>
                  <option value="VN-VA">VietNam Airline</option>
                  <option value="VN-BB">BamBoo Airline</option>
                  <option value="VN-JP">Jetstar Pacific Airline</option>
                </select>
              </div>
            </div>

            <hr></hr>

            <div>
              <div className={style.flightChoose}>
                <h4 style={{width:"80px"}}>Adult</h4>
                <input
                  onChange={e => {setFlightSearch({...flightSearch, adult: Number.parseInt(e.target.value)})}}
                  onKeyDown={e => {e.preventDefault()}}
                  type="number" defaultValue={1} style={{width:"60px"}}>
                </input>
              </div>

              <div className={style.flightChoose}>
                <h4 style={{width:"80px"}}>Minors</h4>
                <input 
                  onChange={e => {setFlightSearch({...flightSearch, minor: Number.parseInt(e.target.value)})}}
                  onKeyDown={e => {e.preventDefault()}}
                  type="number" defaultValue={0} style={{width:"60px"}}>

                </input>
            </div>

              <div className={style.flightChoose}>
                <h4 style={{width:"80px"}}>Baby</h4>
                <input 
                  onChange={e => {setFlightSearch({...flightSearch, baby: Number.parseInt(e.target.value)})}}  
                  onKeyDown={e => {e.preventDefault()}}
                  type="number" defaultValue={0} style={{width:"60px"}}>

                </input>
              </div>
            </div>

            <div className={style.flightChoose}>
              <br></br>
              <br></br>
              <button type="submit" className="btn btn-success" 
                onClick={e => {
                  e.preventDefault();
                  dispatch(flightSearchAction(flightSearch));
                }}>
                  
                Search
              </button>
            </div>
          </div>

          <div id="weatherEmbed" style={{ width: "55%", marginLeft: "35px", marginTop: "25px" }}>
            <a className="weatherwidget-io" href="https://forecast7.com/en/10d82106d63/ho-chi-minh-city/" data-label_1="HO CHI MINH" data-label_2="WEATHER" data-theme="original" >HO CHI MINH WEATHER</a>
          </div>

        </div>
      
      </form>

      <div className={style.ticketList}>
        <TicketList dateIndex={0}></TicketList>
      </div>

      <div className={style.ticketList}>
        <TicketList dateIndex={1}></TicketList>
      </div>
    </div>
  )
}