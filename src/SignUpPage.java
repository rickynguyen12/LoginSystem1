import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.HashMap;
import java.lang.String;
import java.util.Random;

public class SignUpPage implements ActionListener {

    JFrame frame = new JFrame();
    JLabel userFirstNameLabel = new JLabel("First Name:");
    JLabel userLastNameLabel = new JLabel("Last Name: ");
    JLabel userPasswordLabel = new JLabel("Password: ");
    JLabel userEmailLabel = new JLabel("Email: ");
    JLabel messageLabel = new JLabel("");
    JLabel titleLabel = new JLabel("Sign Up Page");
    JButton signUpButton = new JButton("Sign Up");
    JButton backToLoginButton = new JButton("Back to login");

    JTextField userFirstNameField = new JTextField();
    JTextField userLastNameField = new JTextField();
    JTextField userEmailField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    FileWriter fileWriter;
    HashMap<String,String> signup = new HashMap<String,String>();

    public SignUpPage(HashMap<String, String> signUpInfo) {
        signup = signUpInfo;

        userFirstNameLabel.setBounds(50,100,75,25);
        userLastNameLabel.setBounds(50,150,75,25);
        userPasswordLabel.setBounds(50,200,75,25);
        userEmailLabel.setBounds(50,250,75,25);

        userFirstNameField.setBounds(125,100,200,25);
        userLastNameField.setBounds(125,150,200,25);
        userPasswordField.setBounds(125,200,200,25);
        userEmailField.setBounds(125,250,200,25);

        messageLabel.setBounds(50, 280, 300, 25);
        messageLabel.setFont(new Font(null,Font.ITALIC,15));

        titleLabel.setBounds(100,-20,250,150);
        titleLabel.setFont(new Font(null,Font.PLAIN,30));

        backToLoginButton.setBounds(75,310,125,25);
        backToLoginButton.addActionListener(this);
        backToLoginButton.setFocusable(false);


        signUpButton.setBounds(225,310,125,25);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener( this);

        frame.add(userFirstNameLabel);
        frame.add(userFirstNameField);
        frame.add(userLastNameLabel);
        frame.add(userLastNameField);
        frame.add(userEmailLabel);
        frame.add(userEmailField);
        frame.add(userPasswordLabel);
        frame.add(userPasswordField);
        frame.add(signUpButton);
        frame.add(backToLoginButton);
        frame.add(messageLabel);
        frame.add(titleLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public String generateUsername(String firstName, String lastName) {
        // Get first letters of firstName and lastName
        String firstInitial = firstName.substring(0, 1).toUpperCase();
        String secondInitial = lastName.substring(0, 1).toUpperCase();

        // Generate 4 random numbers
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;

        // Combine the letters and numbers to form the username
        String username = firstInitial + secondInitial + "-" + randomNumber;

        return username;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            String firstName = userFirstNameField.getText();
            String lastName = userLastNameField.getText();
            String password = new String(userPasswordField.getPassword());
            String email = userEmailField.getText();
            String username = generateUsername(firstName, lastName);


            String[] userData = {firstName, lastName, password, email};

            String errorMessage = "";

            if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || email.isEmpty()) {
                errorMessage = "Please fill in all fields.";
            } else {
                try {
                    if (!password.matches(".*[A-Z].*")) {
                        throw new PasswordException.UpperCaseCharacterMissing("Missing uppercase letter in password.");
                    }

                    if (!password.matches(".*[a-z].*")) {
                        throw new PasswordException.LowerCaseCharacterMissing("Missing lowercase letter in password.");
                    }

                    if (!password.matches(".*[!@#$%^&*()_+=\\[\\]{}|:;\"<>,.?/~`].*")) {
                        throw new PasswordException.SpecialCharacterMissing("Missing Special Character in password.");
                    }

                    if (!password.matches(".*\\d.*")) {
                        throw new PasswordException.NumberCharacterMissing("Missing number in password.");
                    }

                    if (password.length() < 8) {
                        throw new PasswordException.Minimum8CharactersRequired("Needs more than 8 characters.");
                    }

                    FileWriter fileWriter = new FileWriter("user_data.txt", true);
                    String userDataString = username + ":" + password + ":" + firstName + " " + lastName + ":" + email + "\n";
                    fileWriter.write(userDataString);
                    fileWriter.close();

                    // Navigate back to login page after successful sign up
                    HashMap<String,String> loginInfo = new HashMap<String,String>();
                    LoginPage loginPage = new LoginPage(loginInfo);
                    frame.dispose();

                } catch (PasswordException ex) {
                    errorMessage = ex.getMessage();
                    ex.printStackTrace();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex + "");
                }
            }

            if (!errorMessage.isEmpty()) {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText(errorMessage);
            }
        }
        if(e.getSource() == backToLoginButton){
            LoginPage loginPage = new LoginPage(new HashMap<>());
            frame.dispose();
        }
    }


}
