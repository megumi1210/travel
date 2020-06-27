package com.huike.travel.domain;

public class LoginMessage {

    private  String message;
    private  Boolean  result;
    private  int status;  //错误信息状态码  0 成功 1错误 2未验证失败 3 未激活

    public LoginMessage(String message, Boolean result) {
        this.message = message;
        this.result = result;
    }

    public LoginMessage(String message, Boolean result, int status) {
        this.message = message;
        this.result = result;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
