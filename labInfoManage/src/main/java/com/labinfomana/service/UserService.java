package com.labinfomana.service;


import com.labinfomana.entity.User;

public interface UserService {
    User findByName(String name);
    boolean verifyUser(User user);

    void insertUser(User user);
}
