package com.innowise.controller;

import com.innowise.commands.Command;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@Stateless
public class Controller {
    private static final String COMMAND_SEPARATOR = "/";
    private final Map<String, Map<String, Command>> commandMap = new HashMap<>();

    @EJB
    private Command findAllEmployeesCommand;
    @EJB
    private Command addEmployeeCommand;
    @EJB
    private Command deleteEmployeeByIdCommand;
    @EJB
    private Command findEmployeeByIdCommand;
    @EJB
    private Command updateEmployeeCommand;

    @EJB
    private Command findAllDepartmentsCommand;
    @EJB
    private Command addDepartmentCommand;
    @EJB
    private Command deleteDepartmentCommand;
    @EJB
    private Command updateDepartmentCommand;

    @EJB
    private Command findAllUsersCommand;
    @EJB
    private Command addUserCommand;
    @EJB
    private Command deleteUserCommand;

    @EJB
    private Command logInCommand;

    @EJB
    private Command wrongCommand;

    @PostConstruct
    private void init() {
        Map<String, Command> employeesCommands = createEmployeeCommandsMap();
        commandMap.put("employees", employeesCommands);

        Map<String, Command> departmentsCommands = createDepartmentCommandsMap();
        commandMap.put("departments", departmentsCommands);

        Map<String, Command> usersCommands = createUsersCommandsMap();
        commandMap.put("users", usersCommands);

        Map<String, Command> wrongCommands = createWrongCommandsMap();
        commandMap.put("wrongCommand", wrongCommands);

        Map<String, Command> loginCommands = createLoginCommandsMap();
        commandMap.put("login", loginCommands);
    }

    private Map<String, Command> createEmployeeCommandsMap() {
        Map<String, Command> employeeCommands = new HashMap<>();
        employeeCommands.put("GET", findAllEmployeesCommand);
        employeeCommands.put("POST", addEmployeeCommand);
        employeeCommands.put("DELETE", deleteEmployeeByIdCommand);
        employeeCommands.put("PUT", updateEmployeeCommand);
        return employeeCommands;
    }

    private Map<String, Command> createDepartmentCommandsMap() {
        Map<String, Command> departmentCommands = new HashMap<>();
        departmentCommands.put("GET", findAllDepartmentsCommand);
        departmentCommands.put("POST", addDepartmentCommand);
        departmentCommands.put("DELETE", deleteDepartmentCommand);
        departmentCommands.put("PUT", updateDepartmentCommand);
        return departmentCommands;
    }

    private Map<String, Command> createUsersCommandsMap() {
        Map<String, Command> usersCommands = new HashMap<>();
        usersCommands.put("GET", findAllUsersCommand);
        usersCommands.put("POST", addUserCommand);
        usersCommands.put("DELETE", deleteUserCommand);
        return usersCommands;
    }

    private Map<String, Command> createWrongCommandsMap() {
        Map<String, Command> wrongCommands = new HashMap<>();
        wrongCommands.put("GET", wrongCommand);
        wrongCommands.put("POST", wrongCommand);
        wrongCommands.put("DELETE", wrongCommand);
        return wrongCommands;
    }

    private Map<String, Command> createLoginCommandsMap() {
        Map<String, Command> loginCommands = new HashMap<>();
        loginCommands.put("POST", logInCommand);
        return loginCommands;
    }

    public Command provideCommand(HttpServletRequest request) {
        String path = request.getRequestURI();
        String[] pathParts = path.split(COMMAND_SEPARATOR);
        String commandName = pathParts.length > 2 ? pathParts[3] : "wrongCommand";
        Map<String, Command> innerMap = commandMap.getOrDefault(commandName, new HashMap<>());
        return innerMap.getOrDefault(request.getMethod(), wrongCommand);
    }
}





