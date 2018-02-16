package example.Entity;

public class LoginForm {

    private String username;
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean authenticate() {
        return this.username.equals("admin") && this.password.equals("admin");
    }

    public String toString() {
        return "Username: " + this.username + " , " + "Password: " + this.password;
    }

}
