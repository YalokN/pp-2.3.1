package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void delUser(Long userId);

    void changeUser(User user);

    List<User> listUser();
}
