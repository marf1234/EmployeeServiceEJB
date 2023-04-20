package com.innowise.commands;

import com.innowise.dto.DepartmentDto;
import com.innowise.service.DepartmentService;
import com.innowise.util.JsonConverter;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;
import java.util.List;

/**
 * Command that retrieves all departments from the database and sends them to the client as JSON.
 */
@Data
@Stateless
public class FindAllDepartmentsCommand implements Command {
    @EJB
    private DepartmentService departmentService;

    /**
     * Retrieves all departments from the database and sends them to the client as JSON.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @throws IOException      if an I/O error occurs while writing to the response output stream
     * @throws ServletException if the request could not be handled
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<DepartmentDto> allDepartments = departmentService.findAll();
        response.getWriter().write(JsonConverter.toJson(allDepartments));
    }
}