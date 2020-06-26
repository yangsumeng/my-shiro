package com.yangsm.demo.myshiro.service;


import com.yangsm.demo.myshiro.entity.User;

public interface UserService {
    User findByUserName(String userName);
}
