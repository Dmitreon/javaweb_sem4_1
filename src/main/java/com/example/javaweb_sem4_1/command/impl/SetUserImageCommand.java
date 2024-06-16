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

public class SetUserImageCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            throw new CommandException("No user is currently logged in.");
        }

        String imageIdStr = request.getParameter("imageId");
        if (imageIdStr == null || imageIdStr.isEmpty()) {
            throw new CommandException("Image ID is missing.");
        }

        try {
            int imageId = Integer.parseInt(imageIdStr);
            userService.setUserPredefinedImage(currentUser.getId(), imageId);
            currentUser.setPredefinedImageId(imageId);
            request.getSession().setAttribute("currentUser", currentUser);

            return new Router(request.getContextPath() + "/controller?command=VIEW_PROFILE", Router.Type.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Error setting user image", e);
        }
    }
}
