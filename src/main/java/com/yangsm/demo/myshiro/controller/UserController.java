package com.yangsm.demo.myshiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    //用户查询
    @GetMapping("/userList")
    @RequiresPermissions("user:view")//权限管理;
    public String userInfo(){
        return "userList";
    }

    //用户添加
    @GetMapping("/userAdd")
    @RequiresPermissions("user:add")//权限管理;
    public String userInfoAdd(){
        return "userAdd";
    }

    //用户删除
    @GetMapping("/userDel")
    @RequiresPermissions("user:del")//权限管理;
    public String userDel(){
        return "userDel";
    }

    //用户删除
    @GetMapping("/deleteall")
    @RequiresPermissions("deleteall")//权限管理;
    public String deleteall(){
        return "deleteall";
    }
}
