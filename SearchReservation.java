package com.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchReservation {

    public static void searchTicket(String pnr) {

        String sql = "SELECT * FROM reservations WHERE pnr = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pnr);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                System.out.println("\n----- RESERVATION DETAILS -----");

                System.out.println("PNR: " + result.getString("pnr"));
                System.out.println("Passenger: " + result.getString("passenger_name"));
                System.out.println("Train: " + result.getString("train_name"));
                System.out.println("Train Number: " + result.getString("train_number"));
                System.out.println("Journey Date: " + result.getString("journey_date"));
                System.out.println("From: " + result.getString("from_station"));
                System.out.println("To: " + result.getString("to_station"));
                System.out.println("Class: " + result.getString("class_type"));
                System.out.println("Status: " + result.getString("status"));

            } else {
                System.out.println("No reservation found for this PNR.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        searchTicket("734179DA");

    }
}