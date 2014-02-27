package com.company.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/18/13
 */
public class Hasher {
    private static final String MD5 = "MD5";
    private static final String encoding = "UTF-8";

    public static String hash(String input, String algorithm){
        if(MD5.equals(algorithm)){
            return hashMD5(input);
        } else return hashMD5(input);

    }

    private static String hashMD5(String input) {
        byte[] bytesOfMessage;
        MessageDigest messageDigest;
        byte[] digest;
        String hashText = "";

        try {
            messageDigest = MessageDigest.getInstance(MD5);
            messageDigest.reset();
            bytesOfMessage = input.getBytes();
            messageDigest.update(bytesOfMessage);
            digest = messageDigest.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            hashText = bigInt.toString(16);
            while(hashText.length() < 32) {
                hashText = "0"+hashText;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashText;

    }
}
