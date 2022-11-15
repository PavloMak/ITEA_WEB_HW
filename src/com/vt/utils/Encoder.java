package com.vt.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {
	
	private static final String SULT = "dfjtr"; 
	
	public static String md5Encription(String input) {
		String output = null;
		try {
			MessageDigest message = MessageDigest.getInstance("MD5");
			message.update(StandardCharsets.UTF_8.encode(input));
			output = String.format("%032x", new BigInteger(message.digest()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	
	public static String md5EncriptionWithSult(String input) {
		return md5Encription(input+SULT);
	}

}
