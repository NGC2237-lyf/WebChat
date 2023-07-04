import request from "@/tool/request";
let url = "http://192.168.0.110:8080";
export default class Login {
    constructor() {

    }

    getVerifyImg() {
        return request({
            method: "get",
            url: `${url}/login/verify`
        })
    }

    checkLogin({ email, password, verifyCode }) {
        let param = new URLSearchParams();
        param.append('email', email);
        param.append('password', password);
        param.append('verifyCode', verifyCode);
        return request({
            url: `${url}/login/check`,
            method: "post",
            data: param
        })
    }

    startRegister({ nickName, password, code, email }) {
        return request({
            url: `${url}/register`,
            method: "post",
            data: {
                nickName,
                code,
                password,
                email
            },
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
