package com.frontend;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Admin {

    private String adminName;
    private List<User> users;

    public Admin(String adminName) {
        this.adminName = adminName;
        this.users = new ArrayList<>();
    }

    public User getUser(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void createUser(String username, String password, String email) {
        if (getUser(username) == null) {
            User newUser = new User(username, password, email);
            users.add(newUser);
        } else {
        }
    }

    public List<User> searchUsers(String keyword) {
        return users.stream()
                .filter(user -> user.getUsername().contains(keyword))
                .collect(Collectors.toList());
    }

    public void deleteUser(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}

