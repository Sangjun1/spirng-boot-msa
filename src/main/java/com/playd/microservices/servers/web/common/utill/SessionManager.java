package com.playd.microservices.servers.web.common.utill;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Locale;

@Slf4j
public class SessionManager {

	public static String getSessionId(HttpServletRequest request) {
		return WebUtils.getSessionId(request);
	}

	public static Object getSession(HttpServletRequest request, String sessionKey) {
		return WebUtils.getSessionAttribute(request, sessionKey);
	}

	public static void setSession(HttpServletRequest request, String sessionKey, Object sessionObject) {
		WebUtils.setSessionAttribute(request, sessionKey, sessionObject);
	}

	public static void removeSession(HttpServletRequest request, String sessionKey) {
		if (getSession(request, sessionKey) != null) {
			request.getSession().removeAttribute(sessionKey);
		}
	}

	public static void removeSession(HttpServletRequest request) {
		request.getSession().invalidate();
	}

	public static void setMaxInactiveInterval(HttpServletRequest request, int interval) {
		if (request.getSession() != null) {
			request.getSession().setMaxInactiveInterval(interval);
		}
	}

	public static Locale getLocale(HttpServletRequest request) {
		return RequestContextUtils.getLocale(request);
	}

	public static void setLocale(HttpServletRequest request, Locale locale) {
		if (request.getSession() != null) {
			setSession(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
			setSession(request, "sessionLocale", locale);
		}
	}

	public static void showParameters(HttpServletRequest request) {
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String s = enu.nextElement();
			log.info(" request [" + s + "]  : " + request.getParameter(s));
		}
	}
}