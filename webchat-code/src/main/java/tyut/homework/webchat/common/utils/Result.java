package tyut.homework.webchat.common.utils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int code;//状态码

    private String msg;//提示信息

    private Object data;//数据

    public Result() {
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success(String msg){
        return new Result(200,msg);
    }

    public static Result success(Object obj){
        return new Result(200,obj);
    }

    public static Result error(String msg){
        return new Result(500,msg);
    }

    public static Result error(Object obj){
        return new Result(500,obj);
    }
}
