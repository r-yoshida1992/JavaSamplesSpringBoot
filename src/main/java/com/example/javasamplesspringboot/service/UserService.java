package com.example.javasamplesspringboot.service;

import com.example.javasamplesspringboot.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * IDからユーザーを取得する
     * @return Users
     */
    public String searchUserById(String id);
}
