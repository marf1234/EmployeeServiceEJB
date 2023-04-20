package com.innowise.commands;

import com.innowise.controller.RegistrationRequest;
import com.innowise.dto.UserDto;
import com.innowise.service.UserService;
import com.innowise.util.JsonConverter;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;

@Data
@Stateless
public class AddUserCommand implements Command {
    @EJB
    private UserService userService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RegistrationRequest registrationRequest = JsonConverter.toObject(request, RegistrationRequest.class);
        UserDto actual = userService.addUser(registrationRequest.toDTO());
        response.getWriter().write(JsonConverter.toJson(actual));
    }
}
