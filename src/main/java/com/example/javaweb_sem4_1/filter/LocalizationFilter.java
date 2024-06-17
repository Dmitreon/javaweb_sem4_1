package com.example.javaweb_sem4_1.filter;

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
        String localeParam = request.getParameter("lang");

        if (localeParam != null) {
            Locale locale = new Locale(localeParam);
            Config.set(session, Config.FMT_LOCALE, locale);
            session.setAttribute("lang", localeParam);
        } else if (session.getAttribute("lang") == null) {
            Config.set(session, Config.FMT_LOCALE, Locale.ENGLISH);
            session.setAttribute("lang", "en");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //todo
    }
}
