package com.yangsm.demo.myshiro.repository;


import com.yangsm.demo.myshiro.entity.User;

public interface UserRepository extends BaseRepository<User,Long> {
    User findByUserName(String userName);
}