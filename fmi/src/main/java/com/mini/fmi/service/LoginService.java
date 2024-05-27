package com.mini.fmi.service;

import com.mini.fmi.dao.UserDao;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserDao userDao;

    public LoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean loginCheck(String id, String pw) {
        return userDao.loginCheck(id, pw);
    }
}
