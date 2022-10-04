package com.tubitakyte.studentmanagementsystem.TimeTable.TimeDTO;

import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.SimpleLessonDTO;
import com.tubitakyte.studentmanagementsystem.TimeTable.enums.Days;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TimeDTO {

    private Integer id;
    private LocalTime startDate;

    private LocalTime endDate;

    private Days days;
    private Boolean isInUse;

    private SimpleLessonDTO ownerLesson;

    public TimeDTO(Integer id,LocalTime startDate, LocalTime endDate, Days days, Boolean isInUse) {
        this.id=id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.isInUse = isInUse;
    }
}
