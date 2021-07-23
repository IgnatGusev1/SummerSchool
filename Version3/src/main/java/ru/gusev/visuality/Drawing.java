package ru.gusev.visuality;

import javax.swing.*;
import java.awt.*;

class Drawing extends JPanel {

    int X[];
    int temperatureY[];
    int pressureY[];
    int humidityY[];

    public Drawing(Integer[] temperature, Integer[] pressure, Integer[] humidity) {
        temperatureY = new int[temperature.length];
        for (int i = 0; i < temperatureY.length; i++) {
            temperatureY[i] = 175 - (temperature[i] - Minimum(temperature)) * 150/(Maximum(temperature)-Minimum(temperature));
        }
        pressureY = new int[pressure.length];
        for (int i = 0; i < pressureY.length; i++) {
            pressureY[i] = 355 - (pressure[i] - Minimum(pressure)) * 150/(Maximum(pressure)-Minimum(pressure));
        }
        humidityY = new int[humidity.length];
        for (int i = 0; i < humidityY.length; i++) {
            humidityY[i] = 535 - (humidity[i] - Minimum(humidity)) * 150/(Maximum(humidity)-Minimum(humidity));
        }
        X = new int[temperature.length];
        for(int i = 0;i<X.length;i++){
            X[i] = i * 10 + 30;
        }
    }
    public int Minimum(Integer[] a){
        int x = a[0];
        for(int i = 0; i< a.length;i++){
            if(x>a[i]) x=a[i];
        }
        return x;
    }
    public int Maximum(Integer[] a){
        int x = a[0];
        for(int i = 0; i< a.length;i++){
            if(x<a[i]) x=a[i];
        }
        return x;
    }

    @Override
    protected void paintComponent(Graphics gh) {
        Graphics2D drp = (Graphics2D)gh;
        drp.drawLine(20, 180, 20, 20);
        drp.drawLine(20, 360, 20, 200);
        drp.drawLine(20, 540, 20, 380);
        drp.drawLine(20, 180, 600, 180);
        drp.drawLine(20, 360, 600, 360);
        drp.drawLine(20, 540, 600, 540);
        drp.drawPolyline(X, temperatureY, X.length);
        drp.drawPolyline(X, pressureY, X.length);
        drp.drawPolyline(X, humidityY, X.length);
    }
}




