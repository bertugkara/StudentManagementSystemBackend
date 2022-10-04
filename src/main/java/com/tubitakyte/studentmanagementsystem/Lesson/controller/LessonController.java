package com.tubitakyte.studentmanagementsystem.Lesson.controller;

import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.LessonDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.Services.LessonService;
import com.tubitakyte.studentmanagementsystem.common.requests.LessonRelatedRequest.*;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(path = "api/lesson")
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/createLesson")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public DataResult<LessonDTO> createLesson(@RequestBody @Valid LessonCreateRequest request){
        return lessonService.addLesson(request);
    }

    @PostMapping("/updateLesson")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public DataResult<LessonDTO> updateLesson( @RequestBody @Valid LessonUpdateRequest request){
        return lessonService.lessonUpdate(request);
    }

    @PostMapping("/updateLessonForProfessor")
    @PreAuthorize("hasAnyAuthority('ROLE_PROFESSOR')")
    public DataResult<LessonDTO> updateLessonForProfessor( @RequestBody @Valid LessonUpdateRequestFromProfessor request){
        return lessonService.updateLessonForProfessor(request);
    }

    @PostMapping("/updateLessonForAssistant")
    @PreAuthorize("hasAnyAuthority('ROLE_ASSISTANT')")
    public DataResult<LessonDTO> updateLessonForAssistant( @RequestBody @Valid LessonUpdateRequestFromAssistant request){
        return lessonService.updateLessonForAssistant(request);
    }
    @GetMapping("/getByID")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<LessonDTO> getById( @RequestParam @NotBlank int id){
        return lessonService.getOneLesson(id);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<List<LessonDTO>> getById(){
        return (lessonService.getAll());
    }

    @PostMapping("/addAssistant")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESSOR')")
    public DataResult<LessonDTO> addAssistant(@RequestBody @Valid AddAssistantToLessonRequest addAssistantToLessonRequest){
        return lessonService.addAnAssistantToLesson(addAssistantToLessonRequest);
    }

    @PostMapping("/addStudent")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESSOR','ROLE_STUDENT','ROLE_ASSISTANT')")
    public Result addStudent(@RequestParam Integer lessonID, @RequestParam Integer studentID){
        return lessonService.addStudentToTheLesson(lessonID, studentID);
    }

}
