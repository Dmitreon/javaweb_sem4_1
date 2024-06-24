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

public class VerifyCodeCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String enteredCode = request.getParameter(AttributeConstant.VERIFICATION_CODE);
        Integer sessionCode = (Integer) session.getAttribute(AttributeConstant.VERIFICATION_CODE);

        Router router = new Router();
        if (enteredCode != null && Integer.parseInt(enteredCode) == sessionCode) {
            String username = (String) session.getAttribute(AttributeConstant.USERNAME);
            String password = (String) session.getAttribute(AttributeConstant.PASSWORD);
            String email = (String) session.getAttribute(AttributeConstant.EMAIL);

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);

            try {
                userService.createUser(user);
                router.setPage(PageConstant.REGISTER_SUCCESS_PAGE);
                router.setType(Router.Type.REDIRECT);
            } catch (ServiceException e) {
                session.setAttribute(AttributeConstant.ERROR, e.getMessage());
                router.setPage(PageConstant.REGISTER_PAGE);
                router.setType(Router.Type.FORWARD);
            }
        } else {
            request.setAttribute(AttributeConstant.ERROR, "Invalid verification code");
            router.setPage(PageConstant.VERIFICATION_PAGE);
            router.setType(Router.Type.FORWARD);
        }
        return router;
    }
}
