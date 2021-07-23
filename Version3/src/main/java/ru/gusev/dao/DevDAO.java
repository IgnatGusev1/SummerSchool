package ru.gusev.dao;

import org.springframework.stereotype.Component;
import ru.gusev.models.Dev1;
import ru.gusev.models.DevModelBd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DevDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/Ts";
    private static final String username = "postgres";
    private static final String password = "89202508117";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void rec(int ts, double pressure, double temperature, double humidity) throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "INSERT INTO dev VALUES("+ts+",'" + pressure + "'," + temperature + ",'"
                + humidity + "')";
        statement.executeUpdate(SQL);
    }

    public List<DevModelBd> index() throws SQLException {
        List<DevModelBd> devDAO = new ArrayList<>();

        Statement statement = connection.createStatement();
        String SQL = "SELECT * FROM dev";

        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next()){
            DevModelBd devModelBd = new DevModelBd();

            devModelBd.setTs(resultSet.getInt("ts"));
            devModelBd.setTemperature(resultSet.getInt("temperature"));
            devModelBd.setHumidity(resultSet.getInt("humidity"));
            devModelBd.setPressure(resultSet.getInt("pressure"));

            devDAO.add(devModelBd);
        }
        return devDAO;

    }

    public static Integer [] ArrayFromSQL() throws SQLException {
        List<Integer> array = new ArrayList<>();
        Statement statement = connection.createStatement();
        String SQL = "SELECT temerature * FROM dev";
        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next()){

            array.add(resultSet.getInt("temperature"));
        }
        return array.toArray(new Integer[array.size()]);


    }

    public void delete(){

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM dev");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }





}
