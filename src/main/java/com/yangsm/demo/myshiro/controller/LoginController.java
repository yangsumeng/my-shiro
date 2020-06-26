package com.yangsm.demo.myshiro.controller;

import com.yangsm.demo.myshiro.entity.User;
import com.yangsm.demo.myshiro.model.LoginResult;
import com.yangsm.demo.myshiro.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {
    @Resource
    private LoginService loginService;

    @GetMapping(value = "/login")
    public String login() {
        return "登录页";
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody User user) {
        System.out.println("login()");
        String userName = user.getUserName();
        String password = user.getPassword();
        LoginResult loginResult = loginService.login(userName,password);
        if(loginResult.isLogin()){
            return "登录成功";
        } else {
            return "登录失败：" + loginResult.getResult();
        }
    }

    @GetMapping(value = "/index")
    public String index() {
        return "主页";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "退出";
    }

    @GetMapping("/403")
    public String unauthorizedRole(){
        return "没有权限";
    }
}
