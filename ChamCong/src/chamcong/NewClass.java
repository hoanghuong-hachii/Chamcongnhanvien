/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chamcong;
// Imports
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Scanner;

public class NewClass {

    // Signing Algorithm
    private static final String SIGNING_ALGORITHM = "SHA256withRSA";
    private static final String RSA = "RSA";
    private static Scanner sc;

    // Function to implement Digital signature
    // using SHA256 and RSA algorithm
    // by passing private key.
    public static byte[] Create_Digital_Signature( byte[] input,PrivateKey Key)throws Exception
    {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initSign(Key);
        signature.update(input);
        return signature.sign();
    }

    // Generating the asymmetric key pair
    // using SecureRandom class
    // functions and RSA algorithm.
    public static KeyPair Generate_RSA_KeyPair()throws Exception
    {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(2048, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

    // Function for Verification of the
    // digital signature by using the public key
    public static boolean
    Verify_Digital_Signature( byte[] input,byte[] signatureToVerify,PublicKey key)throws Exception
    {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initVerify(key);
        signature.update(input);
        return signature.verify(signatureToVerify);
    }
    
    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }

    // Driver Code
    public static void main(String args[])throws Exception
    {

        String input = "GEEKSFORGEEKS IS A" + " COMPUTER SCIENCE PORTAL";
        KeyPair keyPair = Generate_RSA_KeyPair();
        
        byte[] ip = input.getBytes();
        System.out.println("Input Value:\n " + bytesToHex(ip));
        // Function Call
        byte[] signature  = Create_Digital_Signature( input.getBytes(),keyPair.getPrivate());

        // Convert signature to hex manually
        StringBuilder signatureHex = new StringBuilder();
        StringBuilder inputHex = new StringBuilder();
        System.out.println("input " + input.getBytes());
        for (byte b : signature) {
            signatureHex.append(String.format("%02X", b));
        }
        System.out.println("Signature Value:\n " + signatureHex);

        System.out.println( "Verification: " + Verify_Digital_Signature( input.getBytes(),signature, keyPair.getPublic()));
    }
}
