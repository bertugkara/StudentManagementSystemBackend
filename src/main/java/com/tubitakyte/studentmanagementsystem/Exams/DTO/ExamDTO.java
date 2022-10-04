package com.tubitakyte.studentmanagementsystem.Exams.DTO;

import com.tubitakyte.studentmanagementsystem.Exams.entity.ExamSubmissions;
import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.SimpleLessonDTO;
import com.tubitakyte.studentmanagementsystem.Rooms.entity.Room;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ExamDTO {

    private Integer id;
    private String name;
    private LocalDateTime examDateTime;
    private String examDetailsAndExamTips;
    private Room room;
    private UserDTO creator;
    private SimpleLessonDTO lesson;

    private List<ExamSubmissions> examSubmissions=new ArrayList<>();

    public ExamDTO(Integer id,String name, LocalDateTime examDateTime, String examDetailsAndExamTips, Room room, UserDTO creator, SimpleLessonDTO lesson) {
        this.id=id;
        this.name = name;
        this.examDateTime = examDateTime;
        this.examDetailsAndExamTips = examDetailsAndExamTips;
        this.room = room;
        this.creator = creator;
        this.lesson = lesson;
    }
}
