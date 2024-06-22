package com.example.javaweb_sem4_1.controller;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.CommandType;
import com.example.javaweb_sem4_1.command.Router;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.pool.impl.ConnectionPoolImpl;
import com.example.javaweb_sem4_1.util.constant.PageConstant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
@MultipartConfig
public class Controller extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Controller.class.getName());

    @Override
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.log(Level.INFO, "Controller: processRequest called");

        String commandStr = request.getParameter("command");
        if (commandStr == null || commandStr.isEmpty()) {
            commandStr = request.getParameter("action");
        }

        if (commandStr == null || commandStr.isEmpty()) {
            logger.log(Level.WARNING, "Command parameter is missing or empty.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Command parameter is missing or empty.");
            return;
        }

        logger.log(Level.INFO, "Received command: {0}", commandStr);
        response.setContentType("text/html");

        Command command = CommandType.define(commandStr.toUpperCase());
        if (command == null) {
            logger.log(Level.SEVERE, "Command not recognized: {0}", commandStr);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Command not recognized.");
            return;
        }

        try {
            Router router = command.execute(request);
            logger.log(Level.INFO, "Command executed, routing to: {0} with method {1}", new Object[]{router.getPage(), router.getType()});
            if (router.getType() == Router.Type.FORWARD) {
                request.getRequestDispatcher(router.getPage()).forward(request, response);
            } else {
                response.sendRedirect(router.getPage());
            }
        } catch (CommandException e) {
            logger.log(Level.SEVERE, "CommandException caught", e);
            request.setAttribute("error_msg", e.getMessage());
            request.getRequestDispatcher(PageConstant.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    public void destroy() {
        ConnectionPoolImpl.getInstance().destroyPool();
    }
}