import java.awt.*;
import javax.swing.*;

public class Login {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Online Reservation System");

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("User Login");
        title.setBounds(145, 20, 150, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 80, 100, 25);
        panel.add(userLabel);

        JTextField username = new JTextField();
        username.setBounds(150, 80, 180, 25);
        panel.add(username);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 120, 100, 25);
        panel.add(passLabel);

        JPasswordField password = new JPasswordField();
        password.setBounds(150, 120, 180, 25);
        panel.add(password);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(145, 180, 100, 30);
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String user = username.getText();
            String pass = new String(password.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password!");
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}