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
import jakarta.servlet.http.HttpSession;

public class VerifyCodeCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String enteredCode = request.getParameter("verificationCode");
        Integer sessionCode = (Integer) session.getAttribute("verificationCode");

        Router router = new Router();
        if (enteredCode != null && Integer.parseInt(enteredCode) == sessionCode) {
            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
            String email = (String) session.getAttribute("email");

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);

            try {
                userService.createUser(user);
                router.setPage(PageConstant.REGISTER_SUCCESS_PAGE);
            } catch (ServiceException e) {
                session.setAttribute("error", e.getMessage());
                router.setPage(PageConstant.REGISTER_PAGE);
            }
        } else {
            request.setAttribute("error", "Invalid verification code");
            router.setPage(PageConstant.VERIFICATION_PAGE);
        }
        router.setRedirect();
        return router;
    }
}
