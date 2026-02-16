package net.javaguides.esm.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.esm.dto.EmployeeDto;
import net.javaguides.esm.entity.Employee;
import net.javaguides.esm.exception.ResourceNotFoundException;
import net.javaguides.esm.mapper.EmployeeMapper;
import net.javaguides.esm.repository.EmployeeRepository;
import net.javaguides.esm.service.EmployeeService;
import org.springframework.stereotype.Service;

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
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exits with given id: " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }
}
