package com.service.imp;

import com.bean.User;
import com.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    @Override
    public User doUserLogin(User user) {
        return user;
    }
}
