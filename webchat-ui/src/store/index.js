// store/index.js
import { createStore } from 'vuex'

export default createStore({
    state: {
        info: {
            id: "",
            photo: "",
            name: "",
        },
        isShow: {
            rightmenu: false,
            personInfo: false,
            onlinePerson: false
        },
        search: {
            id: "",
            nickName: "",
            email: "",
            photo: ""
        },
        friends: [],
        currentChat: { name: "聊天室", id: 0 },
        messages: [],
        chatRoom: [],
        private: {

        },
        onlinePerson: 0,
        onlinePeople: [],
        inflinePeople: [],
    },
    mutations: {

    },
    actions: {

    },
})