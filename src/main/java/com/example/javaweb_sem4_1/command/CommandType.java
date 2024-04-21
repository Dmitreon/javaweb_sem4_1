package com.example.javaweb_sem4_1.command;

import com.example.javaweb_sem4_1.command.impl.*;

import java.util.Arrays;

public enum CommandType {
    ADD_USER {
        public Command create() {
            return new AddUserCommand();
        }
    },
    LOGIN {
        public Command create() {
            return new LoginCommand();
        }
    },
    LOGOUT {
        public Command create() {
            return new LogoutCommand();
        }
    },
    VIEW_USERS {
        public Command create() {
            return new ViewUsersCommand();
        }
    },
    REGISTER_USER {
        public Command create() {
            return new RegisterUserCommand();
        }
    },
    DEFAULT {
        public Command create() {
            return new DefaultCommand();
        }
    };

    public abstract Command create();

    public static Command define(String commandStr) {
        CommandType commandType;
        try {
            System.out.println("Available commands: " + Arrays.toString(CommandType.values()));
            commandType = CommandType.valueOf(commandStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("CommandType.define: Command not found: " + commandStr);
            commandType = DEFAULT;
        }
        return commandType.create();
    }
}