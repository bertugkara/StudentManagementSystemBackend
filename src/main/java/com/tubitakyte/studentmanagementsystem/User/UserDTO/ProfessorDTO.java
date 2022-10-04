package com.tubitakyte.studentmanagementsystem.User.UserDTO;

import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.LessonDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ProfessorDTO extends UserDTO {

    List<LessonDTO> lessonProfessorTeaches=new ArrayList<>();

    public ProfessorDTO(Integer userID, String firstName, String email, String username, String lastName, Set<com.tubitakyte.studentmanagementsystem.Role.entity.Role> Role, List<LessonDTO> lessonProfessorTeaches) {
        super(userID, firstName, email, username, lastName, Role);
        this.lessonProfessorTeaches = lessonProfessorTeaches;
    }

    public ProfessorDTO(List<LessonDTO> lessonProfessorTeaches) {
        this.lessonProfessorTeaches = lessonProfessorTeaches;
    }
}
