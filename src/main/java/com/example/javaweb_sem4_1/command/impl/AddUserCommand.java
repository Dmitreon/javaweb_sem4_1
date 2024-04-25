package com.example.javaweb_sem4_1.command.impl;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.example.javaweb_sem4_1.util.PageConstant.*;

public class AddUserCommand implements Command {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_EMAIL = "email";

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String username = request.getParameter(PARAM_USERNAME);
        String password = request.getParameter(PARAM_PASSWORD);
        String email = request.getParameter(PARAM_EMAIL);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        try {
            userService.createUser(user);
            request.getSession().setAttribute("message", "User successfully added.");
            return ADD_USER_SUCCESS_PAGE;
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", "Failed to add user: " + e.getMessage());
            return ADD_USERS_PAGE;
        }
    }
}


