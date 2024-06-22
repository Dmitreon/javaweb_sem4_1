package com.example.javaweb_sem4_1.command.impl.admin;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.Router;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import com.example.javaweb_sem4_1.util.constant.AttributeConstant;
import com.example.javaweb_sem4_1.util.constant.PageConstant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.IOException;

public class AddPredefinedImageCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            Part filePart = request.getPart("image");
            try {
                boolean isAdded = userService.addPredefinedImage(filePart);
                if (isAdded) {
                    router.setPage(PageConstant.ADD_IMAGE_SUCCESS_PAGE);
                    router.setType(Router.Type.REDIRECT);
                } else {
                    request.getSession().setAttribute(AttributeConstant.ERROR, "Failed to add image.");
                    router.setPage(PageConstant.ADD_IMAGE_PAGE);
                    router.setType(Router.Type.FORWARD);
                }
            } catch (ServiceException e) {
                request.getSession().setAttribute(AttributeConstant.ERROR, e.getMessage());
                router.setPage(PageConstant.ADD_IMAGE_PAGE);
                router.setType(Router.Type.FORWARD);
            }
        } catch (IOException | ServletException e) {
            throw new CommandException("Error while adding image", e);
        }
        return router;
    }
}
