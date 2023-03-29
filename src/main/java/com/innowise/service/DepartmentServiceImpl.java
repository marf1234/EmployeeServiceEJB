package com.innowise.service;

import com.innowise.dto.DepartmentConverter;
import com.innowise.dto.DepartmentDto;
import com.innowise.entity.Department;
import com.innowise.repository.DepartmentRepository;
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
public class DepartmentServiceImpl implements DepartmentService {
    @EJB
    private DepartmentRepository departmentRepository;
    @EJB
    private DepartmentConverter converter;



    @Override
    public DepartmentDto saveDepartment(DepartmentDto department) {
        return converter.toDTO(departmentRepository.save(converter.toEntity(department)));
    }

    @Transactional
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto findById(Long id) {
        Department department = departmentRepository.findById(id)
//                .orElseThrow(() ->
//                        new NoSuchRecordException(String.format("Department with id=%s not found", id))
//                )
                ;
        return converter.toDTO(department);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
//        if (!departmentRepository.existsById(id)) {
//            throw new NoSuchRecordException
//                    (String.format("Department with id=%s not found for deleting", id));
//        }
        departmentRepository.deleteById(id);
    }
}


//@Stateless
//public class DepartmentServiceImpl implements DepartmentService {
//
//    @EJB
//    private DepartmentRepository departmentRepository;
//
//    private final DepartmentConverter converter;
//
//    public DepartmentServiceImpl() {
//        this.converter = new DepartmentConverter();
//    }
//
//    @Override
//    public DepartmentDto saveDepartment(DepartmentDto departmentDTO) {
//        Department department = converter.convertToEntity(departmentDTO);
//        departmentRepository.save(department);
//        return converter.convertToDto(department);
//    }
//
//    @Override
//    public List<DepartmentDto> getAllDepartments() {
//        return departmentRepository.getAll()
//                .stream()
//                .map(converter::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public DepartmentDto getDepartmentById(Long id) {
//        Department department = departmentRepository.getById(id);
//        if (department == null) {
//            throw new NoSuchRecordException(String.format("Department with id=%s not found", id));
//        }
//        return converter.convertToDto(department);
//    }
//
//    @Override
//    public void deleteDepartmentById(Long id) {
//        departmentRepository.deleteById(id);
//    }
//}