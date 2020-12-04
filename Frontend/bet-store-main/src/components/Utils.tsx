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

export interface AccountInfo {
  id: string;
  user: string;
  exp: number;
  iat: number;
  iss: string;
}