package Account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Account {
    private String user_data;
    private String pwd_data;
    private byte[] salt;

    private static String FilePath = "D:\\KTPM\\temp\\src\\Account\\Accounts_Data.txt";

    public Account(String username, String password, byte[] salt) {
        this.user_data = username;
        this.pwd_data = password;
        this.salt = salt;
    }

    public String getUsername() {
        return user_data;
    }

    public String getPassword() {
        return pwd_data;
    }

    public byte[] getSalt() {
        return salt;
    }

    public class PwdUtil {
        public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPwd = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPwd);
        }

        public static byte[] generateSalt() {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            return salt;
        }
    }

    public class Auth {
        private static Map<String, String> cache = new HashMap<>();

        public static void registerUser(String username, String password) throws NoSuchAlgorithmException {
            byte[] salt = PwdUtil.generateSalt();
            String hashedPwd = PwdUtil.hashPassword(password, salt);

            Account.toFile(FilePath, username, hashedPwd, salt);
            cache.put(username, hashedPwd);
        }

        public static boolean auth_register(String username, String password) throws NoSuchAlgorithmException {
            try (BufferedReader inputFile = new BufferedReader(new FileReader(FilePath))) {
                String line;
                while ((line = inputFile.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length >= 1) {
                        String savedUsername = parts[0];
                        if (savedUsername.equals(username)) {
                            System.out.println("Username already exists!");
                            return false;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        public static boolean authUser(String username, String password) throws NoSuchAlgorithmException {

            try (BufferedReader inputFile = new BufferedReader(new FileReader(FilePath))) {
                String line;
                while ((line = inputFile.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 3) {
                        String savedUsername = parts[0];
                        String savedHashedPwd = parts[1];
                        byte[] savedSalt = Base64.getDecoder().decode(parts[2]);

                        if (savedUsername.equals(username)) {
                            String hashedPwd = PwdUtil.hashPassword(password, savedSalt);
                            return savedHashedPwd.equals(hashedPwd);
                        }
                    }
                }
            } catch (IOException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public static void toFile(String FileName, String username, String hashedPwd, byte[] salt) {
        try (BufferedWriter tempOutput = new BufferedWriter(new FileWriter(FilePath, true))) {

            tempOutput.write(username + ":" + hashedPwd + ":" + Base64.getEncoder().encodeToString(salt));
            tempOutput.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Auth.registerUser("admin", "12345");
        System.out.println(Auth.authUser("admin", "12313"));
        System.out.println(Auth.authUser("admin", "12345"));

    }

}
