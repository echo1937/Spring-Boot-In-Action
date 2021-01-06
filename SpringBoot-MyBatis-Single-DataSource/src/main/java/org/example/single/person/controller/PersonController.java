package org.example.single.person.controller;

import org.example.single.person.entity.Person;
import org.example.single.person.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Person)表控制层
 */
@RestController
public class PersonController {
    /**
     * 服务对象
     */
    @Resource
    private PersonService personService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("person/selectOne")
    public Person selectOne(Long id) {
        return this.personService.queryById(id);
    }

}