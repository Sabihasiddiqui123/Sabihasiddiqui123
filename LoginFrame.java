package com.reservation;

import java.awt.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {

        setTitle("Online Reservation System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        JLabel title = new JLabel("ONLINE RESERVATION");
        title.setBounds(100, 35, 300, 35);
        title.setFont(new Font("Arial", Font.BOLD, 23));
        title.setForeground(new Color(25, 80, 160));
        panel.add(title);

        JLabel subtitle = new JLabel("Login to your account");
        subtitle.setBounds(165, 80, 200, 25);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(subtitle);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(80, 135, 120, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(80, 160, 330, 35);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(80, 210, 120, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(80, 235, 330, 35);
        panel.add(passwordField);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(80, 295, 330, 40);
        loginButton.setBackground(new Color(25, 80, 160));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        panel.add(loginButton);

        loginButton.addActionListener(e -> login());

        add(panel);
        setVisible(true);
    }

    private void login() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

       if (username.equals("admin") && password.equals("1234")) {

    JOptionPane.showMessageDialog(
        this,
        "Login Successful! Welcome."
    );

    dispose();

    new Dashboard();

} else {

            JOptionPane.showMessageDialog(
                this,
                "Invalid Username or Password!"
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}