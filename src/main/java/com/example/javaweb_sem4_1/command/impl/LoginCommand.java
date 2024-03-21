package com.example.javaweb_sem4_1.command.impl;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");//todo
        UserService userService = UserServiceImpl.getInstance();
        String page;
        if (userService.authenticate(login, password)){
           request.setAttribute("user", login);
           page = "pages/main.jsp";
        } else {
            request.setAttribute("login_msg", "incorrect login or password");
            page = "index.jsp";
        }

        return page;
    }
}
