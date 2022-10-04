package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.HomeworkConversions;

import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.LessonConversions.ConvertLessonToSimpleLessonDTO;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions.ConvertUserToUserDTO;
import com.tubitakyte.studentmanagementsystem.Homework.Entity.Homework;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkDTO;

import java.util.ArrayList;
import java.util.List;

public record HomeworkListToHomeworkDtoList(
        List<HomeworkDTO> homeworkDTOList
) {
    public static HomeworkListToHomeworkDtoList convertToDTO(List<Homework> homework){

       List<HomeworkDTO> tempList=new ArrayList<>();

       homework.forEach((tempHomework) ->{
           HomeworkDTO homeworkDTO= new HomeworkDTO(
                   tempHomework.getId(),
                   tempHomework.getStartDate(),
                   tempHomework.getEndDate(),
                   tempHomework.getStartTime(),
                   tempHomework.getEndTime(),
                   tempHomework.getDescription(),
                   ConvertLessonToSimpleLessonDTO.convert(tempHomework.getLesson()).simpleLessonDTO(),
                   ConvertUserToUserDTO.convert(tempHomework.getCreator()).userDTO()
           );
           tempList.add(homeworkDTO);
       });
       return new HomeworkListToHomeworkDtoList(tempList);
    }

}
