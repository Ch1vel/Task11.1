package com.example.Task11.services;

import com.example.Task11.models.User;

import java.util.List;

public interface UserServiceInterface {
    boolean add(User user);
    void delete(int id);
    List<User> show();
    User show(int id);
    void update(User user);
}
