package xws_pi_bezb.helpers;

public class Helpers {

	public static boolean isNullOrEmpty(String str) {
		if (str == null) {
			return true;
		} else if (str.isEmpty()) {
			return true;
		}
		return false;
	}
}
