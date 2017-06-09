package com.endlessspace.demo.arch.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.google.common.html.HtmlEscapers;

/**
 * Xss request wrapper
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {  
	  
    public XssHttpServletRequestWrapper(HttpServletRequest request) {  
        super(request);
    }  
  
    @Override  
    public String getHeader(String name) {  
        return HtmlEscapers.htmlEscaper().escape(super.getHeader(name));  
    }
  
    @Override  
    public String getQueryString() {  
        return HtmlEscapers.htmlEscaper().escape(super.getQueryString());  
    }
  
    @Override  
    public String getParameter(String name) {  
        return HtmlEscapers.htmlEscaper().escape(super.getParameter(name));  
    }
  
    @Override  
    public String[] getParameterValues(String name) {  
        String[] values = super.getParameterValues(name);  
        if(values != null) {  
            int length = values.length;  
            String[] escapseValues = new String[length];  
            for(int i = 0; i < length; i++){  
                escapseValues[i] = HtmlEscapers.htmlEscaper().escape(values[i]);  
            }
            return escapseValues;  
        }
        return super.getParameterValues(name);
    }
}  