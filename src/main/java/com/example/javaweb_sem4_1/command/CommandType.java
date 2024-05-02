package com.example.javaweb_sem4_1.command;

import com.example.javaweb_sem4_1.command.impl.*;
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
    VIEW_PROFILE(new ViewProfileCommand()),
    UPDATE_PASSWORD(new UpdatePasswordCommand()),
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
