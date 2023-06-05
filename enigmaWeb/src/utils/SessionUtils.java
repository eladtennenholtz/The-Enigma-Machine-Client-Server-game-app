package utils;

//import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtils {

    public static String getUsername (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object sessionAttribute = session != null ? session.getAttribute(constants.ConstantsWeb.USERNAME) : null;
        return sessionAttribute != null ? sessionAttribute.toString() : null;
    }


    
    public static void clearSession (HttpServletRequest request) {
        request.getSession().invalidate();
    }
}