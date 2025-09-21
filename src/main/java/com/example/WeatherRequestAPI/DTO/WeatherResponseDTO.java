package com.example.WeatherRequestAPI.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponseDTO {
    private String resolvedAddress;
    private String address;
    private String timezone;
    private List<DayDTO> dayDTOS;

    public String getResolvedAddress() {
        return resolvedAddress;
    }

    public void setResolvedAddress(String resolvedAddress) {
        this.resolvedAddress = resolvedAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public List<DayDTO> getDays() {
        return dayDTOS;
    }

    public void setDays(List<DayDTO> dayDTOS) {
        this.dayDTOS = dayDTOS;
    }
}
