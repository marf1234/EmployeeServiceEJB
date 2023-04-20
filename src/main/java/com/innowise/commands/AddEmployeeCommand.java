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

/**
 * A stateless command that adds an employee to the system.
 */
@Data
@Stateless
public class AddEmployeeCommand implements Command {
    @EJB
    private EmployeeService employeeService;

    /**
     * Adds an employee to the system by deserializing the request body into an EmployeeDto,
     * passing it to the EmployeeService to save, and serializing the resulting EmployeeDto
     * into the response body.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @throws IOException      if an I/O error occurs while reading or writing to the request or response
     * @throws ServletException if an error occurs while processing the servlet request
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EmployeeDto employee = JsonConverter.toObject(request, EmployeeDto.class);
        EmployeeDto actual = employeeService.saveEmployee(employee);
        response.getWriter().write(JsonConverter.toJson(actual));
    }
}