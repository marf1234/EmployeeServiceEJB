package com.innowise.commands;

import com.innowise.dto.EmployeeDto;
import com.innowise.service.EmployeeService;
import com.innowise.util.JsonConverter;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;
import java.util.List;

@Data
@Stateless
public class FindAllEmployeesCommand implements Command {

    /**
     * An instance of the {@link EmployeeService} to perform business logic operations on employees.
     */
    @EJB
    private EmployeeService employeeService;

    /**
     * Executes the command by retrieving all employees, converting them to a list of DTOs,
     * and writing the JSON representation of the list to the response's output stream.
     *
     * @param request  the HTTP request object
     * @param response the HTTP response object
     * @throws IOException      if there is an error writing the response
     * @throws ServletException if there is an error handling the request
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<EmployeeDto> allEmployees = employeeService.findAll();
        response.getWriter().write(JsonConverter.toJson(allEmployees));
    }
}