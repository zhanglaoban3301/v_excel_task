package com.gxay.excel_task.service.impl;

import com.gxay.excel_task.mapper.PersonMapper;
import com.gxay.excel_task.pojo.Person;
import com.gxay.excel_task.service.PersonService;
import com.gxay.excel_task.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public int insert(Person person) {
        return personMapper.insert(person);
    }
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public int update(Person person) {
        return personMapper.update(person);
    }

    @Override
    public Person getPersonById(Integer id) {
        return personMapper.getPersonById(id);
    }

    @Override
    public List<Person> getAllPerson() throws ParseException {
        String redisKey = "personList";
        // 检查 Redis 中是否存在 personList Hash
        if (!redisUtils.hasKey(redisKey)) {
            List<Person> personList = personMapper.getAllPerson();
            // 将 Person 对象存储为 Redis Hash
            for (Person person : personList) {
                String hashKey = "person:" + person.getId();  // 使用 ID 作为唯一标识符
                Map<String, String> personData = new HashMap<>();
                personData.put("name", person.getName());
                personData.put("deptName", person.getDeptName());
                personData.put("birthDay", dateFormat.format(person.getBirthDay()));
                personData.put("gender", String.valueOf(person.getGender()));
                personData.put("email", person.getEmail());
                personData.put("phoneNumber", person.getPhoneNumber());
                personData.put("address", person.getAddress());
                personData.put("city", person.getCity());
                personData.put("state", person.getState());
                redisUtils.hset(redisKey, hashKey, personData);
            }
        }
        // 获取所有 Person 对象
        List<Person> result = new ArrayList<>();
        Map<Object, Object> hashEntries = redisUtils.hmget(redisKey);
        for (Object entryKey : hashEntries.keySet()) {
            Map<Object, Object> personData = (Map<Object, Object>) hashEntries.get(entryKey);
            Person person = new Person();
            String[] split = entryKey.toString().split(":");
            String id = split[1];
            person.setId(Integer.parseInt(id));
            person.setName(personData.get("name").toString());
            person.setDeptName(personData.get("deptName").toString());
            person.setBirthDay(dateFormat.parse(personData.get("birthDay").toString()));
            person.setGender(personData.get("gender").toString().charAt(0));
            person.setEmail(personData.get("email").toString());
            person.setPhoneNumber(personData.get("phoneNumber").toString());
            person.setAddress(personData.get("address").toString());
            person.setCity(personData.get("city").toString());
            person.setState(personData.get("state").toString());
            result.add(person);
        }

        // 对 result 列表根据 id 升序排序
        Collections.sort(result, new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                // 根据 id 属性升序排序
                return Integer.compare(person1.getId(), person2.getId());
            }
        });
        return result;
       // return personMapper.getAllPerson();
    }


    @Override
    public int delete(Integer id) {
        return personMapper.deletePersonById(id);
    }

    @Override
    public List<Person> getPersonByPage(int pageNo, int pageSize) {
        return personMapper.getPersonByPage(pageNo,pageSize);
    }
}
