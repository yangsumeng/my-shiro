package com.yangsm.demo.myshiro;

import org.apache.shiro.crypto.hash.SimpleHash;

public class TestMain {

    /**
     * Shiro的加密  例子中
     * @param args
     */
    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        String credentials = "123456";
        //realm中设置
        String salt = "admin"+"8d78869f470951332959580424d4bf4f";
        Object obj = new SimpleHash(hashAlgorithmName, credentials, salt, 2);
        System.out.println(obj);
    }
}
