package api.gorest.utils;

public class StringUtils {

	public static String getRandomEmails() {
		return "apitest" + System.currentTimeMillis() + "@api.com";
	}
}
