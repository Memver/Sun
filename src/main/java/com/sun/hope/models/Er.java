package com.sun.hope.models;

public class Er {
    private String errorMessage;

    public Er() {
    }

    public Er(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public boolean isEmpty(String str){
        return str.replaceAll(" ", "").equals("");
    }
    public boolean lessThanZero(int number){
        return number <=0;
    }
    public boolean lessThanZero(Long number){
        return number <=0;
    }
    public boolean targetCondition(String str){
        return (str.equals("sell") || str.equals("buy"));
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Er obj = (Er)o;
        return this.getErrorMessage().equals(obj.getErrorMessage());
    }
}
