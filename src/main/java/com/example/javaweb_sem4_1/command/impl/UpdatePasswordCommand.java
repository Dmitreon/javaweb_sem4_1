package com.example.javaweb_sem4_1.command.impl;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.Router;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.exception.DaoException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import com.example.javaweb_sem4_1.util.PageConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UpdatePasswordCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("currentUser");
        Router router = new Router();

        if (currentUser == null) {
            router.setPage(PageConstant.INDEX_PAGE);
            router.setRedirect();
            return router;
        }

        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            router.setPage(PageConstant.UPDATE_PASSWORD_PAGE);
            return router;
        }

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        try {
            userService.changePassword(currentUser, currentPassword, newPassword, confirmPassword);
            session.setAttribute("currentUser", currentUser); // Update session user
            router.setPage(PageConstant.SUCCESS_UPDATE_PASSWORD_PAGE);
            router.setRedirect();
        } catch (ServiceException | DaoException e) {
            request.setAttribute("errorMessage", e.getMessage());
            router.setPage(PageConstant.UPDATE_PASSWORD_PAGE);
        }

        return router;
    }
}
