package com.example.javaweb_sem4_1.command.impl.auth;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.Router;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import com.example.javaweb_sem4_1.util.constant.AttributeConstant;
import com.example.javaweb_sem4_1.util.constant.PageConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class RegisterUserCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String username = request.getParameter(AttributeConstant.USERNAME);
        String password = request.getParameter(AttributeConstant.PASSWORD);
        String email = request.getParameter(AttributeConstant.EMAIL);

        Router router = new Router();
        HttpSession session = request.getSession();
        try {
            if (userService.usernameExists(username)) {
                session.setAttribute(AttributeConstant.ERROR, "Username already exists.");
                router.setPage(PageConstant.REGISTER_PAGE);
                router.setType(Router.Type.FORWARD);
            } else if (userService.emailExists(email)) {
                session.setAttribute(AttributeConstant.ERROR, "Email already exists.");
                router.setPage(PageConstant.REGISTER_PAGE);
                router.setType(Router.Type.FORWARD);
            } else {
                int verificationCode = userService.verification(email);
                session.setAttribute(AttributeConstant.USERNAME, username);
                session.setAttribute(AttributeConstant.PASSWORD, password);
                session.setAttribute(AttributeConstant.EMAIL, email);
                session.setAttribute(AttributeConstant.VERIFICATION_CODE, verificationCode);
                router.setPage(PageConstant.VERIFICATION_PAGE);
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            session.setAttribute(AttributeConstant.ERROR, e.getMessage());
            router.setPage(PageConstant.REGISTER_PAGE);
            router.setType(Router.Type.FORWARD);
        }
        return router;
    }
}
