package com.example.springbootmybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmybatisplus.entity.UserDO;
import com.example.springbootmybatisplus.pojo.SexEnum;
import com.example.springbootmybatisplus.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    /**
     * description: 新增
     */
    @RequestMapping("/save")
    public boolean save() {
        UserDO userDO = new UserDO();
        userDO.setNickname("大漂亮");
        userDO.setSex(SexEnum.MAN);
        userDO.setHobbies(Arrays.asList("游泳", "健身"));
        return userService.save(userDO);
    }

    /**
     * description: 修改
     */
    @RequestMapping("/update")
    public boolean update(@RequestParam String nickname, @RequestParam Long id) {
        UserDO userDO = new UserDO();
        userDO.setNickname(nickname);
        userDO.setId(id);
        return userService.updateById(userDO);
    }

    /**
     * description: 删除
     */
    @RequestMapping("/delete")
    public boolean delete(@RequestParam Long id) {
        UserDO userDO = new UserDO();
        userDO.setId(id);
        return userService.removeById(userDO);
    }

    /**
     * description: 列表
     */
    @RequestMapping("/list")
    public List<UserDO> list() {
        return userService.list();
    }

    /**
     * description: 分页列表
     */
    @RequestMapping("/page")
    public Page<UserDO> page(@RequestParam(defaultValue = "1") int current, @RequestParam(defaultValue = "5") int size) {
        return userService.page(new Page<>(current, size), new QueryWrapper<>(new UserDO()));
    }

}
