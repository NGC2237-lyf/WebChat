/**
    * 通过Blob的形式下载
    * @param {String} filetype 文件类型
    * @param {String} data Blob字符串
    * @param {String} fileName 文件名称
    */
export default function downloadBase64(data, filetype, fileName) {
    if (window.navigator.msSaveOrOpenBlob) {
        // navigator.msSaveBlob(blob, fileName);
    } else {
        let link = document.createElement("a");
        link.href = `data:${filetype};base64,${data}`;
        link.download = fileName;
        link.click();
        window.URL.revokeObjectURL(link.href);
    }
}