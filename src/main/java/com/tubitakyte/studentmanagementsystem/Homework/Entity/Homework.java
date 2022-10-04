package com.tubitakyte.studentmanagementsystem.Homework.Entity;

import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Assistant;
import com.tubitakyte.studentmanagementsystem.common.BaseEntity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Homework extends BaseEntity {

    @FutureOrPresent
    @NotBlank
    private LocalDate startDate;

    @Future
    @NotBlank
    private LocalDate endDate;

    @NotBlank
    private LocalTime startTime;
    @NotBlank
    private LocalTime endTime;
    @NotBlank
    @Size(max = 1000)
    private String description;

    @OneToMany
    @JoinTable(name = "homework_and_submissions",
            joinColumns = @JoinColumn(name ="homework_id"),
            inverseJoinColumns = @JoinColumn(name = "submission_id")
    )
    private List<HomeworkSubmissions> submission;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lessons lesson;

    @ManyToOne
    @JoinColumn(name = "responsible_assistant")
    private Assistant assistant;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    public Homework(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, String description, Lessons lesson, Assistant assistant, User creator) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.lesson = lesson;
        this.assistant = assistant;
        this.creator = creator;
    }

    public void addSubmission(List<HomeworkSubmissions> submissionsList)
    {
        this.submission=submissionsList;
    }
}
