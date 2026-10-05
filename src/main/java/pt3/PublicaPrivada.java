package pt3;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class PublicaPrivada {

    public static KeyPair generateRSAKeyPair() {
        KeyPair kPair = null;
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048); 
            kPair = keyGen.generateKeyPair();
        } catch (Exception ex) {
            System.err.println("Error generant les claus: " + ex);
        }
        return kPair;
    }

    public static byte[] encryptData(PublicKey pubKey, byte[] data) {
        byte[] encryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            encryptedData = cipher.doFinal(data);
        } catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
        }
        return encryptedData;
    }

    public static byte[] decryptData(PrivateKey privKey, byte[] data) {
        byte[] decryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privKey);
            decryptedData = cipher.doFinal(data);
        } catch (Exception ex) {
            System.err.println("Error desxifrant les dades: " + ex);
        }
        return decryptedData;
    }

    public static void main(String[] args) {
        try {
            KeyPair keyPair = generateRSAKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            String textOriginal = "Aquest és el text secret per a la pràctica Pt4.1";
            System.out.println("Text original: " + textOriginal);

            byte[] dadesXifrades = encryptData(publicKey, textOriginal.getBytes("UTF-8"));
            System.out.println("Text encriptat (Base64): " + Base64.getEncoder().encodeToString(dadesXifrades));

            byte[] dadesDesxifrades = decryptData(privateKey, dadesXifrades);
            String textRecuperat = new String(dadesDesxifrades, "UTF-8");
            System.out.println("Text desencriptat: " + textRecuperat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}