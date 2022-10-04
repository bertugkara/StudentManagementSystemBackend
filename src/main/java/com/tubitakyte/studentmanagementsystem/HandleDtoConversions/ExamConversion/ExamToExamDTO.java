package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.ExamConversion;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamDTO;
import com.tubitakyte.studentmanagementsystem.Exams.entity.Exams;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.LessonConversions.ConvertLessonToSimpleLessonDTO;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions.ConvertUserToUserDTO;

public record ExamToExamDTO(
        ExamDTO examDTO
) {
    public static ExamToExamDTO convert(Exams exams){
        return new ExamToExamDTO(
                new ExamDTO(
                        exams.getId(),
                        exams.getName(),
                        exams.getExamDateTime(),
                        exams.getExamDetailsAndTips(),
                        exams.getRoom(),
                        ConvertUserToUserDTO.convert(exams.getCreator()).userDTO(),
                        ConvertLessonToSimpleLessonDTO.convert(exams.getLesson()).simpleLessonDTO(),
                        exams.getSubmissions()
                )
        );
    }
}
