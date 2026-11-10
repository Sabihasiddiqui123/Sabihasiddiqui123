package com.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cancellation {

    public static void cancelTicket(String pnr) {

        String sql = "UPDATE reservations SET status = 'CANCELLED' "
                   + "WHERE pnr = ? AND status = 'BOOKED'";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pnr);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Ticket cancelled successfully!");
            } else {
                System.out.println("Invalid PNR or ticket already cancelled.");
            }

        } catch (SQLException e) {
            System.out.println("Cancellation failed!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        cancelTicket("734179DA");

    }
}
