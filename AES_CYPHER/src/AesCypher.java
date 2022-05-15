import java.util.Base64;
import java.util.Scanner;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesCypher {
	private static final String characterEncoding = "UTF-8";
	private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
	private static final String aesEncryptionAlgorithem = "AES";


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		String encryptionKey="RIYADRIDWANABRAR";// System.in is a standard input stream.
		System.out.println("16 Digit Keys are : " +encryptionKey );
		
		System.out.println("Fist we encript and chypertex .... ");
		encrypt(encryptionKey);
		System.out.println("dycript the chypertex text and get plain text.... ");
		decrypt(encryptionKey);
		System.out.println("Encyption and decryption has done");
	}
	
	
	private static void encrypt(String encryptionKey) {
		
		
		System.out.println("Encryption process fuction calling . ");
		String cyphertext;
		System.out.println("File Seaching...");
		String pathofinput = FileSearch();//getting the file name
		if(pathofinput==null) {

			System.out.println("No file found");
			return;
		}
		else {
			System.out.println("File Found");
		}
        Path fileName = Path.of(pathofinput);
		
		// Retrive plaintext from the java AES_Cipher filename.txt file
		String Stringplaintext = null;
		try {
			Stringplaintext = Files.readString(fileName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		
		
		
		Scanner scanner = new Scanner(System.in);

		String encryptedText = "";
		try {
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			byte[] key = encryptionKey.getBytes(characterEncoding);
			SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
			IvParameterSpec ivparameterspec = new IvParameterSpec(key);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
			byte[] cipherText = cipher.doFinal(Stringplaintext.getBytes("UTF8"));
			Base64.Encoder encoder = Base64.getEncoder();
			encryptedText = encoder.encodeToString(cipherText);

		} catch (Exception E) {
			System.err.println("Encrypt Exception : " + E.getMessage());
		}
		
		Path crypto = Path.of("crypto.txt");

		try {
			Files.writeString(crypto, encryptedText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Encryption process Finished and Get Chypher Text.... ");
		

	}

	private static String FileSearch() {
		// TODO Auto-generated method stub
		
		File dir = new File(".");
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith("java AES_Cipher");
			}
		};
		String[] children = dir.list(filter);
		if (children == null) {
			System.out.println("No file found.");
			return null;
		} else {
			String filename = children[0];
			return filename;
		}
	}
	
	
	private static void decrypt(String encryptionKey) {
		// TODO Auto-generated method stub
		System.out.println("Decryption process fuction calling . ");
		
		
		
		String pathofinput = FileSearch();//getting the file name
		if(pathofinput==null) {
			return;
		}
		// TODO Auto-generated method stub
		System.out.println("Decryption process Started.... ");
		String cyphertext;
		Path fileName = Path.of("crypto.txt");
		// Retrive plaintext from the givencleartext.txt file
		String Stringcyphertext = null;
		try {
			Stringcyphertext = Files.readString(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Files to be submitted: AES_Cipher.java AES_Cipher.class plaintext.txt ");
		
		
		
		String decryptedText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(Stringcyphertext.getBytes("UTF8"));
            decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

        } catch (Exception E) {
            System.err.println("decrypt Exception : "+E.getMessage());
        }
        
        
        
        
        Path cleartext = Path.of("cleartext.txt");

		try {
			Files.writeString(cleartext, decryptedText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Decryption process Finished.... ");
        
        
        
        
	}



}
