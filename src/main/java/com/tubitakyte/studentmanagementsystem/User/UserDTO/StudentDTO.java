package com.tubitakyte.studentmanagementsystem.User.UserDTO;

import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.LessonDTO;
import com.tubitakyte.studentmanagementsystem.Role.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
public class StudentDTO extends UserDTO {

    List<LessonDTO> lessonStudentTakes=new ArrayList<>();


    public StudentDTO(Integer userID, String firstName, String email, String username, String lastName, Set<Role> Role, List<LessonDTO> lessonStudentTakes) {
        super(userID, firstName, email, username, lastName, Role);
        this.lessonStudentTakes = lessonStudentTakes;
    }

    public StudentDTO(List<LessonDTO> lessonStudentTakes) {
        this.lessonStudentTakes = lessonStudentTakes;
    }
}
