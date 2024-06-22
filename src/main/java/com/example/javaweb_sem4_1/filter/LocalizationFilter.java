package com.example.javaweb_sem4_1.filter;

import com.example.javaweb_sem4_1.util.constant.AttributeConstant;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.jstl.core.Config;

import java.io.IOException;
import java.util.Locale;

public class LocalizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //todo
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String localeParam = request.getParameter(AttributeConstant.LANG);

        if (localeParam != null) {
            Locale locale = new Locale(localeParam);
            Config.set(session, Config.FMT_LOCALE, locale);
            session.setAttribute(AttributeConstant.LANG, localeParam);
        } else if (session.getAttribute(AttributeConstant.LANG) == null) {
            Config.set(session, Config.FMT_LOCALE, Locale.ENGLISH);
            session.setAttribute(AttributeConstant.LANG, "en");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //todo
    }
}
