package com.universidadez.tcc.util;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Criptografa uma String.
 * 
 */
public class Criptografia {

	/**
	 * Método que recebe a String e retorna o valor criptografado (SHA-256) em
	 * um objeto do tipo String.
	 * 
	 * @param str
	 *            String.
	 * @return String.
	 */
	public String criptografiaSha256(String str) {

		MessageDigest algorithm = null;
		try {
			algorithm = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte messageDigest[] = null;

		try {
			messageDigest = algorithm.digest(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		StringBuilder hexString = new StringBuilder();

		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		String strHex = hexString.toString();
		return strHex;
	}

	/*
	 * Construtores
	 */
	public Criptografia() {
	}
}
