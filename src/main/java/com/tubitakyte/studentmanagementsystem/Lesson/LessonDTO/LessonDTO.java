package com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO;

import com.tubitakyte.studentmanagementsystem.Rooms.entity.Room;
import com.tubitakyte.studentmanagementsystem.TimeTable.Entity.TimeTable;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.AssistantDTO;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.ProfessorDTO;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {

    private Integer id;

    private String name;

    private String description;

    private String lessonType;

    private String lessonCode;

    private Room room;

    private List<TimeTable> timeTable;

    private List<AssistantDTO> assistantDTOList;

    private List<StudentDTO> studentDTOList;

    private ProfessorDTO professorDTO;

    public LessonDTO(Integer id, String name, String description, String lessonType,String lessonCode, Room room,List<TimeTable> timeTable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lessonType = lessonType;
        this.lessonCode=lessonCode;
        this.room = room;
        this.timeTable=timeTable;
    }
}
