package com.example.javasamplesspringboot.dao;

import com.example.javasamplesspringboot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<Users, Long> {

}
