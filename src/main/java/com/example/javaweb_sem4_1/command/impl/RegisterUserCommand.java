package com.example.javaweb_sem4_1.command.impl;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.Router;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import com.example.javaweb_sem4_1.util.PageConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class RegisterUserCommand implements Command {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_EMAIL = "email";
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String username = request.getParameter(PARAM_USERNAME);
        String password = request.getParameter(PARAM_PASSWORD);
        String email = request.getParameter(PARAM_EMAIL);

        Router router = new Router();
        HttpSession session = request.getSession();
        try {
            if (userService.usernameExists(username)) {
                session.setAttribute("error", "Username already exists.");
                router.setPage(PageConstant.REGISTER_PAGE);
            } else if (userService.emailExists(email)) {
                session.setAttribute("error", "Email already exists.");
                router.setPage(PageConstant.REGISTER_PAGE);
            } else {
                int verificationCode = userService.verification(email);
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                session.setAttribute("email", email);
                session.setAttribute("verificationCode", verificationCode);
                router.setPage(PageConstant.VERIFICATION_PAGE);
            }
        } catch (ServiceException e) {
            session.setAttribute("error", e.getMessage());
            router.setPage(PageConstant.REGISTER_PAGE);
        }
        router.setRedirect();
        return router;
    }
}
