package com.innowise.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**

 Represents a command that can be executed in response to an HTTP request.

 Implementations of this interface should provide a concrete implementation

 of the execute method to handle the specific logic for the command.
 */
public interface Command {

    /**

     Executes the command based on the given HTTP request and response objects.
     Implementations should provide specific logic for handling the request
     and generating an appropriate response.
     @param request the HTTP request object
     @param response the HTTP response object
     @throws IOException if an I/O error occurs while handling the request or response
     @throws ServletException if an error occurs while processing the request or response
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}