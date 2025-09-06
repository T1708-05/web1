package murach.business;

import java.io.Serializable;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String email;

    public User() {}
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String v) { this.firstName = v; }

    public String getLastName() { return lastName; }
    public void setLastName(String v) { this.lastName = v; }

    public String getEmail() { return email; }
    public void setEmail(String v) { this.email = v; }
}
