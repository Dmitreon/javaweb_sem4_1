package com.example.javaweb_sem4_1.command.impl;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import com.example.javaweb_sem4_1.util.PageConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        try {
            if (userService.authenticate(login, password)){
                request.setAttribute("user", login);
                session.setAttribute("user_name", login);
                session.setAttribute("current_page", PageConstant.MAIN_PAGE);
                return PageConstant.MAIN_PAGE;
            } else {
                request.setAttribute("login_msg", "incorrect login or password");
                session.setAttribute("current_page", PageConstant.INDEX_PAGE);
                return PageConstant.INDEX_PAGE;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
