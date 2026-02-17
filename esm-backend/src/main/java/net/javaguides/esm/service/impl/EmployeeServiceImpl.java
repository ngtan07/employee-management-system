package net.javaguides.esm.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.esm.dto.EmployeeDto;
import net.javaguides.esm.entity.Employee;
import net.javaguides.esm.exception.ResourceNotFoundException;
import net.javaguides.esm.mapper.EmployeeMapper;
import net.javaguides.esm.repository.EmployeeRepository;
import net.javaguides.esm.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exists with given id: " + employeeId)
        );
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());

        Employee updateEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);
    }
}
