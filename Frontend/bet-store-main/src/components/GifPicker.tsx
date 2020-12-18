
import React, { useState } from "react";
import {GiphyFetch} from "@giphy/js-fetch-api";
import { IGif } from "@giphy/js-types";
import { Gif, Grid, Carousel } from "@giphy/react-components";

export const gifWidthDisplay = 400;

const giphyFetch = new GiphyFetch("0L5XCnJwI21DzramGOLEE5sZiUDihjVe");

const _GifPicker: React.FC<{onGifChoosen: (gif: any, e : any) => void}> = (param) => {
  const [searchString, setSearchString] = useState<string>("");

  const fetchGifTrending = (_offset: number) => {
    return giphyFetch.trending({offset: _offset, limit: 9});
  }

  const fetchGifOnSearch = (_offset: number) => {
    return giphyFetch.search(searchString, {offset: _offset, limit: 9});
  }

  const renderHandle = () => {
    if (searchString.length == 0) {
      return (
        <div style={{overflowY:"scroll", height:"90%"}}>
          <Grid
            onGifClick={param.onGifChoosen}
            fetchGifs={fetchGifTrending}
            width={gifWidthDisplay}
            columns={2}
            gutter={6}>

          </Grid>
        </div>
      )
    }
    return (
      <div style={{overflowY:"scroll", height:"90%"}}>
        <Grid
          onGifClick={param.onGifChoosen}
          fetchGifs={fetchGifOnSearch}
          width={gifWidthDisplay}
          columns={2}
          gutter={6}>
            
        </Grid>
      </div>
    )
  }

  return ( 
    <div style={{backgroundColor:"whitesmoke", height:"450px", maxHeight:"450px", padding:"10px"}}>
      <div style={{height:"10%", display:"flex", flexFlow:"row", alignItems:"center"}}>
        <i className="fa fa-search fa-1x" style={{paddingRight:"5px"}}></i>
        <input style={{resize:"none"}} onChange={
          (e : React.ChangeEvent<HTMLInputElement>) => {
            setSearchString((e.target as HTMLInputElement).value);
          }
        }></input>
      </div>
      {renderHandle()}
    </div>
  )
};

export const GifPicker = React.memo(_GifPicker);
