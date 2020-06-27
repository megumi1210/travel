package com.huike.travel.domain;

//登录的状态校验
public enum Status {
    SUCCESS(0),
    ERROR(1),
    VALID_WRONG(2),
    NOT_ACTIVE(3);

    private int  code ;

      Status(int code){
        this.code = code;
    }


    public int getCode() {
        return code;
    }
}
