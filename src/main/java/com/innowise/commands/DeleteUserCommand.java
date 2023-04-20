package com.innowise.commands;

import com.innowise.service.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;

/**
 * Command implementation for deleting a user from the system.
 */
@Data
@Stateless
public class DeleteUserCommand implements Command {
    @EJB
    private UserService userService;

    /**
     * Executes the command to delete a user by ID.
     *
     * @param request  the HTTP request object
     * @param response the HTTP response object
     * @throws IOException      if an I/O error occurs
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Long id = Long.valueOf(pathParts[pathParts.length - 1]);
        userService.deleteById(id);

        response.getWriter().write("User with id " + id + " was deleted");
    }
}