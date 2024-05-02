package com.example.javaweb_sem4_1.command;

import com.example.javaweb_sem4_1.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
    default void refresh(){
    }
}
