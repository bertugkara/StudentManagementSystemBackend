package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.LessonConversions;

import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.LessonDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;

import java.util.ArrayList;
import java.util.List;

public record ConvertLessonsListToLessonDTOList(
        List<LessonDTO> lessonsList
) {
    public static ConvertLessonsListToLessonDTOList convertLessonToDTO(List<Lessons> lessons){
        List<LessonDTO> lessonDTOList = new ArrayList<>();
        lessons.forEach((tempLesson) -> {
            LessonDTO lessonDTO = new LessonDTO(
                    tempLesson.getId(),
                    tempLesson.getName(),
                    tempLesson.getDescription(),
                    tempLesson.getLessonType().toString(),
                    tempLesson.getLessonCode(),
                    tempLesson.getRoom(),
                    tempLesson.getTimeTable());
            lessonDTOList.add(lessonDTO);
        });
        return new ConvertLessonsListToLessonDTOList(lessonDTOList);
    }
}
