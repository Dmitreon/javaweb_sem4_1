package com.example.javaweb_sem4_1.command.impl;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.Router;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.util.PageConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SendEmailCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        Router router = new Router();
        HttpSession session = request.getSession();

        try {
            int verificationCode = userService.verification(email);
            if (verificationCode != 0) {
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                session.setAttribute("email", email);
                session.setAttribute("verificationCode", verificationCode);
                router.setPage(PageConstant.VERIFICATION_PAGE);
            } else {
                request.setAttribute("signupError", "Incorrect email address");
                router.setPage(PageConstant.REGISTER_PAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }
}
