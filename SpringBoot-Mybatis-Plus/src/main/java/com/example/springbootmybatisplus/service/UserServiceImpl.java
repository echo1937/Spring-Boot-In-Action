package com.example.springbootmybatisplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootmybatisplus.entity.UserDO;
import com.example.springbootmybatisplus.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements IUserService {
}
