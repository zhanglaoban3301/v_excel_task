package com.gxay.excel_task.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Person implements Serializable {
    private Integer id;
    private String name;
    private String deptName;
    private Date birthDay;
    private char gender;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
}
