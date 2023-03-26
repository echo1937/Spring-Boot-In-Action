package com.example.springbootmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.example.springbootmybatisplus.entity.UserDO;
import com.example.springbootmybatisplus.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class SpringBootMybatisPlusApplicationTests {
    @Autowired
    private UserServiceImpl userService;

    @Test
    void contextLoads() {
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        SFunction<UserDO, LocalDateTime> createTime = UserDO::getCreateTime;
        queryWrapper.select(createTime).groupBy(createTime).orderByDesc(createTime).last("limit 1 offset 1");
        UserDO one = userService.getOne(queryWrapper);

        if (Objects.equals(one, null) || Objects.equals(one.getCreateTime(), null)) {
            return;
        }

        List<UserDO> list = userService.list(new LambdaQueryWrapper<UserDO>().eq(UserDO::getCreateTime, one.getCreateTime()));
        System.out.println("list的结果为: " + list);
        System.out.println("list的size为: " + list.size());
    }

}
