package com.example.baitap11;

public class User {
    String login,avatar_url, subsriptions_url;
    public User(){

    }

    public User(String login, String avatar_url, String subsriptions_url) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.subsriptions_url = subsriptions_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", subsriptions_url='" + subsriptions_url + '\'' +
                '}';
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getSubsriptions_url() {
        return subsriptions_url;
    }

    public void setSubsriptions_url(String subsriptions_url) {
        this.subsriptions_url = subsriptions_url;
    }
}
