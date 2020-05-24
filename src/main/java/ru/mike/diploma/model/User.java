package ru.mike.diploma.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends AbstractNamedEntity {

    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    protected List<Vote> votes;

    public User() {
    }

    public User(String email, Role role, String password) {
        this(null,null,email,role,password);
        /*this.email = email;
        this.role = role;
        this.password = password;*/
    }

    public User(Integer id, String name,  String email, Role role, String password) {
        super(name, id);
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public User(String name, String email,  Role role,String password) {
        this(null,name,email,role,password);
     /*   super(name, null);
        this.email = email;
        this.role = role;
        this.password = password;*/

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}