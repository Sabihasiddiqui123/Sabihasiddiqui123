package com.reservation;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class SearchFrame extends JFrame {

    private JTextField pnrField;
    private JTextArea resultArea;

    public SearchFrame() {

        setTitle("Search Reservation");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("SEARCH RESERVATION");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(25, 80, 160));

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.WHITE);

        pnrField = new JTextField(15);
        JButton searchButton = new JButton("SEARCH");

        searchButton.setBackground(new Color(25, 80, 160));
        searchButton.setForeground(Color.WHITE);

        topPanel.add(new JLabel("Enter PNR:"));
        topPanel.add(pnrField);
        topPanel.add(searchButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        panel.add(title, BorderLayout.NORTH);
        panel.add(topPanel, BorderLayout.CENTER);
        panel.add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchTicket());

        add(panel);
        setVisible(true);
    }

    private void searchTicket() {

        String pnr = pnrField.getText().trim();

        if (pnr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter PNR!");
            return;
        }

        String sql = "SELECT * FROM reservations WHERE pnr = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pnr);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                resultArea.setText(
                    "----- RESERVATION DETAILS -----\n\n"
                    + "PNR: " + result.getString("pnr") + "\n"
                    + "Passenger: " + result.getString("passenger_name") + "\n"
                    + "Train: " + result.getString("train_name") + "\n"
                    + "Train Number: " + result.getString("train_number") + "\n"
                    + "Journey Date: " + result.getString("journey_date") + "\n"
                    + "From: " + result.getString("from_station") + "\n"
                    + "To: " + result.getString("to_station") + "\n"
                    + "Class: " + result.getString("class_type") + "\n"
                    + "Status: " + result.getString("status")
                );

            } else {
                resultArea.setText("No reservation found!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Search failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchFrame());
    }
}
