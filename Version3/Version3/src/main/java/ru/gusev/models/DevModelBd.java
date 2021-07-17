package ru.gusev.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class DevModelBd {
    private int ts;
    private float temperature;
    private float humidity;
    private float pressure;

    public DevModelBd(int ts, float temperature, float humidity, float pressure) {
        this.ts = ts;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public DevModelBd() {
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }
}
