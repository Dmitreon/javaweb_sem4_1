package com.example.javaweb_sem4_1.command.impl.admin;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.Router;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import com.example.javaweb_sem4_1.util.constant.AttributeConstant;
import com.example.javaweb_sem4_1.util.constant.PageConstant;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ViewUsersCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            List<User> usersList = userService.retrieveAllUsers();
            request.setAttribute(AttributeConstant.USERS, usersList);
            router.setPage(PageConstant.VIEW_USERS_PAGE);
        } catch (Exception e) {
            throw new CommandException("Failed to get list of users", e);
        }
        return router;
    }
}
