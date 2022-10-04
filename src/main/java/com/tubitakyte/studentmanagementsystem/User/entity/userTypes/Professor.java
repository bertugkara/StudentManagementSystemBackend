package com.tubitakyte.studentmanagementsystem.User.entity.userTypes;

import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Entity
@DiscriminatorValue(value = "PROFESSOR")
public class Professor extends User {

    @OneToMany(mappedBy = "instructor")
    private List<Lessons> lessonsHasBeenAttending=new ArrayList<>();

    public Professor() {

    }

    public Professor(String firstName, String email, String lastName, String password, String username) {
        super(firstName, email, lastName, password, username);
    }
}
