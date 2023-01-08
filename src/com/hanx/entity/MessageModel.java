package com.hanx.entity;

public class MessageModel {
    private Integer code = 0;  //状态: 1=success; 0=failure
    private String msg = "Failure"; //具体描述
    private Object content; //用于回传数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
