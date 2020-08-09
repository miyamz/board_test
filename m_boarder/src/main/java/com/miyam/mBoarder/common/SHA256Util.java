package com.miyam.mBoarder.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SHA256Util {
	/**
	 * SHA-256 암호화 함
	 * 
	 * @param source       원본
	 * @param salt(String) SALT 값
	 * @return
	 */
	public static String getEncrypt(String source) {
		// 패스워드의 변경을 막기위해 난수값을 하나 지정하자
		String fix_salt = "e9130ca66a2f5993";
		
		return getEncrypt(source, fix_salt.getBytes());
	}

	/**
	 * SHA-256 암호화 함
	 * 
	 * @param source       원본
	 * @param salt(byte[]) SALT 값
	 * @return
	 */
	public static String getEncrypt(String source, byte[] salt) {

		String result = "";

		byte[] a = source.getBytes();
		byte[] bytes = new byte[a.length + salt.length];

		System.arraycopy(a, 0, bytes, 0, a.length);
		System.arraycopy(salt, 0, bytes, a.length, salt.length);

		try {
			// 암호화 방식 지정 메소드
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(bytes);

			byte[] byteData = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
			}

			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * SALT를 얻어온다.
	 * 
	 * @return
	 */
	public static String generateSalt() {
		Random random = new Random();

		byte[] salt = new byte[8];
		random.nextBytes(salt);

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < salt.length; i++) {
			// byte 값을 Hex 값으로 바꾸기.
			sb.append(String.format("%02x", salt[i]));
		}

		return sb.toString();
	}
	
	/*
	public static String encryptSHA256(String str) {
		String SHA = null;
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); // 이 부분을 SHA-1으로 바꿔도 된다!
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return SHA;
	}*/
}
