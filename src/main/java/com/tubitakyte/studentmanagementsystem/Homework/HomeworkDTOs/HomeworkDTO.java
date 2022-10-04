package com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs;

import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.SimpleLessonDTO;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkDTO {

    Integer id;
    LocalDate startDate;
    LocalDate endDate;
    LocalTime startTime;
    LocalTime endTime;
    String description;
    SimpleLessonDTO lesson;
    UserDTO creator;

}
