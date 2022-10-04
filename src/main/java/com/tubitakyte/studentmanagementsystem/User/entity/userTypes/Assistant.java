package com.tubitakyte.studentmanagementsystem.User.entity.userTypes;

import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;


@Getter
@ToString
@Entity
@DiscriminatorValue(value="ASSISTANT")
public class Assistant extends User {

    @ManyToMany(mappedBy = "assistants", cascade = CascadeType.PERSIST)
    private List<Lessons> lessonsAttended=new ArrayList<>();

    public Assistant(){

    }
    public Assistant(String firstName, String email, String lastName, String password, String username) {
        super(firstName, email, lastName, password, username);
    }
}
