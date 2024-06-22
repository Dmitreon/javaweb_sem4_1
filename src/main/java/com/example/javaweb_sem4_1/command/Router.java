package com.example.javaweb_sem4_1.command;

import com.example.javaweb_sem4_1.util.constant.PageConstant;

public class Router {
    private String page;
    private Type type;

    public enum Type {
        FORWARD, REDIRECT
    }

    public Router() {
        this.page = PageConstant.INDEX_PAGE;
        this.type = Type.FORWARD;
    }

    public Router(String page) {
        this.page = page;
        this.type = Type.FORWARD;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setRedirect() {
        this.type = Type.REDIRECT;
    }
}
