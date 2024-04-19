package dev.hklm.fdbackend.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Userboard {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> userList = new ArrayList<>();

    protected Userboard() {}

    public Userboard(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return String.format(
                "Userboard[id=%d, userList='%s']",
                id, userList.toString());
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }
}
