package com.example.javasamplesspringboot.service.impl;

import com.example.javasamplesspringboot.dao.UsersDao;
import com.example.javasamplesspringboot.entity.Users;
import com.example.javasamplesspringboot.service.UserService;
import com.example.javasamplesspringboot.utils.LogUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @NonNull
    private ObjectMapper objectMapper;

    public String searchUserById(String id) {
        Long parsedId = null;
        try {
            parsedId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            log.error("The id must be specified numerically.");
            return null;
        }
        Optional<Users> users = usersDao.findById(parsedId);
        if (users.isPresent()) {
            try {
                return objectMapper.writeValueAsString(users.get());
            } catch (JsonProcessingException e){
                log.error(e.getMessage());
                log.error(LogUtil.convertStackTraceToString(e));
                return null;
            }
        } else {
            log.error("This id does not exist.");
            return null;
        }
    }
}
