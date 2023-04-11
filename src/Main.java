import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {

        // Create a HashMap to store login information
        HashMap<String, String> loginInfo = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line by colon
                String[] parts = line.split(":");

                if (parts.length == 2) { // Check if the line has both username and password
                    loginInfo.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Create a LoginPage object with the login information
        LoginPage loginPage = new LoginPage(loginInfo);
    }
}