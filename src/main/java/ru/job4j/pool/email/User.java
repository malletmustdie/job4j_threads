package ru.job4j.pool.email;

public class User {

    private final String username;
    private final String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("User{%s, %s}", username, email);
    }

}
