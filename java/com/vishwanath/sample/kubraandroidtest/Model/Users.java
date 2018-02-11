package com.vishwanath.sample.kubraandroidtest.Model;

/**
 * Created by vishwanath on 2/11/2018.
 */

public class Users {
    String id;
    String name;
    String username;
    String email;
    UserAddress address;

    public Users(String id, String name, String username, String email, UserAddress address) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.address = address;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserAddress getAddress() {
        return address;
    }

    public void setAddress(UserAddress address) {
        this.address = address;
    }

}
