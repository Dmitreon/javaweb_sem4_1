package com.example.javaweb_sem4_1.command.impl;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.dao.impl.UserDaoImpl;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;

import static com.example.javaweb_sem4_1.util.PageConstants.*;

public class AddUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (username == null || password == null) {
            throw new CommandException("Username or password parameter is missing");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

        if (email != null && !email.trim().isEmpty()) {
            user.setEmail(email.trim());
        }

        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            boolean isInserted = userDao.insert(user);
            if (isInserted) {
                request.getSession().setAttribute("message", "User successfully added.");
                return ADD_USER_SUCCESS_PAGE;
            } else {
                request.getSession().setAttribute("error", "Failed to add user.");
                return ADD_USERS_PAGE;
            }
        } catch (DaoException e) {
            throw new CommandException("Error adding user", e);
        }
    }
}
