package com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleLessonDTO {
    private Integer id;

    private String name;

    private String description;

    private String lessonType;

    private String lessonCode;
}
