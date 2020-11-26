import React, { Component } from "react";
import { NumericLiteral } from "typescript";
import style from "../styles/ChatBox.module.scss";

interface APIResponse<T> {
  status: number;
  message: string;
  data: T;
}

export function ChatBox() {
  
  return(
    <div className={style.chatBox}>
      <i style={{color: '#ffffff'}} className="fas fa-comments fa-4x"></i>
    </div>
  )
}