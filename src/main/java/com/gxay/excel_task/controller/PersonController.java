package com.gxay.excel_task.controller;

import com.gxay.excel_task.pojo.Person;
import com.gxay.excel_task.service.PersonService;
import com.gxay.excel_task.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/all")
    public List<Person> getAllPerson(){
        List<Person> allPerson = new ArrayList<Person>();
        try {
            allPerson = personService.getAllPerson();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return allPerson;
    }


    @PostMapping(value = "/getPersonByPage")
    public List<Person> getPersonByPage(@RequestBody Map map){
        return personService.getPersonByPage(Integer.parseInt(map.get("pageNo").toString()),Integer.parseInt(map.get("pageSize").toString()));
    }


    @PostMapping(value = "/insert")
    public int insert(@RequestBody Person person){
        return personService.insert(person);
    }


    @PostMapping(value = "/update")
    public int update(@RequestBody Person person){
        return personService.update(person);
    }


    @GetMapping(value = "/delete")
    public int delete(@RequestParam(value = "id") Integer id){
        return personService.delete(id);
    }
}
