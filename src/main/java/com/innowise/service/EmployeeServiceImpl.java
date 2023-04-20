package com.innowise.service;

import com.innowise.dto.EmployeeConverter;
import com.innowise.dto.EmployeeDto;
import com.innowise.entity.Employee;
import com.innowise.repository.EmployeeRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the {@link EmployeeService} interface and provides methods for performing CRUD operations
 * <p>
 * on the employee repository.
 */
@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @EJB
    private EmployeeRepository employeeRepository;
    @EJB
    private EmployeeConverter converter;

    /**
     * Retrieves all employees from the repository.
     *
     * @return a list of {@link EmployeeDto} containing all employees in the repository.
     */
    @Override
    @Transactional
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(converter::toDTO).collect(Collectors.toList());
    }

    /**
     * Saves the given employee into the repository.
     *
     * @param employeeDTO an instance of {@link EmployeeDto} containing the employee's data to be saved.
     * @return an instance of {@link EmployeeDto} containing the saved employee's data.
     */
    @Override
    @Transactional
    public EmployeeDto saveEmployee(EmployeeDto employeeDTO) {
        return converter.toDTO(employeeRepository.save(converter.toEntity(employeeDTO)));
    }

    /**
     * Retrieves the employee with the given id from the repository.
     *
     * @param id the id of the employee to be retrieved.
     * @return an instance of {@link EmployeeDto} containing the retrieved employee's data.
     */
    @Override
    @Transactional
    public EmployeeDto findById(Long id) {
        Employee employee = employeeRepository.findById(id);
        return converter.toDTO(employee);
    }

    /**
     * Deletes the employee with the given id from the repository.
     *
     * @param id the id of the employee to be deleted.
     */
    @Transactional
    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}

