// base64 转 二进制流(blob)
export function dataURLtoBlob(dataurl) {
    // console.log(dataurl);
    // let str = "data:audio/wav;base64," + dataurl.slice(2)
    // console.log(str);
    let mime = "audio/wav"
    // var arr = str.split(","),
    // mime = arr[0].match(/:(.*?);/),
    // let bstr = atob(arr[1])
    let bstr = atob(dataurl)
    let n = bstr.length
    let u8arr = new Uint8Array(n);
    while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new Blob([u8arr], {
        type: mime,
    });
}