package com.wavemaker.learning.algorithm;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by venkateswarluk on 20/7/16.
 */
public class CookieHandler {

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        String encodedName = Base64Encryption.encode(name);
        Cookie cookie = new Cookie("auth-cookie", encodedName);
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(18000);
        response.addCookie(cookie);
    }

    public static boolean validateCookie(Cookie[] cookie) {
        String name = getCookie(cookie);
        return (name != null && !name.isEmpty());
    }

    public static String getAuthCookie(Cookie[] cookie) {
        String name = getCookie(cookie);
        return name;
    }

    private static String getCookie(Cookie[] cookie) {
        String name = null;
        if (cookie != null) {
            for (int i = 0; i < cookie.length; i++) {
                if (cookie[i].getName().equals("auth-cookie")) {
                    name = Base64Encryption.decode(cookie[i].getValue());
                }
            }
        }
        return name;
    }
}
