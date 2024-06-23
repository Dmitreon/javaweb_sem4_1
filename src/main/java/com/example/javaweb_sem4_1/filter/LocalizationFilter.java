package com.example.javaweb_sem4_1.filter;

import com.example.javaweb_sem4_1.util.constant.AttributeConstant;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.jstl.core.Config;

import java.io.IOException;
import java.util.Locale;

public class LocalizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // todo
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        String localeParam = request.getParameter(AttributeConstant.LANG);
        if (localeParam != null) {
            Locale locale = new Locale(localeParam);
            Config.set(session, Config.FMT_LOCALE, locale);
            session.setAttribute(AttributeConstant.LANG, localeParam);

            Cookie languageCookie = new Cookie("language", localeParam);
            languageCookie.setMaxAge(60 * 60 * 24 * 30);
            languageCookie.setPath("/");
            httpResponse.addCookie(languageCookie);
        } else {
            Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("language".equals(cookie.getName())) {
                        String language = cookie.getValue();
                        Locale locale = new Locale(language);
                        Config.set(session, Config.FMT_LOCALE, locale);
                        session.setAttribute(AttributeConstant.LANG, language);
                        break;
                    }
                }
            }

            if (session.getAttribute(AttributeConstant.LANG) == null) {
                Config.set(session, Config.FMT_LOCALE, Locale.ENGLISH);
                session.setAttribute(AttributeConstant.LANG, "en");
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // todo
    }
}
