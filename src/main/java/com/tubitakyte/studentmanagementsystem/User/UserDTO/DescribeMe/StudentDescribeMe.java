package com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamDTO;
import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkDTO;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.LessonDTO;
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
public class StudentDescribeMe extends UserDTO {

    List<ExamDTO> examsThatStudentIsResponsible=new ArrayList<>();

    List<ExamSubmissionDTO> listOfExamSubmissionsThatStudentPerformed=new ArrayList<>();

    List<HomeworkDTO> listOfHomeworksThatStudentIsResponsible=new ArrayList<>();

    List<HomeworkSubmissionDTO> listOfHomeworkSubmissionsThatStudentPerformed=new ArrayList<>();

    List<LessonDTO> lessonsEnrolled=new ArrayList<>();

    public StudentDescribeMe(Integer id, String firstName, String email, String username, String lastName, Set<com.tubitakyte.studentmanagementsystem.Role.entity.Role> Role, List<ExamDTO> examsThatStudentResponsible, List<ExamSubmissionDTO> submissionsStudentPerformed, List<HomeworkDTO> listOfHomeworksThatStudentResponsible, List<HomeworkSubmissionDTO> listOfHomeworksThatStudentPerformed, List<LessonDTO> lessonsEnrolled) {
        super(id, firstName, email, username, lastName, Role);
        this.examsThatStudentIsResponsible = examsThatStudentResponsible;
        this.listOfExamSubmissionsThatStudentPerformed = submissionsStudentPerformed;
        this.listOfHomeworksThatStudentIsResponsible = listOfHomeworksThatStudentResponsible;
        this.listOfHomeworkSubmissionsThatStudentPerformed = listOfHomeworksThatStudentPerformed;
        this.lessonsEnrolled = lessonsEnrolled;
    }

}
