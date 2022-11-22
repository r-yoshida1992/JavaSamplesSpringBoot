package com.example.javasamplesspringboot.controller;

import com.example.javasamplesspringboot.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Restコントローラーのサンプル
 */
@RestController
@RequiredArgsConstructor
public class IndexController {

    @NonNull
    private UserService userService;

    /**
     * 文字列「hello world」を返す
     *
     * @return "hello world"
     */
    @GetMapping("/")
    public String helloWorld() {
        return "hello world";
    }

    @GetMapping("/searchUser")
    public String searchUser(@RequestParam("id") String id) {
        return userService.searchUserById(id);
    }

}
