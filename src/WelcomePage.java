import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class WelcomePage {

    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel("Welcome to Friends for You!");
    JLabel userIDLabel = new JLabel();
    JLabel userPasswordLabel = new JLabel();
    JLabel userEmailLabel = new JLabel();
    JLabel userNameLabel = new JLabel();
    JButton logoutButton = new JButton("Logout");

    WelcomePage(String userID, String password, String name, String email) {
        frame.getContentPane().setBackground(Color.PINK);

        userNameLabel.setText("Name: " + name);
        userIDLabel.setText("User ID: " + userID);
        userPasswordLabel.setText("Password: " + password);
        userEmailLabel.setText("Email: " + email);

        welcomeLabel.setBounds(50, 50, 300, 50);
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 20));

        userNameLabel.setBounds(50, 150, 300, 25);
        userEmailLabel.setBounds(50, 175, 300, 25);
        userIDLabel.setBounds(50, 100, 300, 25);
        userPasswordLabel.setBounds(50, 125, 300, 25);

        logoutButton.setBounds(50, 225, 300, 25);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage(new HashMap<>());
                frame.dispose();
            }
        });

        frame.add(welcomeLabel);
        frame.add(userNameLabel);
        frame.add(userEmailLabel);
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(logoutButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}





