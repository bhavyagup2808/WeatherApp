package com.weather.values;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthData {
	@JsonProperty("January")
    private int january;
    
    @JsonProperty("February")
    private int february;
    
    @JsonProperty("March")
    private int march;
    
    @JsonProperty("April")
    private int april;
    
    @JsonProperty("May")
    private int may;
    
    @JsonProperty("June")
    private int june;
    
    @JsonProperty("July")
    private int july;
    
    @JsonProperty("August")
    private int august;
    
    @JsonProperty("September")
    private int september;
    
    @JsonProperty("October")
    private int october;
    
    @JsonProperty("November")
    private int november;
    
    @JsonProperty("December")
    private int december;
    
    // Getters and setters for each field

    public int getJanuary() {
        return january;
    }

    public void setJanuary(int january) {
        this.january = january;
    }

    public int getFebruary() {
        return february;
    }

    public void setFebruary(int february) {
        this.february = february;
    }

    public int getMarch() {
        return march;
    }

    public void setMarch(int march) {
        this.march = march;
    }

    public int getApril() {
        return april;
    }

    public void setApril(int april) {
        this.april = april;
    }

    public int getMay() {
        return may;
    }

    public void setMay(int may) {
        this.may = may;
    }

    public int getJune() {
        return june;
    }

    public void setJune(int june) {
        this.june = june;
    }

    public int getJuly() {
        return july;
    }

    public void setJuly(int july) {
        this.july = july;
    }

    public int getAugust() {
        return august;
    }

    public void setAugust(int august) {
        this.august = august;
    }

    public int getSeptember() {
        return september;
    }

    public void setSeptember(int september) {
        this.september = september;
    }

    public int getOctober() {
        return october;
    }

    public void setOctober(int october) {
        this.october = october;
    }

    public int getNovember() {
        return november;
    }

    public void setNovember(int november) {
        this.november = november;
    }

    public int getDecember() {
        return december;
    }

    public void setDecember(int december) {
        this.december = december;
    }
}

