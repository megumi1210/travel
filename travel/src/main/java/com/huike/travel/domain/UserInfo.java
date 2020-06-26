package com.huike.travel.domain;

public class UserInfo {

    private  String username;

    private  int  uid;

    public UserInfo(String username, int uid) {
        this.username = username;
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", uid=" + uid +
                '}';
    }
}
