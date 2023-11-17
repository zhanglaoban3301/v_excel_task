package com.gxay.excel_task.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.CacheNamespace;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CacheNamespace
public class Employee implements Serializable {
    private Integer id;
    private String name;
    private String deptName;
    private String deptCode;
    private BigDecimal single;
    private Integer num;
    private BigDecimal performance;
    private BigDecimal fine;
    private BigDecimal should;
    private BigDecimal reality;
    private char delFlag;

    public Map toMap(Employee employee){
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
        return employeeData;
    }
}
