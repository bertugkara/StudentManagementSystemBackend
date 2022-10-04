package com.tubitakyte.studentmanagementsystem.Lesson.entity;

import com.tubitakyte.studentmanagementsystem.Homework.Entity.Homework;
import com.tubitakyte.studentmanagementsystem.Lesson.enums.LessonType;
import com.tubitakyte.studentmanagementsystem.Rooms.entity.Room;
import com.tubitakyte.studentmanagementsystem.TimeTable.Entity.TimeTable;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Assistant;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Professor;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;
import com.tubitakyte.studentmanagementsystem.common.BaseEntity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "lessons")
@NoArgsConstructor
public class Lessons extends BaseEntity {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @Enumerated(EnumType.STRING)
    private LessonType lessonType;

    @NotBlank
    private String lessonCode;

    @NotBlank
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @OneToMany
    @JoinTable(name = "lesson_and_table",
            joinColumns = @JoinColumn(name ="lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "time_table_id")
    )
    private List<TimeTable> timeTable;

    @OneToMany
    @JoinTable(name = "lesson_and_homeworks",
            joinColumns = @JoinColumn(name ="lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "homework_id")
    )
    private List<Homework> homeworkList=new ArrayList<>();

    //CASCADE TYPES HANGİSİNİ SEÇMELİ
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Professor instructor;

    @ManyToMany
    @JoinTable(name = "lesson_and_assistant",
            joinColumns = @JoinColumn(name = "lesson_code"),
            inverseJoinColumns = @JoinColumn(name = "assistant_id"))
    private List<Assistant> assistants = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "lesson_and_student",
            joinColumns = @JoinColumn(name = "lesson_code"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students = new ArrayList<>();

    public Lessons(String name, String description, LessonType lessonType,String lessonCode,
                   Room room, List<TimeTable> timeTable, Professor instructor, List<Assistant> assistants, List<Student> students) {
        this.name = name;
        this.description = description;
        this.lessonType = lessonType;
        this.lessonCode=lessonCode;
        this.room = room;
        this.timeTable = timeTable;
        this.instructor = instructor;
        this.assistants = assistants;
        this.students = students;
    }

    public void updateAssistant(List<Assistant> updatedAssistants) {
        this.assistants=updatedAssistants;
    }

    public void updateLesson(Lessons lessonUpdateRequest) {
        this.name=lessonUpdateRequest.getName();
        this.description=lessonUpdateRequest.getDescription();
        this.lessonType=lessonUpdateRequest.getLessonType();
        this.lessonCode=lessonUpdateRequest.getLessonCode();
        this.room=lessonUpdateRequest.getRoom();
        this.instructor=lessonUpdateRequest.getInstructor();
        this.assistants=lessonUpdateRequest.getAssistants();
        this.students=lessonUpdateRequest.getStudents();
        this.timeTable=lessonUpdateRequest.getTimeTable();
    }

    public void updateLessonForProfessor(Lessons lessonUpdateRequest) {
        this.name=lessonUpdateRequest.getName();
        this.description=lessonUpdateRequest.getDescription();
        this.room=lessonUpdateRequest.getRoom();
        this.assistants=lessonUpdateRequest.getAssistants();
        this.timeTable=lessonUpdateRequest.getTimeTable();
    }

    public void updateLessonForAssistant(Lessons lessonUpdateRequest) {
        this.name=lessonUpdateRequest.getName();
        this.description=lessonUpdateRequest.getDescription();
        this.room=lessonUpdateRequest.getRoom();
    }

    public void attachHomeworkList(List<Homework> homeworkList){
        this.homeworkList=homeworkList;
    }

    public void deleteTimeTable(){
        List<TimeTable> timeTableList=new ArrayList<>();
        this.timeTable=timeTableList;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }


}
