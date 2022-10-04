package com.seor0.cache.fragment.service;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Base64Crypter {
	
	
	public static String encrypt(String keyStr, String randomVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(randomVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(keyStr.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted text: "  + Base64.encodeBase64String(encrypted));
            return Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String keyStr, String randomVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(randomVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(keyStr.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] originalText = cipher.doFinal(Base64.decodeBase64(encrypted));
            System.out.println("decrypted text: "  + new String(originalText));
            return new String(originalText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   
}
