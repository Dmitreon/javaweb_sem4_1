package com.example.javaweb_sem4_1.command;

import com.example.javaweb_sem4_1.command.impl.account.*;
import com.example.javaweb_sem4_1.command.impl.admin.AddPredefinedImageCommand;
import com.example.javaweb_sem4_1.command.impl.admin.AddUserCommand;
import com.example.javaweb_sem4_1.command.impl.admin.DeleteUserCommand;
import com.example.javaweb_sem4_1.command.impl.admin.ViewUsersCommand;
import com.example.javaweb_sem4_1.command.impl.auth.*;
import com.example.javaweb_sem4_1.command.impl.common.DefaultCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    VIEW_USERS(new ViewUsersCommand()),
    REGISTER_USER(new RegisterUserCommand()),
    SEND_EMAIL(new SendEmailCommand()),
    VERIFY_CODE(new VerifyCodeCommand()),
    VIEW_PROFILE(new ViewProfileCommand()),
    UPDATE_PASSWORD(new UpdatePasswordCommand()),
    DELETE_USER(new DeleteUserCommand()),
    DELETE_OWN_ACCOUNT(new DeleteOwnAccountCommand()),
    ADD_PREDEFINED_IMAGE(new AddPredefinedImageCommand()),
    VIEW_IMAGES(new ViewImagesCommand()),
    SET_USER_IMAGE(new SetUserImageCommand()),
    DEFAULT(new DefaultCommand());

    private static final Logger logger = LogManager.getLogger(CommandType.class);
    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        for (CommandType commandType : values()) {
            commandMap.put(commandType.name(), commandType.command);
        }
    }

    private final Command command;

    CommandType(Command commandInstance) {
        this.command = commandInstance;
    }

    public Command getCommand() {
        return command;
    }

    public static Command define(String commandStr) {
        Command command = commandMap.get(commandStr.toUpperCase());
        if (command != null) {
            return command;
        } else {
            logger.error("Command not found: {}", commandStr);
            return DEFAULT.getCommand();
        }
    }
}
