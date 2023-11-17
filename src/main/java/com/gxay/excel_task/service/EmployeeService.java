package com.gxay.excel_task.service;

import com.gxay.excel_task.pojo.Employee;

import java.util.List;

public interface EmployeeService {

    int insert(Employee employee);

    int update(Employee employee);

    Employee getEmployeeById(Integer id);

    List<Employee> getAllEmployees();

    int delete(Integer id);

    List<Employee> getEmployeeByPage(int pageNo, int pageSize);
}
