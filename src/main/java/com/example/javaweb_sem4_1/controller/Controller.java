package com.example.javaweb_sem4_1.controller;

import com.example.javaweb_sem4_1.command.Command;
import com.example.javaweb_sem4_1.command.CommandType;
import com.example.javaweb_sem4_1.exception.CommandException;
import com.example.javaweb_sem4_1.pool.ConnectionPool;
import com.example.javaweb_sem4_1.util.PageConstants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("Controller: doGet called");

        String commandStr = request.getParameter("command");
        if (commandStr == null || commandStr.isEmpty()) {
            commandStr = request.getParameter("action");
        }

        if (commandStr == null || commandStr.isEmpty()) {
            System.out.println("Controller: Command parameter is missing or empty.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Command parameter is missing or empty.");
            return;
        }

        System.out.println("Controller: Received command: " + commandStr);
        response.setContentType("text/html");

        Command command = CommandType.define(commandStr.toUpperCase());
        if (command == null) {
            System.out.println("Controller: Command not recognized: " + commandStr);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Command not recognized.");
            return;
        }

        try {
            String page = command.execute(request);
            System.out.println("Controller: Command executed, forwarding to: " + page);
            request.getRequestDispatcher(page).forward(request, response);
        } catch (CommandException e) {
            System.out.println("Controller: CommandException caught");
            e.printStackTrace();
            request.setAttribute("error_msg", e.getMessage());
            request.getRequestDispatcher(PageConstants.ERROR_PAGE).forward(request, response);
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
