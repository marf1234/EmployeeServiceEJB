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

@Data
@Stateless
public class AddDepartmentCommand implements Command {

    /**

     The department service that is used to add the department to the system.
     */
    @EJB
    private DepartmentService departmentService;
    /**

     Handles the HTTP POST request to add a new department to the database.
     Converts the request body to a {@link DepartmentDto} object using {@link JsonConverter}.
     Calls the {@link DepartmentService#saveDepartment(DepartmentDto)} method to add the department to the database.
     Converts the resulting {@link DepartmentDto} object to JSON format using {@link JsonConverter}.
     Writes the JSON response to the HTTP response body.
     In case of an exception, writes an error message with the HTTP BAD REQUEST status to the response body.
     @param request the HTTP request object containing the request body
     @param response the HTTP response object to write the response to
     @throws IOException if an I/O error occurs while reading or writing the request or response body
     @throws ServletException if a servlet-specific error occurs
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            DepartmentDto departmentRequestDTO = JsonConverter.toObject(request, DepartmentDto.class);
            DepartmentDto departmentResponseDTO = departmentService.saveDepartment(departmentRequestDTO);
            response.getWriter().write(JsonConverter.toJson(departmentResponseDTO));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error adding department: " + e.getMessage());
        }
    }
}