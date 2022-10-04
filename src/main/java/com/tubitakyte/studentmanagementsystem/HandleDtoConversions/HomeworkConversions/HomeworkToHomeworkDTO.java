package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.HomeworkConversions;

import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.LessonConversions.ConvertLessonToSimpleLessonDTO;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions.ConvertUserToUserDTO;
import com.tubitakyte.studentmanagementsystem.Homework.Entity.Homework;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkDTO;

public record HomeworkToHomeworkDTO(

        HomeworkDTO homeworkDTO

) {
    public static HomeworkToHomeworkDTO convertToDTO(Homework homework){
        return new HomeworkToHomeworkDTO(
                new HomeworkDTO(
                        homework.getId(),
                        homework.getStartDate(),
                        homework.getEndDate(),
                        homework.getStartTime(),
                        homework.getEndTime(),
                        homework.getDescription(),
                        ConvertLessonToSimpleLessonDTO.convert(homework.getLesson()).simpleLessonDTO(),
                        ConvertUserToUserDTO.convert(homework.getCreator()).userDTO()
                )
        );
    }
}
