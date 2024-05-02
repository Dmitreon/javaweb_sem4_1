package com.example.javaweb_sem4_1.util;

public final class PageConstant {
    public static final String INDEX_PAGE = "index.jsp";
    public static final String MAIN_PAGE = "pages/main/main.jsp";
    public static final String ERROR_PAGE = "pages/error/error_500.jsp";
    public static final String VIEW_USERS_PAGE = "pages/admin/view_user.jsp";
    public static final String ADD_USERS_PAGE = "pages/admin/add_user.jsp";
    public static final String ADD_USER_SUCCESS_PAGE = "pages/admin/add_user_success.jsp";
    public static final String REGISTER_PAGE = "pages/account/register.jsp";
    public static final String REGISTER_SUCCESS_PAGE = "pages/account/register_success.jsp";
    public static final String VIEW_PROFILE_PAGE = "pages/profile/view_profile.jsp";
    public static final String UPDATE_PASSWORD_PAGE = "pages/profile/update_password.jsp";
    public static final String SUCCESS_UPDATE_PASSWORD_PAGE = "pages/profile/success_update_password_page.jsp";

    private PageConstant() {
        throw new AssertionError("Instantiating utility class.");
    }
}
