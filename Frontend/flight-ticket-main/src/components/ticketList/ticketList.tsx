import Axios from "axios";
import React, { useCallback, useEffect, useRef, useState } from "react"
import ReactPaginate from "react-paginate";
import { useDispatch, useSelector } from "react-redux"
import { ArrayData, FlightInfo, FlightSearch } from "../../reducers/FlightReducer"
import Pagination from "../pagination/pagination";
import "../../paginate.css";
import style from "./ticketList.module.scss";
import { TicketComponent } from "./ticketComponent";

export const TicketList: React.FC<{ dateIndex: number }> = (par) => {
  const flightSearchState = useSelector((state: { flightSearch: FlightSearch }) => state.flightSearch);

  const [heading, setHeading] = useState<string>("Loading...");
  const [listFlight, setListFlight] = useState<FlightInfo[]>([]);
  const [dtbSize, setDtbSize] = useState<number>();

  const listFlightRef = useRef<HTMLDivElement>();
  const setListFlightRef = useCallback((node) => {
    listFlightRef.current = node;
  }, []);

  const dispath = useDispatch();

  const requestFlight = async (index: number) => {
    let url;

    if (flightSearchState.supplierId.length == 0) {
      url = `/java/api/ticket/get?index=${index}&airlineStart=${flightSearchState.airlineStart}&airlineEnd=${flightSearchState.airlineEnd}`
        + `&seatClass=${flightSearchState.seatClass}&dateFlight=${flightSearchState.dateFlight[par.dateIndex]}`
        + `&customerCount=${flightSearchState.adult + flightSearchState.minor + flightSearchState.baby}`;

      if (par.dateIndex == 1) {
        url = `/java/api/ticket/get?index=${index}&airlineStart=${flightSearchState.airlineEnd}&airlineEnd=${flightSearchState.airlineStart}`
        + `&seatClass=${flightSearchState.seatClass}&dateFlight=${flightSearchState.dateFlight[par.dateIndex]}`
        + `&customerCount=${flightSearchState.adult + flightSearchState.minor + flightSearchState.baby}`;
      }
    } else {
      url = `/java/api/ticket/getFilterSupplier?index=${index}&supplierId=${flightSearchState.supplierId}&airlineStart=${flightSearchState.airlineStart}&airlineEnd=${flightSearchState.airlineEnd}`
        + `&seatClass=${flightSearchState.seatClass}&dateFlight=${flightSearchState.dateFlight[par.dateIndex]}`
        + `&customerCount=${flightSearchState.adult + flightSearchState.minor + flightSearchState.baby}`;
      
      if (par.dateIndex == 1) {
        url = `/java/api/ticket/getFilterSupplier?index=${index}&supplierId=${flightSearchState.supplierId}&airlineStart=${flightSearchState.airlineEnd}&airlineEnd=${flightSearchState.airlineStart}`
          + `&seatClass=${flightSearchState.seatClass}&dateFlight=${flightSearchState.dateFlight[par.dateIndex]}`
          + `&customerCount=${flightSearchState.adult + flightSearchState.minor + flightSearchState.baby}`;
      }
    }

    console.log(url);

    Axios.get(url).then(response => {
      let arrayData: ArrayData<FlightInfo[]> = response.data.data as ArrayData<FlightInfo[]>;
      setListFlight(arrayData.array);
      setDtbSize(arrayData.dtbSize);
    });

    if (listFlightRef.current != null)
      listFlightRef.current.scrollTop = 0;
  }

  const localFlightDate = () => {
    let days = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
    let months = ['January','February','March','April','May','June','July','August','September','October','November','December'];

    const date = new Date(flightSearchState.dateFlight[par.dateIndex]);
    return `${days[date.getDay()]} ${date.toLocaleDateString("en-GB")}`;
  }

  const requestHeading = () => {
    if (flightSearchState.airlineStart.length == 0)
      return;

    if (par.dateIndex == 1) {
      Axios.get(`/java/api/ticket/getAirline?id=${flightSearchState.airlineEnd}`).then(a => {
        Axios.get(`/java/api/ticket/getAirline?id=${flightSearchState.airlineStart}`).then(b => {
          setHeading(a.data.data.name + " ➟ " + b.data.data.name);
        });
      });
      return;
    }
    Axios.get(`/java/api/ticket/getAirline?id=${flightSearchState.airlineStart}`).then(a => {
      Axios.get(`/java/api/ticket/getAirline?id=${flightSearchState.airlineEnd}`).then(b => {
        setHeading(a.data.data.name + " ➟ " + b.data.data.name);
      });
    });
  }

  const sortOptionHandle = (e: React.ChangeEvent<HTMLSelectElement>) => {
    if (e.target.value == "") return;

    let arr = [...listFlight];

    if (e.target.value == "time:increase") {
      arr.sort((a, b) => {
        return a.flightDate - b.flightDate;
      });
    }
    if (e.target.value == "time:decrease") {
      arr = arr.sort((a, b) => {
        return b.flightDate - a.flightDate;
      });
    }
    
    if (e.target.value == "price:increase") {
      arr = arr.sort((a, b) => {
        return a.price - b.price;
      });
    }
    if (e.target.value == "price:decrease") {
      arr = arr.sort((a, b) => {
        return b.price - a.price;
      });
    }

    if (e.target.value == "weight:increase") {
      arr = arr.sort((a, b) => {
        return a.checkInBaggage - b.checkInBaggage;
      });
    }
    if (e.target.value == "weight:decrease") {
      arr = arr.sort((a, b) => {
        return b.checkInBaggage - a.checkInBaggage;
      });
    }
    setListFlight(arr);
  }

  useEffect(() => {
    requestHeading();
    requestFlight(0);
  }, [flightSearchState])

  const handlePageChange = (data: any) => {
    requestFlight(data.selected);
  }

  if (flightSearchState.dateFlight[par.dateIndex] == 0)
    return (<div></div>);
  return (
    <div className={style.ticketListMain}>

      <div style={{backgroundColor:"#00617E", padding:"5px", color:"white"}}>
        <h2 style={{marginLeft:"5px", marginTop:"5px", fontWeight:"bold"}}>{heading}</h2>
        <h3 style={{marginLeft:"5px", marginTop:"5px"}}>{localFlightDate()}</h3>

        <label style={{fontSize:"20px", marginRight:"5px"}}>Sort by</label>
        <select style={{fontSize:"20px", marginRight:"5px"}}
          onChange={sortOptionHandle}>
          <option value="">None</option>

          <optgroup label="Time">
            <option value="time:increase">Increase</option>
            <option value="time:decrease">Decrease</option>
          </optgroup>

          <optgroup label="Price">
            <option value="price:increase">Increase</option>
            <option value="price:decrease">Decrease</option>
          </optgroup>

          <optgroup label="Weight">
            <option value="weight:increase">Increase</option>
            <option value="weight:decrease">Decrease</option>
          </optgroup> 
        </select>
      </div>

      <div className={style.ticketList} ref={setListFlightRef}>
        {listFlight.map(flight =>
          <TicketComponent
            id={flight.id}
            supplierId={flight.supplierId}
            price={flight.price}
            flightDate={flight.flightDate}
            isExpired={flight.isExpired}
            airplaneId={flight.airplaneId}
            carryOn={flight.carryOn}
            checkInBaggage={flight.checkInBaggage}
            airlineStart={flight.airlineStart}
            airlineEnd={flight.airlineEnd}
            seatClass={flightSearchState.seatClass}>
          </TicketComponent>)}
      </div>
      
      <hr></hr>
      <ReactPaginate
        previousLabel={"prev"}
        nextLabel={"next"}
        breakLabel={"..."}
        breakClassName={"break-me"}
        pageCount={dtbSize / 30}
        marginPagesDisplayed={2}
        pageRangeDisplayed={5}
        onPageChange={handlePageChange}
        containerClassName={"pagination"}
        activeClassName={"active"}/>
    </div>
  )
}