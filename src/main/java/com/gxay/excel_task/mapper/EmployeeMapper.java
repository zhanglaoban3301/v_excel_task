package com.gxay.excel_task.mapper;

import com.gxay.excel_task.pojo.Employee;
import com.gxay.excel_task.pojo.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    @Select("select * from Employee where id= #{id}")
    @Results({
            @Result(property = "deptName", column = "dept_name"),
            @Result(property = "deptCode", column = "dept_code")
    })
    Employee getEmployeeById(Integer id);

    @Select("select * from Employee ")
    @Results({
            @Result(property = "deptName", column = "dept_name"),
            @Result(property = "deptCode", column = "dept_code")
    })
    @Options(useCache = true)
    List<Employee> getAllEmployees();

    @Insert("insert into Employee(name, dept_name, dept_code, single, num, performance, fine, should, reality)" +
            "values (#{name},#{deptName},#{deptCode},#{single},#{num},#{performance},#{fine},#{should},#{reality})")
    int insert(Employee employee);

    @Update("update Employee set single=#{single}," +
            "num=#{num},performance=#{performance},fine=#{fine},should=#{should},reality=#{reality} where id = #{id}")
    int update(Employee employee);

    @Delete("delete from Employee where id= #{id}")
    int deleteEmployeeById(Integer id);

    @Select("select * from Employee order by id limit #{pageNo},#{pageSize} ")
    @Results({
            @Result(property = "deptName", column = "dept_name"),
            @Result(property = "deptCode", column = "dept_code")
    })
    List<Employee> getEmployeeByPage(int pageNo,int pageSize);
}
