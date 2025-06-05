package ru.yandex.prakticum.model;

public class UserData {
    private String name;
    private String email;
    private String password;

    public UserData(String name, String email, String password) {
        this.name = name;
        this.email = email.toLowerCase();
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
