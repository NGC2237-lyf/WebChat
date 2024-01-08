import request from "@/tool/request";
let url = "http://192.168.214.1:8080";
export default function downloadFile(type, userId, fileName) {
    return request({
        url: `${url}/information/download/${type}/${userId}/${fileName}`,
        method: "post",
        header: {
            headers: { 'Content-Type': 'application/x-download' }
        },
    })
}