package com.wavemaker.learning.service;

import com.wavemaker.learning.Models.User;

/**
 * Created by venkateswarluk on 19/7/16.
 */
public class UserService {
    private static final User user = new User();
    public User loadUser(String userName)
    {
        return user;
    }
}
