import request from "@/tool/request";
let url = "http://192.168.0.117:8080";
export default class Login {
    constructor() {

    }

    getVerifyImg() {
        return request({
            method: "get",
            url: `${url}/login/verify`
        })
    }

    checkLogin({ id, password, verifyCode }) {
        let param = new URLSearchParams();
        param.append('id', id);
        param.append('password', password);
        param.append('verifyCode', verifyCode);
        return request({
            url: `${url}/login/check`,
            method: "post",
            data: param
        })
    }

    startRegister({ nickName, password, code, email }) {
        let param = new URLSearchParams();
        param.append('nickName', nickName);
        param.append('code', code);
        param.append('password', password);
        param.append('email', email);
        return request({
            url: `${url}/register`,
            method: "post",
            data: param
        })
    }

    getEmailCode(email) {
        return request({
            url: `${url}/register/sendEmail`,
            method: "get",
            params: {
                email
            }
        })
    }
}
