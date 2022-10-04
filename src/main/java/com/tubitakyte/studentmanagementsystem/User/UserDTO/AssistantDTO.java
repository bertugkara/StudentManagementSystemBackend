package com.tubitakyte.studentmanagementsystem.User.UserDTO;

import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.LessonDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
public class AssistantDTO extends UserDTO {

    List<LessonDTO> lessonAssistantTeaches =new ArrayList<>();

    public AssistantDTO(Integer userID, String firstName, String email, String username, String lastName, Set<com.tubitakyte.studentmanagementsystem.Role.entity.Role> Role, List<LessonDTO> lessonAssistantTeaches) {
        super(userID, firstName, email, username, lastName, Role);
        this.lessonAssistantTeaches = lessonAssistantTeaches;
    }

    public AssistantDTO(List<LessonDTO> lessonAssistantTeaches) {
        this.lessonAssistantTeaches = lessonAssistantTeaches;
    }
}
