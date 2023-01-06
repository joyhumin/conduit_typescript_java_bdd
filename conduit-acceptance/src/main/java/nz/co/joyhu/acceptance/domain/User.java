package nz.co.joyhu.acceptance.domain;

import java.util.Objects;

public class User {
    private String email;
    private String password;
    private String username;
    private String bio;

    public User() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(username, user.username) && Objects.equals(bio, user.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, username, bio);
    }

    @Override
    public String toString() {
        return "User{" +
            "email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", username='" + username + '\'' +
            ", bio='" + bio + '\'' +
            '}';
    }
}
