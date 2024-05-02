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
import org.mindrot.jbcrypt.BCrypt;

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

        if (newPassword == null || newPassword.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            request.setAttribute("errorMessage", "Password fields cannot be empty.");
            router.setPage(PageConstant.UPDATE_PASSWORD_PAGE);
            return router;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "New passwords do not match.");
            router.setPage(PageConstant.UPDATE_PASSWORD_PAGE);
            return router;
        }

//        if (newPassword.length() < 8 || !newPassword.matches(".*\\d.*") || !newPassword.matches(".*[a-zA-Z].*") || !newPassword.matches(".*[!@#$%^&*()].*")) {
//            request.setAttribute("errorMessage", "New password must be at least 8 characters long and include numbers, letters, and special characters.");
//            router.setPage(PageConstant.UPDATE_PASSWORD_PAGE);
//            return router;
//        }

        if (BCrypt.checkpw(newPassword, currentUser.getPassword())) {
            request.setAttribute("errorMessage", "New password cannot be the same as the current password.");
            router.setPage(PageConstant.UPDATE_PASSWORD_PAGE);
            return router;
        }

        try {
            if (userService.updatePassword(currentUser, currentPassword, newPassword)) {
                currentUser = userService.findUserById(currentUser.getId());
                session.setAttribute("currentUser", currentUser);
                router.setPage(PageConstant.SUCCESS_UPDATE_PASSWORD_PAGE);
            } else {
                request.setAttribute("errorMessage", "Current password is incorrect.");
                router.setPage(PageConstant.UPDATE_PASSWORD_PAGE);
            }
        } catch (ServiceException e) {
            request.setAttribute("errorMessage", "Failed to update password: " + e.getMessage());
            router.setPage(PageConstant.UPDATE_PASSWORD_PAGE);
        }
        return router;
    }
}
