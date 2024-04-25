package com.example.javaweb_sem4_1.command;

//todo

import static com.example.javaweb_sem4_1.util.PageConstant.INDEX_PAGE;

public class Router {
    private String page = INDEX_PAGE;
    private Type type = Type.FORWARD;
    enum Type{
        FORWARD, REDIRECT
    }

    public Router(String page) {
        this.page = page;
    }

    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect() {
        this.type = Type.REDIRECT;
    }
}
