package com.tubitakyte.studentmanagementsystem.User.entity.userTypes;

import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Entity
@DiscriminatorValue(value = "STUDENT")
public class Student extends User {

    @ManyToMany(mappedBy = "students")
    private List<Lessons> lessons=new ArrayList<>();

    public Student() {

    }

    public Student(String firstName, String email, String lastName, String password, String username) {
        super(firstName, email, lastName, password, username);
    }

    @Override
    public String toString() {
        return "Student{" +
                "lessons=" + lessons +
                '}';
    }
}
