package com.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Reservation {

    public static void bookTicket(
            String passengerName,
            String trainNumber,
            String trainName,
            String journeyDate,
            String fromStation,
            String toStation,
            String classType) {

        String pnr = UUID.randomUUID()
                .toString().substring(0, 8).toUpperCase();

        String sql = "INSERT INTO reservations "
                + "(pnr, passenger_name, train_number, train_name, "
                + "journey_date, from_station, to_station, class_type) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pnr);
            statement.setString(2, passengerName);
            statement.setString(3, trainNumber);
            statement.setString(4, trainName);
            statement.setString(5, journeyDate);
            statement.setString(6, fromStation);
            statement.setString(7, toStation);
            statement.setString(8, classType);

            statement.executeUpdate();

            System.out.println("Ticket booked successfully!");
            System.out.println("Your PNR Number: " + pnr);

        } catch (SQLException e) {
            System.out.println("Booking failed!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        bookTicket(
                "Sabiha",
                "12951",
                "Mumbai Rajdhani",
                "2026-12-15",
                "Mumbai Central",
                "New Delhi",
                "3AC"
        );
    }
}
