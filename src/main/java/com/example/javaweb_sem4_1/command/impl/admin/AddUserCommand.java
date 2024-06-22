package com.example.javaweb_sem4_1.command.impl.admin;

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

public class AddUserCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String username = request.getParameter(AttributeConstant.USERNAME);
        String password = request.getParameter(AttributeConstant.PASSWORD);
        String email = request.getParameter(AttributeConstant.EMAIL);

        if (username == null && password == null && email == null) {
            return new Router(PageConstant.ADD_USERS_PAGE, Router.Type.FORWARD);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        Router router = new Router();
        try {
            userService.createUser(user);
            request.getSession().removeAttribute(AttributeConstant.ERROR);
            router.setPage(PageConstant.ADD_USER_SUCCESS_PAGE);
            router.setType(Router.Type.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute(AttributeConstant.ERROR, e.getMessage());
            router.setPage(PageConstant.ADD_USERS_PAGE);
            router.setType(Router.Type.FORWARD);
        }
        return router;
    }
}
