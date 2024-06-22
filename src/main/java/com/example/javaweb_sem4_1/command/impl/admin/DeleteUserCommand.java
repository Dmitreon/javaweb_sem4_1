package com.example.javaweb_sem4_1.command.impl.admin;

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

public class DeleteUserCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(AttributeConstant.CURRENT_USER);

        Router router = new Router();

        if (currentUser == null || !"admin".equals(currentUser.getRole())) {
            router.setPage(PageConstant.INDEX_PAGE);
            router.setType(Router.Type.REDIRECT);
            return router;
        }

        String username = request.getParameter(AttributeConstant.USERNAME);
        int userId = Integer.parseInt(request.getParameter("userId"));

        try {
            User user = userService.findUserById(userId);
            if (user != null && user.getUsername().equals(username)) {
                userService.deleteUser(userId);
                router.setPage(PageConstant.DELETE_USER_SUCCESS_PAGE);
                router.setType(Router.Type.REDIRECT);
            } else {
                request.setAttribute(AttributeConstant.ERROR_MESSAGE, "Username does not match. User not deleted.");
                router.setPage(PageConstant.CONFIRM_DELETE_PAGE);
                router.setType(Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            request.setAttribute(AttributeConstant.ERROR_MESSAGE, "Failed to delete user: " + e.getMessage());
            router.setPage(PageConstant.ERROR_PAGE);
            router.setType(Router.Type.REDIRECT);
        }
        return router;
    }
}
