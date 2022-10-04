package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.ExamConversion;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamDTO;
import com.tubitakyte.studentmanagementsystem.Exams.entity.Exams;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.LessonConversions.ConvertLessonToSimpleLessonDTO;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions.ConvertUserToUserDTO;

import java.util.ArrayList;
import java.util.List;

public record ExamListToExamDTOList(
        List<ExamDTO> examDTOList
) {

    public static ExamListToExamDTOList convert(List<Exams> exams) {
        List<ExamDTO> tempList = new ArrayList<>();

        exams.forEach((tempExam) -> {
            ExamDTO examDTO = new ExamDTO(
                    tempExam.getId(),
                    tempExam.getName(),
                    tempExam.getExamDateTime(),
                    tempExam.getExamDetailsAndTips(),
                    tempExam.getRoom(),
                    ConvertUserToUserDTO.convert(tempExam.getCreator()).userDTO(),
                    ConvertLessonToSimpleLessonDTO.convert(tempExam.getLesson()).simpleLessonDTO()
            );

            tempList.add(examDTO);
        });
        return new ExamListToExamDTOList(tempList);
    }
}
