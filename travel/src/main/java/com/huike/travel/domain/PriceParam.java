package com.huike.travel.domain;

import java.util.Objects;

public class PriceParam {

    private  double start;

    private double end;


    public  PriceParam(){

    }

    public PriceParam(double start, double end) {
        this.start = start > 0 ? start : 0;
        this.end = end > 0 ? end: 0;
        reset();
    }


    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start > 0 ? start : 0;
        reset();
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end >0? end : 0;
        reset();
    }


    private  void reset(){
        if(this.end < this.start){
            double tmp = start;
            start = end;
            end = tmp;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceParam that = (PriceParam) o;
        return Double.compare(that.start, start) == 0 &&
                Double.compare(that.end, end) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
