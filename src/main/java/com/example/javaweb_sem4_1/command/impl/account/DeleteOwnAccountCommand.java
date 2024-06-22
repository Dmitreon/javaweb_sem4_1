package com.example.javaweb_sem4_1.command.impl.account;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.Router;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import com.example.javaweb_sem4_1.util.constant.AttributeConstant;
import com.example.javaweb_sem4_1.util.constant.PageConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DeleteOwnAccountCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(AttributeConstant.CURRENT_USER);
        String enteredUsername = request.getParameter(AttributeConstant.USERNAME);

        Router router = new Router();
        if (currentUser == null || !currentUser.getUsername().equals(enteredUsername)) {
            request.setAttribute(AttributeConstant.ERROR_MESSAGE, "Username does not match. Account not deleted.");
            router.setPage(PageConstant.CONFIRM_DELETE_ACCOUNT_PAGE);
            router.setType(Router.Type.FORWARD);
            return router;
        }

        try {
            userService.deleteUser(currentUser.getId());
            session.invalidate();
            router.setPage(PageConstant.DELETE_ACCOUNT_SUCCESS_PAGE);
            router.setType(Router.Type.REDIRECT);
        } catch (ServiceException e) {
            request.setAttribute(AttributeConstant.ERROR_MESSAGE, "Failed to delete account: " + e.getMessage());
            router.setPage(PageConstant.ERROR_PAGE);
            router.setType(Router.Type.REDIRECT);
        }
        return router;
    }
}
