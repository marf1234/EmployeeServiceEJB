package com.innowise.commands;

import com.innowise.dto.UserProfile;
import com.innowise.service.UserService;
import com.innowise.util.JsonConverter;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;

/**
 * Represents a command for finding a user by ID.
 */
@Data
@Stateless
public class FindUserByIdCommand implements Command {

    @EJB
    private UserService userService;

    /**
     * Executes the command to find a user by ID.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException      if an input or output error occurs while the servlet is handling the request
     * @throws ServletException if the request could not be handled
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Long id = Long.valueOf(pathParts[pathParts.length - 1]);
        UserProfile userDTO = userService.findById(id);
        response.getWriter().write(JsonConverter.toJson(userDTO));
    }
}