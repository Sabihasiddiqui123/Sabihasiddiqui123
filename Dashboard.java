package com.reservation;

import java.awt.*;
import javax.swing.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Online Reservation System - Dashboard");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        JLabel title = new JLabel("ONLINE RESERVATION SYSTEM");
        title.setBounds(120, 35, 450, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(25, 80, 160));
        panel.add(title);

        JLabel subtitle = new JLabel("Welcome to Railway Reservation");
        subtitle.setBounds(200, 85, 300, 25);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(subtitle);

        JButton bookButton = new JButton("BOOK TICKET");
        bookButton.setBounds(180, 145, 280, 50);
        styleButton(bookButton);
        panel.add(bookButton);

        JButton searchButton = new JButton("SEARCH RESERVATION");
        searchButton.setBounds(180, 215, 280, 50);
        styleButton(searchButton);
        panel.add(searchButton);

        JButton cancelButton = new JButton("CANCEL TICKET");
        cancelButton.setBounds(180, 285, 280, 50);
        styleButton(cancelButton);
        panel.add(cancelButton);

        bookButton.addActionListener(e -> new BookingFrame());

        searchButton.addActionListener(e -> new SearchFrame());

        cancelButton.addActionListener(e -> new CancellationFrame());

        add(panel);
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(25, 80, 160));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard());
    }
}
