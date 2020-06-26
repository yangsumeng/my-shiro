package com.yangsm.demo.myshiro.service.impl;

import com.yangsm.demo.myshiro.entity.User;
import com.yangsm.demo.myshiro.repository.UserRepository;
import com.yangsm.demo.myshiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
