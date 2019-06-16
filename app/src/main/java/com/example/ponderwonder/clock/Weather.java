package com.example.ponderwonder.clock;

public class Weather {
    private String weatherCity;
    private String weatherCountry;
    private String weatherTemp;
    private String weatherState;
    private String weatherDescription;

    public Weather(String city, String country, String temp, String state, String description) {
        this.weatherCity = city;
        this.weatherCountry = country;
        this.weatherTemp = temp;
        this.weatherState = state;
        this.weatherDescription = description;
    }

    public String getWeatherCity() {
        return weatherCity;
    }

    public void setWeatherCity(String city) {
        this.weatherCity = city;
    }

    public String getWeatherCountry() {
        return weatherCountry;
    }

    public void setWeatherCountry(String country) {
        this.weatherCountry = country;
    }

    public String getWeatherTemp() { return weatherTemp; }

    public void setWeatherTemp(String temp) {
        this.weatherTemp = temp;
    }

    public String getWeatherState() { return weatherState; }

    public void setWeatherState(String state) {
        this.weatherState = state;
    }

    public String getWeatherDescription() { return weatherDescription; }

    public void setWeatherDescription(String description) {
        this.weatherDescription = description;
    }
}
