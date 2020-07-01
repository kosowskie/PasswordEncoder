package pl.kosowski.lab1;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFunction implements PasswordEncoder {

    public HashFunction() {
    }

    @Override
    public String encode(CharSequence rawPassword) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(rawPassword.toString().getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return generatedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(rawPassword.toString().getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        if(MessageDigest.isEqual(generatedPassword.getBytes(StandardCharsets.UTF_8),encodedPassword.getBytes(StandardCharsets.UTF_8)))
        {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
