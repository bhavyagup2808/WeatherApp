package com.weather;

public class CityDetails {
	private String city;
	private double temperature;
	private double weather;
	private double highest;
	private double lowest;
	private double avgWinterTemp;
	private double avgSummerTemp;
	private double avgCustomtemp;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getWeather() {
		return weather;
	}
	public void setWeather(double weather) {
		this.weather = weather;
	}
	public double getHighest() {
		return highest;
	}
	public void setHighest(double highest) {
		this.highest = highest;
	}
	public double getLowest() {
		return lowest;
	}
	public void setLowest(double lowest) {
		this.lowest = lowest;
	}
	public double getAvgWinterTemp() {
		return avgWinterTemp;
	}
	public void setAvgWinterTemp(double avgWinterTemp) {
		this.avgWinterTemp = avgWinterTemp;
	}
	public double getAvgSummerTemp() {
		return avgSummerTemp;
	}
	public void setAvgSummerTemp(double avgSummerTemp) {
		this.avgSummerTemp = avgSummerTemp;
	}
	public double getAvgCustomtemp() {
		return avgCustomtemp;
	}
	public void setAvgCustomtemp(double avgCustomtemp) {
		this.avgCustomtemp = avgCustomtemp;
	}
}
