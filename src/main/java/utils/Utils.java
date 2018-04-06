package utils;

import javax.servlet.http.HttpServletRequest;

public class Utils {

	public static String loginControl(HttpServletRequest req, String invalid, String valid) {

		boolean sessionStatus = req.getSession().getAttribute("id") == null;
		if (sessionStatus) {
			return invalid;
		} else {
			return valid;
		}
	}
}