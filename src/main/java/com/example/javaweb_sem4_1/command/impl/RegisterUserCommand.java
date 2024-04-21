package com.example.javaweb_sem4_1.command.impl;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.dao.impl.UserDaoImpl;
import com.example.javaweb_sem4_1.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.javaweb_sem4_1.util.PageConstants.*;

public class RegisterUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegisterUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Executing RegisterUserCommand with parameters: username={}, email={}",
                request.getParameter("username"), request.getParameter("email"));

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (username == null || password == null) {
            return "redirect:/register?error=true";
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
                request.getSession().setAttribute("message", "User successfully registered.");
                return REGISTER_SUCCESS_PAGE;
            } else {
                request.getSession().setAttribute("error", "Failed to register user.");
                return REGISTER_PAGE;
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Error registering user.");
            return REGISTER_PAGE;
        }
    }
}
