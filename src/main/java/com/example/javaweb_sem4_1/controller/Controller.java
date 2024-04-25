package com.example.javaweb_sem4_1.controller;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.CommandType;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.pool.ConnectionPool;
import com.example.javaweb_sem4_1.util.PageConstant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Controller.class.getName());

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.log(Level.INFO, "Controller: doGet called");

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
            String page = command.execute(request);
            logger.log(Level.INFO, "Command executed, forwarding to: {0}", page);
            request.getRequestDispatcher(page).forward(request, response);
        } catch (CommandException e) {
            logger.log(Level.SEVERE, "CommandException caught", e);
            request.setAttribute("error_msg", e.getMessage());
            request.getRequestDispatcher(PageConstant.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}
