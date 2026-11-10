package com.reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:reservation.db";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL);
            System.out.println("Database connected successfully!");
            return connection;

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }

    public static void createReservationTable() {

        String sql = "CREATE TABLE IF NOT EXISTS reservations ("
                + "pnr TEXT PRIMARY KEY, "
                + "passenger_name TEXT NOT NULL, "
                + "train_number TEXT NOT NULL, "
                + "train_name TEXT NOT NULL, "
                + "journey_date TEXT NOT NULL, "
                + "from_station TEXT NOT NULL, "
                + "to_station TEXT NOT NULL, "
                + "class_type TEXT NOT NULL, "
                + "status TEXT DEFAULT 'BOOKED'"
                + ")";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
            System.out.println("Reservation table created successfully!");

        } catch (SQLException e) {
            System.out.println("Table creation failed!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createReservationTable();
    }
}