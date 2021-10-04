package com.sumerge;

import org.apache.commons.codec.binary.Base64;

public class App {
	public static byte[] encodeText(String text) {
		return Base64.encodeBase64(text.getBytes());
	}

	public static void main(String[] args) {
		String toConvert = "Hii";
		System.out.println(encodeText(toConvert));
	}
}
