package com.tubitakyte.studentmanagementsystem.Exams.entity;

import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import com.tubitakyte.studentmanagementsystem.Rooms.entity.Room;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.common.BaseEntity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Exams extends BaseEntity {

    @NotBlank
    private String name;

    @NotBlank
    @FutureOrPresent
    private LocalDateTime examDateTime;

    @NotBlank
    @Size(max = 1000)
    private String examDetailsAndTips;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @NotBlank
    private User creator;

    @ManyToOne
    @NotBlank
    @JoinColumn(name = "lesson_id") //REFERENCE COLUMN
    private Lessons lesson;

    @OneToMany
    @JoinTable(name = "exams_and_examSubmissions",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_submission_ID")
    )
    private List<ExamSubmissions> submissions;


    public Exams(String name, LocalDateTime examDateTime, String examDetailsAndTips, Room room, User creator, Lessons lesson) {
        this.name = name;
        this.examDateTime = examDateTime;
        this.examDetailsAndTips = examDetailsAndTips;
        this.room = room;
        this.creator = creator;
        this.lesson = lesson;
    }
}
