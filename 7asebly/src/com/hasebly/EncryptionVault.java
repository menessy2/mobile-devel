package com.hasebly;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class EncryptionVault {

	private static  String aesKey, sessionValue;
	private static RSAPublicKey rsaKey;
	
	
	public static String getAesKey() {
		return aesKey;
	}

	public static void setAesKey() {
        String keyvalue  = generateRandomPassword();
        Key Akey = new SecretKeySpec(keyvalue.getBytes(), 0,32,"AES");
        aesKey = Base64.encodeToString(Akey.getEncoded(), Base64.DEFAULT);
	}

	public static String getSessionValue() {
		return sessionValue;
	}

	public static void setSessionValue(String sessionValue2) {
		sessionValue = sessionValue2;
	}

	public static RSAPublicKey getRsaKey() {
		return rsaKey;
	}

	public static void setRsaKey(String modulus, String exponent) throws NoSuchAlgorithmException, InvalidKeySpecException {
		exponent = exponent.replaceAll("[^\\d.]", "");
	    modulus = modulus.replaceAll("[^\\d.]", "");
	    BigInteger mod = new BigInteger(modulus);
	    BigInteger exp = new BigInteger(exponent);
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(mod, exp);
	    rsaKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
	}
	
	private static String generateRandomPassword()
    {
        SecureRandom RANDOM = new SecureRandom();
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ0123456789+/";

        String pw = "";
        for (int i=0; i<32; i++)
        {
            int index = (int)(RANDOM.nextDouble()*letters.length());
            pw += letters.substring(index, index+1);
        }
        return pw;
    }
	
	
}
