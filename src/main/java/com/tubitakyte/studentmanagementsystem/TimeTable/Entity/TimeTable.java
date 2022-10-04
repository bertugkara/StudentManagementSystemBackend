package com.tubitakyte.studentmanagementsystem.TimeTable.Entity;


import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import com.tubitakyte.studentmanagementsystem.TimeTable.enums.Days;
import com.tubitakyte.studentmanagementsystem.common.BaseEntity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

@Getter
@Entity
@Table(name = "timeTableForLessons")
@NoArgsConstructor
public class TimeTable extends BaseEntity {
    @NotBlank
    private LocalTime startDate;

    @NotBlank
    private LocalTime endDate;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Days days;

    private Boolean isInUse=false;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lessons ownerLesson;

    public TimeTable(LocalTime startDate, LocalTime endDate, Days days) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
    }

    public void updateIsInUse(boolean expression){
        this.isInUse=expression;
    }

    public void assignOwner(Lessons lesson){
        this.ownerLesson=lesson;
    }

    public void setTimeTableFree(){
        this.isInUse=false;
    }
}
