package com.example.javasamplesspringboot.service.impl;

import com.example.javasamplesspringboot.dao.UsersDao;
import com.example.javasamplesspringboot.entity.Users;
import com.example.javasamplesspringboot.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @NonNull
    private UsersDao usersDao;

    public String searchUserById(String id) {
        Long parsedId = null;
        try {
            parsedId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            log.warn("The id must be specified numerically.");
            return null;
        }
        Optional<Users> users = usersDao.findById(parsedId);
        if (users.isPresent()) {
            return users.get().getName();
        } else {
            log.warn("This id does not exist.");
            return null;
        }
    }
}
