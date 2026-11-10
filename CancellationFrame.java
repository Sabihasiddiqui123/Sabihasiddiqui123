package com.reservation;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class CancellationFrame extends JFrame {

    private JTextField pnrField;

    public CancellationFrame() {

        setTitle("Cancel Train Ticket");
        setSize(450, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("CANCEL YOUR TICKET");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(25, 80, 160));

        JLabel label = new JLabel("Enter your PNR Number:");

        pnrField = new JTextField();

        JButton cancelButton = new JButton("CANCEL TICKET");
        cancelButton.setBackground(new Color(190, 40, 40));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 15));

        panel.add(title);
        panel.add(label);
        panel.add(pnrField);
        panel.add(cancelButton);

        cancelButton.addActionListener(e -> cancelTicket());

        add(panel);
        setVisible(true);
    }

    private void cancelTicket() {

        String pnr = pnrField.getText().trim();

        if (pnr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter PNR!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to cancel this ticket?",
            "Confirm Cancellation",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "UPDATE reservations SET status = 'CANCELLED' "
                   + "WHERE pnr = ? AND status = 'BOOKED'";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pnr);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(
                    this, "Ticket cancelled successfully!"
                );
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                    this, "Invalid PNR or ticket already cancelled!"
                );
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                this, "Cancellation failed: " + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CancellationFrame());
    }
}
