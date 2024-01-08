import request from "@/tool/request";

let url = "http://192.168.214.1:8080";
export default class Friends {
    constructor() {

    }

    getFriendsList(id) {
        return request({
            method: "post",
            url: `${url}/guy/list/${id}`,
        })
    }

    findFriend(word) {
        return request({
            method: "post",
            url: `${url}/guy/search`,
            data: {
                id: word
            }
        })
    }

    deleteFriend(myId, guyId) {
        return request({
            method: "post",
            url: `${url}/guy/delete`,
            data: {
                myId,
                guyId,
            }
        })
    }

    addFriend(myId, guyId, remark) {
        return request({
            method: "post",
            url: `${url}/guy/add`,
            data: {
                myId: myId,
                guyId: guyId,
                remark: remark
            }
        })
    }

    modifyFriend(myId, guyId, remark) {
        return request({
            method: "post",
            url: `${url}/guy/remark/${remark}`,
            data: {
                myId,
                guyId,
            }
        })
    }

    getFriendsInfo() {
        return request({
            method: "post",
            url: `${url}/guy/detail`,
            data: {
                myId: 1,
                guyId: 1,
            }
        })
    }
}
