package com.example.javaweb_sem4_1.command.impl;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.Router;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import com.example.javaweb_sem4_1.util.PageConstant;
import jakarta.servlet.http.HttpServletRequest;

public class RegisterUserCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        Router router = new Router();
        try {
            userService.createUser(user);
            request.getSession().setAttribute("message", "User successfully registered.");
            router.setPage(PageConstant.REGISTER_SUCCESS_PAGE);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", "Error registering user: " + e.getMessage());
            router.setPage(PageConstant.REGISTER_PAGE);
            router.setRedirect();
        }
        return router;
    }
}
