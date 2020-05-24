package moo.util;

import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class Parameter {
	public static Map<String, String> extract(HttpServletRequest request) throws UnsupportedEncodingException {
		
		HashMap<String, String> params = new HashMap<String, String>();
		
		Enumeration<String> paramNames = request.getParameterNames();
		
		while (paramNames.hasMoreElements()){
		    String name = paramNames.nextElement();
		    params.put(name, request.getParameter(name));
		}

		return params;
	}
}
