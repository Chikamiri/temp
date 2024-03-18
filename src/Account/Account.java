package Account;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Account {
    private String user_data;
    private String pwd_data;
    private byte[] salt;

    private static String FilePath="D:\\KTPM\\temp\\src\\Account\\Accounts_Data.txt";


    public Account(String username, String password, byte[] salt){
        this.user_data=username;
        this.pwd_data=password;
        this.salt=salt;
    }
    public String getUsername(){
        return user_data;
    }
    public String getPassword(){
        return pwd_data;
    }
    public byte[] getSalt(){
        return salt;
    }

    public class PwdUtil{
        public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPwd = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPwd);
        }
        public static byte[] generateSalt(){
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            return salt;
        }
    }
    public class Auth{
        public static void registerUser(String username, String password) throws NoSuchAlgorithmException{
            byte[] salt=PwdUtil.generateSalt();
            String hashedPwd=PwdUtil.hashPassword(password, salt);

            Account.Output.toFile(FilePath, username, hashedPwd, salt);
        }

        public static boolean authUser(String username, String password)throws NoSuchAlgorithmException{
            try(FileInputStream inputFile=new FileInputStream(FilePath);
                ObjectInputStream tempInput=new ObjectInputStream(inputFile)){

                while(true){
                    try{
                        String savedUsername=(String)tempInput.readObject();
                        String savedHashedPwd=(String)tempInput.readObject();
                        byte[] savedSalt=(byte[])tempInput.readObject();

                        if(savedUsername.equals(username)){
                            String hashedPwd=PwdUtil.hashPassword(password, savedSalt);
                            return savedHashedPwd.equals(hashedPwd);
                        }

                    }
                    catch(EOFException e){break;}
                    catch(ClassNotFoundException e){e.printStackTrace();}
                }

            }catch(IOException e){e.printStackTrace();}
            return false;
        }
    }

    public class Output{
        public static void toFile(String FileName, String username, String hashedPwd, byte[] salt){
            try(FileOutputStream outputFile=new FileOutputStream(FileName,true);
                ObjectOutputStream tempOutput=new ObjectOutputStream(
                        new BufferedOutputStream(outputFile))){

                tempOutput.writeObject(username);
                tempOutput.writeObject(hashedPwd);
                tempOutput.writeObject(salt);

            }catch(IOException e){e.printStackTrace();}
        }
    }

    /*
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Auth.registerUser("admin","12345");
        System.out.println(Auth.authUser("admin", "12313"));
        System.out.println(Auth.authUser("admin", "12345"));

    }
    */
}
