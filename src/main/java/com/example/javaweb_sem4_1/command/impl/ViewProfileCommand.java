package com.example.javaweb_sem4_1.command.impl;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.Router;
import com.example.javaweb_sem4_1.entity.Image;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import com.example.javaweb_sem4_1.util.PageConstant;
import jakarta.servlet.http.HttpServletRequest;

public class ViewProfileCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        Router router = new Router();

        if (currentUser == null) {
            throw new CommandException("No user is currently logged in.");
        }

        try {
            User userToView = userService.findUserById(currentUser.getId());
            request.setAttribute("user", userToView);

            String profileImagePath;
            if (userToView.getPredefinedImageId() == null) {
                profileImagePath = request.getContextPath() + "/resources/images/basic_picture.png";
            } else {
                Image profileImage = userService.getPredefinedImageById(userToView.getPredefinedImageId());
                profileImagePath = "data:image/jpeg;base64," + profileImage.getBase64Data();
            }
            request.setAttribute("profileImagePath", profileImagePath);
            router.setPage(PageConstant.VIEW_PROFILE_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Error executing ViewProfileCommand", e);
        }
        return router;
    }
}
