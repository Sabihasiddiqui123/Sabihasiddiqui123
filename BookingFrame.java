package com.reservation;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import javax.swing.*;

public class BookingFrame extends JFrame {

    private JTextField nameField, trainNumberField, trainNameField;
    private JTextField dateField, fromField, toField;
    private JComboBox<String> classBox;

    public BookingFrame() {

        setTitle("Book Train Ticket");
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        panel.setBackground(Color.WHITE);

        JLabel heading = new JLabel("BOOK YOUR TICKET");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setForeground(new Color(25, 80, 160));

        panel.add(heading);
        panel.add(new JLabel(""));

        nameField = addField(panel, "Passenger Name:");
        trainNumberField = addField(panel, "Train Number:");
        trainNameField = addField(panel, "Train Name:");
        dateField = addField(panel, "Journey Date (YYYY-MM-DD):");
        fromField = addField(panel, "From Station:");
        toField = addField(panel, "To Station:");

        panel.add(new JLabel("Class:"));

        String[] classes = {"Sleeper", "3AC", "2AC", "1AC", "Chair Car"};
        classBox = new JComboBox<>(classes);
        panel.add(classBox);

        JButton bookButton = new JButton("BOOK TICKET");
        bookButton.setBackground(new Color(25, 80, 160));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("Arial", Font.BOLD, 15));

        panel.add(new JLabel(""));
        panel.add(bookButton);

        bookButton.addActionListener(e -> bookTicket());

        add(panel);
        setVisible(true);
    }

    private JTextField addField(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private void bookTicket() {

        String name = nameField.getText().trim();
        String trainNumber = trainNumberField.getText().trim();
        String trainName = trainNameField.getText().trim();
        String date = dateField.getText().trim();
        String from = fromField.getText().trim();
        String to = toField.getText().trim();
        String travelClass = (String) classBox.getSelectedItem();

        if (name.isEmpty() || trainNumber.isEmpty()
                || trainName.isEmpty() || date.isEmpty()
                || from.isEmpty() || to.isEmpty()) {

            JOptionPane.showMessageDialog(
                this, "Please fill all the fields!"
            );
            return;
        }

        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(
                this, "Enter date in YYYY-MM-DD format!"
            );
            return;
        }

        String pnr = UUID.randomUUID()
                .toString().substring(0, 8).toUpperCase();

        String sql = "INSERT INTO reservations "
                + "(pnr, passenger_name, train_number, train_name, "
                + "journey_date, from_station, to_station, class_type) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            if (connection == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed!");
                return;
            }

            statement.setString(1, pnr);
            statement.setString(2, name);
            statement.setString(3, trainNumber);
            statement.setString(4, trainName);
            statement.setString(5, date);
            statement.setString(6, from);
            statement.setString(7, to);
            statement.setString(8, travelClass);

            statement.executeUpdate();

            JOptionPane.showMessageDialog(
                this,
                "Ticket booked successfully!\nYour PNR: " + pnr
            );

            dispose();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                this, "Booking failed: " + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookingFrame());
    }
}
