import Axios from "axios";
import reactElementToJSXString from "react-element-to-jsx-string";

export function copyToClipboard(text: string) {
  let input = document.body.appendChild(document.createElement("input")) as HTMLInputElement;
  input.value = text;
  input.focus();
  input.select();
  document.execCommand('copy');
  input.parentNode?.removeChild(input);
}

export function toDomNode(x : JSX.Element) {
  return (new DOMParser()).parseFromString(reactElementToJSXString(x), 'text/html').body.firstChild;
}

export const getTextWidth = (text: string, font: string) => {
  let canvas = document.createElement("canvas");
  canvas.style.visibility = "hidden";

  let context = canvas.getContext("2d");
  context.font = font;
  let metrics = context.measureText(text);
  return metrics.width;
};

export interface AccountInfo {
  id: string;
  user: string;
  avatar: string;
  exp: number;
  iat: number;
  iss: string;
}