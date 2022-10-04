package com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamDTO;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.LessonDTO;
import com.tubitakyte.studentmanagementsystem.Role.entity.Role;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class AssistantDescribeMe extends UserDTO {

    List<ExamDTO> examsCreated=new ArrayList<>();

    List<HomeworkDTO> homeworksCreatedOrResponsible=new ArrayList<>();

    List<LessonDTO> lessonsAttended=new ArrayList<>();

    public AssistantDescribeMe(Integer id, String firstName, String email, String username, String lastName, Set<com.tubitakyte.studentmanagementsystem.Role.entity.Role> Role, List<ExamDTO> examsCreated, List<HomeworkDTO> homeworksCreatedOrResponsible, List<LessonDTO> lessonsAttended) {
        super(id, firstName, email, username, lastName, Role);
        this.examsCreated = examsCreated;
        this.homeworksCreatedOrResponsible = homeworksCreatedOrResponsible;
        this.lessonsAttended = lessonsAttended;
    }
}
