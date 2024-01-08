import request from "@/tool/request";

let url = "http://192.168.214.1:8080";

export default function getHistory(userId, start, end) {
    return request({
        url: `${url}/information/history/${userId}/${start}/${end}`,
        method: "get"
    })
}