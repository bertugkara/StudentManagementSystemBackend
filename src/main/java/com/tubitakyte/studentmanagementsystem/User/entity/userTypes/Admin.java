package com.tubitakyte.studentmanagementsystem.User.entity.userTypes;

import com.tubitakyte.studentmanagementsystem.Announcements.entity.Announcement;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Getter
@ToString
@Entity
@DiscriminatorValue(value="ADMIN")
public class Admin extends User {

    @OneToMany
    private List<Announcement> announcements;

    public Admin(){

    }
    public Admin(String firstName, String email, String lastName, String password, String username) {
        super(firstName, email, lastName, password, username);
    }
}
