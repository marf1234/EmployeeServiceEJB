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

@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @EJB
    private EmployeeRepository employeeRepository;
    @EJB
    private EmployeeConverter converter;

    @Override
    @Transactional
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(converter::toDTO).collect(Collectors.toList());
//        return employeeRepository.findAll().stream().map(EmployeeMapper.INSTANCE::toDto)
//                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeDto saveEmployee(EmployeeDto employeeDTO) {
        return converter.toDTO(employeeRepository.save(converter.toEntity(employeeDTO)));
    }

    @Override
    @Transactional
    public EmployeeDto findById(Long id) {
        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new NoSuchRecordException(String.format("Employee with id=%s not found", id)))
                ;
        return converter.toDTO(employee);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

}
 // @Stateless
//public class EmployeeServiceImpl implements EmployeeService {
//
//    @EJB
//    private EmployeeRepository employeeRepository;
//
//    private final EmployeeConverter converter;
//
//    public EmployeeServiceImpl() {
//        this.converter = new EmployeeConverter();
//    }
//
//    @Override
//    public List<EmployeeDto> getAllEmployees() {
//        return employeeRepository.getAll().stream()
//                .map(converter::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public EmployeeDto saveEmployee(EmployeeDto employeeDTO) {
//        Employee employee = converter.convertToEntity(employeeDTO);
//        employeeRepository.save(employee);
//        return converter.convertToDto(employee);
//    }
//
//    @Override
//    public EmployeeDto getEmployeeById(Long id) {
//        Employee employee = employeeRepository.getById(id);
//        if (employee == null) {
//            throw new NoSuchRecordException(String.format("Employee with id=%s not found", id));
//        }
//        return converter.convertToDto(employee);
//    }
//
//    @Override
//    public void deleteEmployeeById(Long id) {
//        employeeRepository.deleteById(id);
//    }
//}
