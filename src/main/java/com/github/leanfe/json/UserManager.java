package com.github.leanfe.json;

import com.github.leanfe.App;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class UserManager {

    @Getter
    private UserData userData;

    private final UserParser userParser;

    public UserManager() {
        userParser = new UserParser(App.getInstance(), this);

        userData = userParser.fromJsonFile();
    }

    public void addUser(String username) {
        User user = new User();
        user.setExp(0);
        user.setUsername(username);

        userData.getUsers().add(user);
    }

    public User getUser(String username) {
        AtomicReference<User> user = new AtomicReference<>();
        for (User e : userData.getUsers()) {
            if (e.getUsername().equalsIgnoreCase(username)) {
                user.set(e);
                break;
            }
        }

        return user.get();
    }

    public void removeUser(String username) {
        AtomicInteger id = new AtomicInteger();
        userData.getUsers().forEach(e-> {
            id.getAndIncrement();
            if (e.getUsername().equalsIgnoreCase(username)) {
                return;
            }
        });

        userData.getUsers().remove(id.get());
    }

    public void changeUserExp(String username, int xp) {
        User usr = getUser(username);
        usr.setExp(xp);
    }

    public void push() {
        userParser.toJsonFile(userData);
    }

    public void reload() {
        userData = userParser.fromJsonFile();
    }
}
