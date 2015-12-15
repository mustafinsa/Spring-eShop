package com.mustafinsa.spring.eshop.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    @NotBlank
    @Size(min=6, max=15)
    @Pattern(regexp = "^[^\\s]*$")
    private String username;

    @NotNull
    @Pattern(regexp = "[^ ]*@[^ ]*\\.*")
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\S+$")
    @Size(min=8, max = 15)
    private String password;
    private boolean enabled = false;
    private String authority;
    private String name;

    public User() {
    }

    public User(String username, String email, String password, boolean enabled, String authority, String name) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.authority = authority;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", authority='" + authority + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (enabled != user.enabled) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (authority != null ? !authority.equals(user.authority) : user.authority != null) return false;
        return !(name != null ? !name.equals(user.name) : user.name != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
