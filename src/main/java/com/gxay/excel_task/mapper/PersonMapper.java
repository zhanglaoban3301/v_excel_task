package com.gxay.excel_task.mapper;

import com.gxay.excel_task.pojo.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PersonMapper {
    @Select("select * from person where id= #{id}")
    @Results({
            @Result(property = "deptName", column = "dept_name"),
            @Result(property = "birthDay", column = "birth_day"),
            @Result(property = "phoneNumber", column = "phone_number"),
    })
    Person getPersonById(Integer id);

    @Select("select * from person ")
    @Results({
            @Result(property = "deptName", column = "dept_name"),
            @Result(property = "birthDay", column = "birth_day"),
            @Result(property = "phoneNumber", column = "phone_number"),
    })
    List<Person> getAllPerson();

    @Insert("insert into person(name, dept_name, birth_day, gender, email, phone_number, address, city, state)" +
            "values (#{name},#{deptName},#{birthDay},#{gender},#{email},#{phoneNumber},#{address},#{city})")
    int insert(Person person);

    @Update("update person set name=#{name},dept_name=#{deptName},birth_day=#{birthDay},gender=#{gender}," +
            "email=#{email},phone_number=#{phoneNumber},address=#{address},city=#{city}")
    int update(Person person);

    @Delete("delete from person where id= #{id}")
    int deletePersonById(Integer id);

    @Select("select * from person limit #{pageNo},#{pageSize}")
    @Results({
            @Result(property = "deptName", column = "dept_name"),
            @Result(property = "birthDay", column = "birth_day"),
            @Result(property = "phoneNumber", column = "phone_number"),
    })
    List<Person> getPersonByPage(int pageNo,int pageSize);
}
