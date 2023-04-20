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
    @EJB
    private DepartmentService departmentService;

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
