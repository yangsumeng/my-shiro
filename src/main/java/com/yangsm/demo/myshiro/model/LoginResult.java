package com.yangsm.demo.myshiro.model;

import lombok.Data;

@Data
public class LoginResult {
    private boolean isLogin = false;
    private String result;
}
