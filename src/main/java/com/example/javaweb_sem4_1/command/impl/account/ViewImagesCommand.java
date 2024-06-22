package com.example.javaweb_sem4_1.command.impl.account;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.Router;
import com.example.javaweb_sem4_1.entity.Image;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.service.impl.UserServiceImpl;
import com.example.javaweb_sem4_1.util.constant.AttributeConstant;
import com.example.javaweb_sem4_1.util.constant.PageConstant;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ViewImagesCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            List<Image> images = userService.getAvailableImages();
            request.setAttribute(AttributeConstant.IMAGES, images);
            router.setPage(PageConstant.VIEW_IMAGES_PAGE);
            router.setType(Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException("Error while fetching images", e);
        }
        return router;
    }
}
