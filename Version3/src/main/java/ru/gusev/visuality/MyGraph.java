package ru.gusev.visuality;

import javax.swing.*;
import java.awt.*;

class MyGraph extends JFrame {
    public int x[];
    public Integer temperature[];
    public Integer pressure[];
    public Integer humidity[];

    public MyGraph (Integer[] temperature, Integer[] pressure, Integer[] humidity) {
        super("Temperature Pressure Humidity");
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        JPanel jcp = new JPanel(new BorderLayout());
        setContentPane(jcp);
        jcp.add(new Drawing(this.temperature, this.pressure, this.humidity), BorderLayout.CENTER);
        jcp.setBackground(Color.WHITE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
