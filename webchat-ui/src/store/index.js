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
        currentChat: "聊天室",
        messages: [],
        onlinePerson: 0,
        onlinePeople: [],
        inflinePeople: []

    },
    mutations: {

    },
    actions: {

    },
})