package com.gxay.excel_task.service.impl;

import com.gxay.excel_task.mapper.EmployeeMapper;
import com.gxay.excel_task.pojo.Employee;
import com.gxay.excel_task.service.EmployeeService;
import com.gxay.excel_task.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RedisUtils redisUtils;
    private static final String redisKey = "employeeList";
    @Override
    public int insert(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public int update(Employee employee) {
        redisUtils.hset("employee:"+employee.getId(),"single",employee.getSingle());
        redisUtils.hset("employee:"+employee.getId(),"num",employee.getNum());
        redisUtils.hset("employee:"+employee.getId(),"performance",employee.getPerformance());
        redisUtils.hset("employee:"+employee.getId(),"fine",employee.getFine());
        redisUtils.hset("employee:"+employee.getId(),"should",employee.getShould());
        redisUtils.hset("employee:"+employee.getId(),"reality",employee.getReality());
        redisUtils.hset(redisKey,"employee:"+employee.getId(),employee.toMap(employee));
        return employeeMapper.update(employee);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeMapper.getEmployeeById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        // 检查 Redis 中是否存在 employeeList Hash
        if (!redisUtils.hasKey(redisKey)) {
            List<Employee> employeeList = employeeMapper.getAllEmployees();
            // 将 Employee 对象存储为 Redis Hash
            for (Employee employee : employeeList) {
                String hashKey = "employee:" + employee.getId();  // 使用 ID 作为唯一标识符
                Map<String, Object> employeeData = new HashMap<>();
                employeeData.put("name", employee.getName());
                employeeData.put("deptName", employee.getDeptName());
                employeeData.put("deptCode", employee.getDeptCode());
                employeeData.put("single", employee.getSingle());
                employeeData.put("num", employee.getNum());
                employeeData.put("performance", employee.getPerformance());
                employeeData.put("fine", employee.getFine());
                employeeData.put("should", employee.getShould());
                employeeData.put("reality", employee.getReality());
                employeeData.put("delFlag", employee.getDelFlag());
                redisUtils.hset(redisKey, hashKey, employeeData);
            }
        }
        // 获取所有 Employee 对象
        List<Employee> result = new ArrayList<>();
        Map<Object, Object> hashEntries = redisUtils.hmget(redisKey);
        for (Object entryKey : hashEntries.keySet()) {
            Map<Object, Object> employeeData = (Map<Object, Object>) hashEntries.get(entryKey);
            Employee employee = new Employee();
            String[] split = entryKey.toString().split(":");
            String id = split[1];
            employee.setId(Integer.parseInt(id));
            employee.setName(employeeData.get("name").toString());
            employee.setDeptName(employeeData.get("deptName").toString());
            employee.setDeptCode(employeeData.get("deptCode").toString());
            employee.setSingle((BigDecimal) employeeData.get("single"));
            employee.setNum((Integer) employeeData.get("num"));
            employee.setPerformance((BigDecimal)employeeData.get("performance"));
            employee.setFine((BigDecimal) employeeData.get("fine"));
            employee.setShould((BigDecimal) employeeData.get("should"));
            employee.setReality((BigDecimal) employeeData.get("reality"));
            employee.setDelFlag((Character) employeeData.get("delFlag"));
            result.add(employee);
        }
        // 对 result 列表根据 id 升序排序
        Collections.sort(result, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                // 根据 id 属性升序排序
                return Integer.compare(employee1.getId(), employee2.getId());
            }
        });
        return result;
        //return employeeMapper.getAllEmployees();
    }


    @Override
    public int delete(Integer id) {
        return employeeMapper.deleteEmployeeById(id);
    }

    @Override
    public List<Employee> getEmployeeByPage(int pageNo, int pageSize) {
        return employeeMapper.getEmployeeByPage(pageNo,pageSize);
    }
}
