export function copyToClipboard(text: string) {
  let input = document.body.appendChild(document.createElement("input")) as HTMLInputElement;
  input.value = text;
  input.focus();
  input.select();
  document.execCommand('copy');
  input.parentNode?.removeChild(input);
}