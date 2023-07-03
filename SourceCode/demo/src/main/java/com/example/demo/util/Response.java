package com.example.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName Response
 * @Author ZhangFaTong
 * @create 2023/6/30 14:28
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private final static Boolean SUCCESS = true;
    private final static Boolean ERROR = false;

    public static <T> Result<T> ok() {
        return new Result<T>().setCode(ResultEnum.SUCCESS).setSuccess(SUCCESS);
    }

    public static <T> Result<T> ok(ResultEnum Enum) {
        return new Result<T>().setCode(Enum).setSuccess(SUCCESS).setMsg(Enum.getMsg());
    }

    public static <T> Result<T> ok(T data) {
        return new Result<T>().setCode(ResultEnum.SUCCESS).setSuccess(SUCCESS).setData(data);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return new Result<T>().setCode(ResultEnum.SUCCESS).setMsg(msg).setSuccess(SUCCESS).setData(data);
    }

    public static <T> Result<T> error() {
        return new Result<T>().setCode(ResultEnum.ERROR).setSuccess(ERROR);
    }


    public static <T> Result<T> error(ResultEnum Enum, String msg) {
        if (StringUtils.isEmpty(msg)) {
            return new Result<T>().setCode(Enum).setMsg(msg).setSuccess(ERROR);
        }
        return new Result<T>().setCode(Enum).setMsg(msg).setSuccess(ERROR);
    }

    public static <T> Result<T> error(ResultEnum Enum) {
        return new Result<T>().setCode(Enum).setMsg(Enum.getMsg()).setSuccess(ERROR).setData(null);
    }

    public static <T> Result<T> response(String code) {
        return new Result<T>().setCode(code).setSuccess(SUCCESS);
    }

    public static <T> Result<T> response(String code, Boolean success, T data) {
        return new Result<T>().setCode(code).setSuccess(success).setData(data);
    }

}