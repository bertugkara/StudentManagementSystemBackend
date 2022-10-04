package com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs;

import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.DTO.FileDTO;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.SimpleStudentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HomeworkSubmissionDTO {

    Integer submission_id;
    Instant submission_creation_Date;
    SimpleStudentDTO studentDTO;
    HomeworkDTO homeworkDTO;
    FileDTO fileDTO;

}
