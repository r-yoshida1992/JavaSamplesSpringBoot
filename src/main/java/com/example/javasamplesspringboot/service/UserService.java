package com.example.javasamplesspringboot.service;

import com.example.javasamplesspringboot.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * 指定されたIdからユーザーを検索し、jsonで返す
     * @param id String
     * @return json
     */
    public String searchUserById(String id);
}
