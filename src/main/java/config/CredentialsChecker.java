package config;

import com.google.auth.oauth2.GoogleCredentials;
import java.io.FileInputStream;
import java.io.IOException;

public class CredentialsChecker {
    public static void main(String[] args) {
        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:/Users/theja/Downloads/cineaste-430108-4b4cd3198710.json"));
            System.out.println("Credentials loaded successfully: " + credentials);
        } catch (IOException e) {
            System.err.println("Failed to load credentials: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
