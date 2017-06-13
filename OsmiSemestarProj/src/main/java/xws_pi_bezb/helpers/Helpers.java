package xws_pi_bezb.helpers;

import java.util.Random;

public class Helpers {

	public static String allChars = "asdfghjklpoiuytrewqzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

	public static boolean isNullOrEmpty(String str) {
		if (str == null) {
			return true;
		} else if (str.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static String generatePassword (){
		char[] chars = allChars.toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		System.out.println(output);
		return sb.toString();
	}

}
