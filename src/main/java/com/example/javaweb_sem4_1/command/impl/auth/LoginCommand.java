package com.example.javaweb_sem4_1.command.impl.auth;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.Router;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import com.example.javaweb_sem4_1.util.constant.AttributeConstant;
import com.example.javaweb_sem4_1.util.constant.PageConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        HttpSession session = request.getSession();
        Router router = new Router();

        try {
            User authenticatedUser = userService.authenticate(login, password);
            if (authenticatedUser != null) {
                session.setAttribute(AttributeConstant.CURRENT_USER, authenticatedUser);
                session.removeAttribute(AttributeConstant.LOGIN_MSG);
                router.setPage(PageConstant.MAIN_PAGE);
                router.setRedirect();
            } else {
                session.setAttribute(AttributeConstant.LOGIN_MSG, "Incorrect login or password");
                router.setPage(PageConstant.INDEX_PAGE);
                router.setRedirect();
            }
        } catch (ServiceException e) {
            session.setAttribute(AttributeConstant.LOGIN_MSG, "Login error: " + e.getMessage());
            router.setPage(PageConstant.INDEX_PAGE);
            router.setRedirect();
        }

        return router;
    }
}
