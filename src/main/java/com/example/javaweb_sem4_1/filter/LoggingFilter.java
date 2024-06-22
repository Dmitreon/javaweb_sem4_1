package com.example.javaweb_sem4_1.filter;

import com.example.javaweb_sem4_1.util.constant.AttributeConstant;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

public class LoggingFilter implements Filter {
    private static final Logger logger = Logger.getLogger(LoggingFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //todo
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();
        String user = (httpRequest.getSession().getAttribute(AttributeConstant.CURRENT_USER) != null) ?
                httpRequest.getSession().getAttribute(AttributeConstant.CURRENT_USER).toString() : "anonymous";

        logger.info(String.format("User %s requested %s %s", user, method, uri));

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //todo
    }
}
