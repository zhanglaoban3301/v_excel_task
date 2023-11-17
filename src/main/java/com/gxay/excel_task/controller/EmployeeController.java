package com.gxay.excel_task.controller;

import com.gxay.excel_task.pojo.Employee;
import com.gxay.excel_task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/all")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping(value = "/getEmployeeByPage")
    public List<Employee> getPersonByPage(@RequestBody Map map){
        return employeeService.getEmployeeByPage(Integer.parseInt(map.get("pageNo").toString()),Integer.parseInt(map.get("pageSize").toString()));
    }

    @PostMapping(value = "/insert")
    public int insert(@RequestBody Employee employee){
        return employeeService.insert(employee);
    }


    @PostMapping(value = "/update")
    public int update(@RequestBody Employee employee){
        return employeeService.update(employee);
    }


    @GetMapping(value = "/delete")
    public int delete(@RequestParam(value = "id") Integer id){
        return employeeService.delete(id);
    }
}
