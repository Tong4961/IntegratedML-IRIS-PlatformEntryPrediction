package com.example.demo.util;

/**
 * @ClassName ResultEnum
 * @Author ZhangFaTong
 * @create 2023/6/30 14:19
 */
public enum ResultEnum {

    ERROR("-1","暂无数据"),
    WARNING("400","非法操作"),
    NOT_TIME("-3","不在时间内"),
    NOT_FOUND_RESULT("-4","未找到结果集"),
    PARAM_ERROR("-5","未获取完整参数"),
    SUCCESS("200","操作成功"),
    FILE_DOES_NOT_EXIST("-2","文件不存在"),
    RESPONSE_ERROR("400","");


    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // 请求成功、失败
    public static final String RES_SCU = "请求成功";
    public static final int RES_SCU_CODE = 200;
    public static final int RES_DEAL_SUC_CODE = 200;
    public static final String RES_DEAL_SUC_MSG = "业务处理成功";
    public static final int RES_DEAL_FAIL_CODE = -100000;
    public static final String RES_DEAL_FAIL_MSG = "业务处理失败";
}