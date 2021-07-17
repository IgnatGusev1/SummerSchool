package ru.gusev.models;

public class Values {
     public double temperature;
     public int humidity;
     public double pressure;

     public Values(double temperature, int humidity, double pressure) {
          this.temperature = temperature;
          this.humidity = humidity;
          this.pressure = pressure;
     }

     public double getTemperature() {
          return temperature;
     }

     public void setTemperature(double temperature) {
          this.temperature = temperature;
     }

     public int getHumidity() {
          return humidity;
     }

     public void setHumidity(int humidity) {
          this.humidity = humidity;
     }

     public double getPressure() {
          return pressure;
     }

     public void setPressure(double pressure) {
          this.pressure = pressure;
     }
}
