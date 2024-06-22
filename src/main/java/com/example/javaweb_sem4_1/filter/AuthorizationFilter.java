package com.example.javaweb_sem4_1.filter;

import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.util.constant.AttributeConstant;
import com.example.javaweb_sem4_1.util.constant.PageConstant;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthorizationFilter implements Filter {
    private static final List<String> ADMIN_COMMANDS = Arrays.asList("ADD_USER", "DELETE_USER", "ADD_IMAGE");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //todo
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String command = httpRequest.getParameter("command");
        if (command != null && ADMIN_COMMANDS.contains(command.toUpperCase())) {
            if (session != null) {
                User currentUser = (User) session.getAttribute(AttributeConstant.CURRENT_USER);
                if (currentUser != null && "admin".equals(currentUser.getRole())) {
                    chain.doFilter(request, response);
                    return;
                }
            }
            httpResponse.sendRedirect(httpRequest.getContextPath() + PageConstant.INDEX_PAGE);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        //todo
    }
}
