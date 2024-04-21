package com.example.javaweb_sem4_1.util;

public final class PageConstants {
    public static final String INDEX_PAGE = "index.jsp";
    public static final String MAIN_PAGE = "pages/main.jsp";
    public static final String ERROR_PAGE = "pages/error/error_500.jsp";
    public static final String VIEW_USERS_PAGE = "pages/view_user.jsp";
    public static final String ADD_USERS_PAGE = "pages/add_user.jsp";
    public static final String ADD_USER_SUCCESS_PAGE = "pages/add_user_success.jsp";
    public static final String REGISTER_PAGE = "pages/register.jsp";
    public static final String REGISTER_SUCCESS_PAGE = "pages/register_success.jsp";

    private PageConstants() {
        throw new AssertionError("Instantiating utility class.");
    }
}
