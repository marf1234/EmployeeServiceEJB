package com.innowise.servlet;


import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;

/**
 * The ControllerServlet class is a servlet that handles HTTP requests and responses by implementing the
 * <p>
 * doGet, doPost, doPut and doDelete methods inherited from HttpServlet. This class is responsible for processing
 * <p>
 * requests to the "/command/*" URL pattern and producing JSON-formatted responses.
 * <p>
 * This class uses the @WebServlet annotation to indicate that it is a servlet and to specify the URL pattern it
 * <p>
 * should handle. It also uses the @MultipartConfig annotation to indicate that it can handle multipart/form-data
 * <p>
 * requests, and the @Produces and @Consumes annotations to indicate that it produces and consumes JSON data.
 * <p>
 * This class also uses the @EJB annotation to inject an instance of the Controller class, which is a stateless
 * <p>
 * session bean that provides business logic for the application.
 **/
@WebServlet(name = "ControllerServlet", urlPatterns = {"/command/*"})
@MultipartConfig
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ControllerServlet extends HttpServlet {

    @EJB
    private ModuleLayer.Controller controller;

    /**
     * Handles GET requests by calling the processRequest method.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if there is a problem processing the request
     * @throws IOException      if there is an I/O error while processing the request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles POST requests by calling the processRequest method.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if there is a problem processing the request
     * @throws IOException      if there is an I/O error while processing the request
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles PUT requests by calling the processRequest method.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if there is a problem processing the request
     * @throws IOException      if there is an I/O error while processing the request
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles DELETE requests by calling the processRequest method.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if there is a problem processing the request
     * @throws IOException      if there is an I/O error while processing the request
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes the HTTP request by setting the content type of the response to JSON and calling the appropriate
     * method of the injected Controller instance to handle the request.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if there is a problem processing the request
     * @throws IOException      if there is an I/O error while processing the request
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(MediaType.APPLICATION_JSON);
    }

}