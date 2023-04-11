package com.ha.ltschat.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Md5Util {
    public static String encode(String input) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] result = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                String hex = Integer.toHexString(b & 0xff);
                if (hex.length() == 1) {
                    sb.append("0");
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException(e);
        }
    }
}
