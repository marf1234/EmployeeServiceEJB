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

/**
 * Controller class is responsible for providing the appropriate Command object
 * <p>
 * based on the incoming HTTP request's URI and HTTP method.
 */
@Data
@NoArgsConstructor
@Stateless
public class Controller {
    /**
     * Separator used to split the incoming URI to extract the command name.
     */
    private static final String COMMAND_SEPARATOR = "/";
    /**
     * Map of all available commands, grouped by category.
     */
    private final Map<String, Map<String, Command>> commandMap = new HashMap<>();
    /**
     * Command objects for employee-related operations.
     */
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
    /**
     * Command objects for department-related operations.
     */
    @EJB
    private Command findAllDepartmentsCommand;
    @EJB
    private Command addDepartmentCommand;
    @EJB
    private Command deleteDepartmentCommand;
    @EJB
    private Command updateDepartmentCommand;
    /**
     * Command objects for user-related operations.
     */
    @EJB
    private Command findAllUsersCommand;
    @EJB
    private Command addUserCommand;
    @EJB
    private Command deleteUserCommand;
    /**
     * Command object for user login operation.
     */
    @EJB
    private Command logInCommand;
    /**
     * Command object for handling invalid commands.
     */
    @EJB
    private Command wrongCommand;

    /**
     * Command object for handling invalid commands.
     */
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

    /**
     * Creates a map of commands related to employees.
     *
     * @return a map of commands related to employees.
     */
    private Map<String, Command> createEmployeeCommandsMap() {
        Map<String, Command> employeeCommands = new HashMap<>();
        employeeCommands.put("GET", findAllEmployeesCommand);
        employeeCommands.put("POST", addEmployeeCommand);
        employeeCommands.put("DELETE", deleteEmployeeByIdCommand);
        employeeCommands.put("PUT", updateEmployeeCommand);
        return employeeCommands;
    }

    /**
     * Creates a map of commands related to departments.
     *
     * @return a map of commands related to departments.
     */
    private Map<String, Command> createDepartmentCommandsMap() {
        Map<String, Command> departmentCommands = new HashMap<>();
        departmentCommands.put("GET", findAllDepartmentsCommand);
        departmentCommands.put("POST", addDepartmentCommand);
        departmentCommands.put("DELETE", deleteDepartmentCommand);
        departmentCommands.put("PUT", updateDepartmentCommand);
        return departmentCommands;
    }

    /**
     * Creates a map of HTTP methods to their corresponding commands for user-related operations.
     *
     * @return A map containing the HTTP methods and their corresponding commands.
     */
    private Map<String, Command> createUsersCommandsMap() {
        Map<String, Command> usersCommands = new HashMap<>();
        usersCommands.put("GET", findAllUsersCommand);
        usersCommands.put("POST", addUserCommand);
        usersCommands.put("DELETE", deleteUserCommand);
        return usersCommands;
    }

    /**
     * Creates a Map of Commands for invalid requests.
     * <p>
     * This method creates a Map object of Commands that will be used to handle requests that do not match any valid
     * command in the system. The Commands in the map are all set to the same "wrongCommand" Command object.
     * </p>
     *
     * @return a Map of Commands for invalid requests.
     */
    private Map<String, Command> createWrongCommandsMap() {
        Map<String, Command> wrongCommands = new HashMap<>();
        wrongCommands.put("GET", wrongCommand);
        wrongCommands.put("POST", wrongCommand);
        wrongCommands.put("DELETE", wrongCommand);
        return wrongCommands;
    }

    /**
     * Creates a new HashMap of login commands where the key is the HTTP method
     * and the value is the Command instance for logging in.
     *
     * @return a Map<String, Command> containing the login commands
     */
    private Map<String, Command> createLoginCommandsMap() {
        Map<String, Command> loginCommands = new HashMap<>();
        loginCommands.put("POST", logInCommand);
        return loginCommands;
    }

    /**
     * Returns a Command object based on the HttpServletRequest object passed as argument.
     * This method extracts the command name from the request URI and looks it up in the command map.
     * If the command name is not found in the map, it returns the wrongCommand object.
     * If the command name is found, but the HTTP method is not supported for that command, it returns the wrongCommand object.
     *
     * @param request the HttpServletRequest object from which to extract the command name and HTTP method
     * @return a Command object that corresponds to the command name and HTTP method, or the wrongCommand object if the command name is not found or the HTTP method is not supported
     */
    public Command provideCommand(HttpServletRequest request) {
        String path = request.getRequestURI();
        String[] pathParts = path.split(COMMAND_SEPARATOR);
        String commandName = pathParts.length > 2 ? pathParts[3] : "wrongCommand";
        Map<String, Command> innerMap = commandMap.getOrDefault(commandName, new HashMap<>());
        return innerMap.getOrDefault(request.getMethod(), wrongCommand);
    }
}





