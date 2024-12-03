package edu.cd.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Base64Util {
	public static String encode(String msg) {

		String res = null;

		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] b = md.digest(msg.getBytes());

			BASE64Encoder encoder = new BASE64Encoder();
			res = encoder.encode(b);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static void main(String[] args) {
		String x = "123";
		String y = "adfhafhdfturtutjt";

		System.out.println(encode(x));
		System.out.println(encode(y));
	}
}
