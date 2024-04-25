package com.example.javaweb_sem4_1.command.impl;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import com.example.javaweb_sem4_1.util.PageConstant;
import com.example.javaweb_sem4_1.exception.CommandException;

import java.util.List;

public class ViewUsersCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            List<User> usersList = userService.getAllUsers();
            request.setAttribute("users", usersList);
            return PageConstant.VIEW_USERS_PAGE;
        } catch (ServiceException e) {
            throw new CommandException("Failed to get list of users", e);
        }
    }
}
