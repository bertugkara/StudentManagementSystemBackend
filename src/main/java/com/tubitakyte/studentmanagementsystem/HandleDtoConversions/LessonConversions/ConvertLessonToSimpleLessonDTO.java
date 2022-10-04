package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.LessonConversions;

import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.SimpleLessonDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;

public record ConvertLessonToSimpleLessonDTO(
        SimpleLessonDTO simpleLessonDTO
) {
    public static ConvertLessonToSimpleLessonDTO convert(Lessons lesson){
        return new ConvertLessonToSimpleLessonDTO(
                new SimpleLessonDTO(
                        lesson.getId(),
                        lesson.getName(),
                        lesson.getDescription(),
                        lesson.getLessonType().toString(),
                        lesson.getLessonCode()
                )
        );
    }
}
