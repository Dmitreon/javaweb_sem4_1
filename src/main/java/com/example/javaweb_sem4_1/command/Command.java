package com.example.javaweb_sem4_1.command;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request);
    default void refresh(){

    }
}
