import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;


public class LoginPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginbutton = new JButton("Login");
    JButton signUpButton = new JButton("Sign Up");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("User ID: ");
    JLabel userPasswordLabel = new JLabel("Password: ");
    JLabel messageLabel = new JLabel();
    JLabel titleLabel = new JLabel("Friends for you!");
    HashMap<String, String> logininfo;

    LoginPage(HashMap<String, String> logininfo) {
        this.logininfo = logininfo;
        readUserFile();

        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);

        userIDField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);

        titleLabel.setBounds(100, -20, 250, 150);
        titleLabel.setFont(new Font(null, Font.PLAIN, 30));

        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.BOLD, 20));

        loginbutton.setBounds(125, 200, 100, 25);
        loginbutton.addActionListener(this);

        signUpButton.setBounds(225, 200, 100, 25);
        signUpButton.addActionListener(this);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginbutton);
        frame.add(signUpButton);
        frame.add(messageLabel);
        frame.add(titleLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    private void readUserFile() {
        File file = new File("user_data.txt");
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length >= 2) {
                        logininfo.put(parts[0].trim(), parts[1].trim());
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginbutton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());
            String name = null;
            String email = null;

            if (!userID.isEmpty() && !password.isEmpty()) {
                if (logininfo.containsKey(userID)) {
                    if (logininfo.get(userID).equals(password)) {
                        // Read user data from file
                        try {
                            BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                String[] parts = line.split(":");
                                if (parts[0].equals(userID)) {
                                    name = parts[2];
                                    email = parts[3];
                                    break;
                                }
                            }
                            reader.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        messageLabel.setForeground(Color.green);
                        messageLabel.setText("Login successful!");
                        WelcomePage welcomePage = new WelcomePage(userID,password,name, email);
                        frame.dispose();

                    } else {
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("Wrong Password.");
                    }
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Username Not Found.");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Please fill in all fields.");
            }
        }



        if (e.getSource() == signUpButton) {
            frame.dispose();
            SignUpPage signup = new SignUpPage(logininfo);
        }
    }


}