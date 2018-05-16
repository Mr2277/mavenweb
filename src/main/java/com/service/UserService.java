package com.service;

import com.bean.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User doUserLogin(User user);
}
