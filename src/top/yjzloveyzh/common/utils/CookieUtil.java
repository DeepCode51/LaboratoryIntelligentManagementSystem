/**
 *
 */
package top.yjzloveyzh.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.yjzloveyzh.common.Constants;

/**
 * @author 171882105@qq.com
 */
public class CookieUtil {

    public static void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name.trim(), value.trim());
        cookie.setMaxAge(Constants.Cookie.COOKIE_TIME_EXIST);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie uid = new Cookie(name, null);
        uid.setPath("/");
        uid.setMaxAge(0);
        response.addCookie(uid);
    }

    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie cookies[] = request.getCookies();

        for (Cookie cookie : cookies) {

            if (cookie.getName().equals(cookieName)) {

                return cookie.getValue();
            }
        }

        return null;
    }
}
