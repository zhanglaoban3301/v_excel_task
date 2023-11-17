package com.gxay.excel_task.service;

import com.gxay.excel_task.pojo.Person;

import java.text.ParseException;
import java.util.List;

public interface PersonService {
    int insert(Person person);

    int update(Person person);

    Person getPersonById(Integer id);

    List<Person> getAllPerson() throws ParseException;

    int delete(Integer id);

    List<Person> getPersonByPage(int pageNo,int pageSize);
}
