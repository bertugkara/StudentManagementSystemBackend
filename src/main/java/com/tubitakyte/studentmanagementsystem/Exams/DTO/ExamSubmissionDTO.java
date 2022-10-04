package com.tubitakyte.studentmanagementsystem.Exams.DTO;


import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.DTO.FileDTO;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamSubmissionDTO {

    private Integer id;

    private String submission_date;

    private Integer grade;

    private String description;

    private ExamDTO examDTO;

    private StudentDTO studentDTO;

    private FileDTO fileDTO;
}
