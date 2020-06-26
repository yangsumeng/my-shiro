package com.yangsm.demo.myshiro.service;


import com.yangsm.demo.myshiro.model.LoginResult;

public interface LoginService {

    LoginResult login(String userName, String password);

    void logout();
}