package ru.goryachev.multichief.auth.dto;

import java.util.Objects;

public class AuthenticationRequestDTO {

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthenticationRequestDTO)) return false;
        AuthenticationRequestDTO that = (AuthenticationRequestDTO) o;
        return name.equals(that.name) &&
                password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    @Override
    public String toString() {
        return "AuthenticationRequestDTO{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
