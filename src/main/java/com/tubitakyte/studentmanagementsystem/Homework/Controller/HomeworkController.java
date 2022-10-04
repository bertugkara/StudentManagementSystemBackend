package com.tubitakyte.studentmanagementsystem.Homework.Controller;

import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkDTO;
import com.tubitakyte.studentmanagementsystem.Homework.Service.HomeworkService;
import com.tubitakyte.studentmanagementsystem.common.requests.HomeworkRequests.HomeworkCreateRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(path = "api/homework")
public class HomeworkController {
    private final HomeworkService homeworkService;

    @PostMapping("/createHomework")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<HomeworkDTO> createHomework(@RequestBody @Valid HomeworkCreateRequest request){
        return (homeworkService.createHomework(request));
    }

    @GetMapping("/getHomework")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<HomeworkDTO> getHomework(@NotBlank Integer id){
        return (homeworkService.getOneHomework(id));
    }

    @GetMapping("/getAllHomeworks")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<List<HomeworkDTO>> getAllHomeworks(){
        return (homeworkService.getAllHomeworks());
    }

    @GetMapping("/getAllHomeworksBelongsToId")
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<List<HomeworkDTO>> getAllHomeworksBelongsToId(@RequestParam Integer id){
        return (homeworkService.getHomeworksBelongsToLessonId(id));
    }



}
