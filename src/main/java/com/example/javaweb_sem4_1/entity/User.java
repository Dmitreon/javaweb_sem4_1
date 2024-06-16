package com.example.javaweb_sem4_1.entity;

import java.util.Objects;

public class User extends AbstractEntity {
    private int id;
    private String username;
    private String password;
    private String email;
    private Integer predefinedImageId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPredefinedImageId() {
        return predefinedImageId;
    }

    public void setPredefinedImageId(Integer predefinedImageId) {
        this.predefinedImageId = predefinedImageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(predefinedImageId, user.predefinedImageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, predefinedImageId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", predefinedImageId=" + predefinedImageId +
                '}';
    }
}
