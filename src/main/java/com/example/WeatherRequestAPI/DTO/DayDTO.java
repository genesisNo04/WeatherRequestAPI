package com.example.WeatherRequestAPI.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DayDTO {

    private String datetime;
    private double tempmax;
    private double tempmin;
    private double temp;
    private String conditions;
    private List<HourDTO> hourDTOS;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public double getTempmax() {
        return tempmax;
    }

    public void setTempmax(double tempmax) {
        this.tempmax = tempmax;
    }

    public double getTempmin() {
        return tempmin;
    }

    public void setTempmin(double tempmin) {
        this.tempmin = tempmin;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public List<HourDTO> getHours() {
        return hourDTOS;
    }

    public void setHours(List<HourDTO> hourDTOS) {
        this.hourDTOS = hourDTOS;
    }
}
