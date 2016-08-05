package com.sung.sframe.common.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.omg.CORBA.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class LogUtils {

	public static final Logger log = LoggerFactory.getLogger(LogUtils.class);


	/**
	 * 记录访问日志
	 * [username][jsessionid][ip][accept][UserAgent][url][params][Referer]
	 *
	 * @param request
	 */
	public static void logAccess(HttpServletRequest request) {
//		String username = getUsername();
		String jsessionId = request.getRequestedSessionId();
		String ip = IpUtils.getIpAddr(request);
		String accept = request.getHeader("accept");
		String userAgent = request.getHeader("User-Agent");
		String url = request.getRequestURI();
		String params = getParams(request);
		String headers = getHeaders(request);

		StringBuilder s = new StringBuilder();
//		s.append(getBlock(username));
		s.append(getBlock(jsessionId));
		s.append(getBlock(ip));
		s.append(getBlock(accept));
		s.append(getBlock(userAgent));
		s.append(getBlock(url));
		s.append(getBlock(params));
		s.append(getBlock(headers));
		s.append(getBlock(request.getHeader("Referer")));
		log.info(s.toString());
	}

	/**
	 * 记录一直 info信息
	 *
	 * @param message
	 */
	public static void logInfo(String message) {
		StringBuilder s = new StringBuilder();
		s.append((message));
		log.info(s.toString());
	}

	public static void logInfo(String message, Object... arguments) {
		StringBuilder s = new StringBuilder();
		s.append(message);
		log.info(message, arguments);
	}

	public static void logInfo(String message, Throwable e) {
		StringBuilder s = new StringBuilder();
		s.append(("exception : -->>"));
		s.append((message));
		log.info(s.toString(), e);
	}

	public static void logWarn(String message) {
		StringBuilder s = new StringBuilder();
		s.append((message));
		log.warn(s.toString());
	}

	public static void logWarn(String message, Object... arguments) {
		StringBuilder s = new StringBuilder();
		s.append(message);
		log.warn(message, arguments);
	}

	public static void logWarn(String message, Throwable e) {
		StringBuilder s = new StringBuilder();
		s.append(("exception : -->>"));
		s.append((message));
		log.warn(s.toString(), e);
	}

	public static void logDebug(String message) {
		StringBuilder s = new StringBuilder();
		s.append((message));
		log.debug(s.toString());
	}

	public static void logDebug(String message, Object... arguments) {
		StringBuilder s = new StringBuilder();
		s.append(("exception : -->>"));
		s.append((message));
		log.debug(message, arguments);
	}

	public static void logDebug(String message, String a1,String a2) {
		StringBuilder s = new StringBuilder();
		s.append(("exception : -->>"));
		s.append((message));
		log.debug(message,a1,a2);
	}

	public static void logDebug(String message, Throwable e) {
		StringBuilder s = new StringBuilder();
		s.append(("exception : -->>"));
		s.append((message));
		log.debug(s.toString(), e);
	}

	public static void logError(String message) {
		StringBuilder s = new StringBuilder();
		s.append(message);
		log.error(s.toString());
	}

	public static void logError(String message, Object... arguments) {
		StringBuilder s = new StringBuilder();
		s.append(message);
		log.error(message, arguments);
	}

	/**
	 * 记录日志错误信息
	 *
	 * @param message
	 * @param e
	 */
	public static void logError(String message, Throwable e) {
		StringBuilder s = new StringBuilder();
		s.append(("exception : -->>"));
		s.append((message));
		log.error(s.toString(), e);

	}

/*	protected static String getUsername() {
		return (String) SecurityUtils.getSubject().getPrincipal();
	}*/

	protected static String getParams(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		return JSON.toJSONString(params);
	}

	private static String getHeaders(HttpServletRequest request) {
		Map<String, List<String>> headers = Maps.newHashMap();
		Enumeration<String> namesEnumeration = request.getHeaderNames();
		while(namesEnumeration.hasMoreElements()) {
			String name = namesEnumeration.nextElement();
			Enumeration<String> valueEnumeration = request.getHeaders(name);
			List<String> values = Lists.newArrayList();
			while(valueEnumeration.hasMoreElements()) {
				values.add(valueEnumeration.nextElement());
			}
			headers.put(name, values);
		}
		return JSON.toJSONString(headers);
	}

	public static String getBlock(String msg) {

		if (msg == null) {
			msg = "";
		}
		return "[" + msg.toString() + "]";
	}


	public static boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	public static boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	public static boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	public static boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}
}
