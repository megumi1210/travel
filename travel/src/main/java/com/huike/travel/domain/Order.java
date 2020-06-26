package com.huike.travel.domain;

public enum Order {
    DESC("desc"),
    ASC("asc");

    private String name;

    private Order(String name){
        this.name = name;
    }

    public String getName(){
        return  name;
    }
}
