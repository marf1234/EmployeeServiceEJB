package com.innowise.commands;

import com.innowise.service.DepartmentService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;

/**
 * Command handler for deleting a department by ID.
 */
@Data
@Stateless
public class DeleteDepartmentCommand implements Command {
    @EJB
    private DepartmentService departmentService;

    /**
     * Executes the command by deleting the department with the given ID and sending a response to the client.
     *
     * @param request  the HTTP request from the client
     * @param response the HTTP response to send to the client
     * @throws IOException      if an I/O error occurs while reading or writing the request or response
     * @throws ServletException if the servlet encounters an error while processing the request
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Long id = Long.valueOf(pathParts[pathParts.length - 1]);
        departmentService.deleteById(id);

        response.getWriter().write("Department with id " + id + " was deleted");
    }

    /**
     * Extracts a request parameter from the given URI.
     *
     * @param uri the URI to extract the parameter from
     * @return the extracted parameter
     */
    private String extractRequestParam(final String uri) {
        final var start = uri.lastIndexOf('/') - 1;
        return uri.substring(start);
    }
}
