package com.tubitakyte.studentmanagementsystem.User.controllers;


import com.tubitakyte.studentmanagementsystem.RegisterAndAuthControl.Services.RegistrationService;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.AssistantDao;
import com.tubitakyte.studentmanagementsystem.User.Services.AssistantService;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.AssistantDTO;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe.AssistantDescribeMe;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Assistant;
import com.tubitakyte.studentmanagementsystem.common.requests.AuthRequests.RegistrationRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.ErrorDataResult;
import com.tubitakyte.studentmanagementsystem.utilities.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(path = "api/assistant")
public class AssistantController {

    private final RegistrationService<Assistant> assistantRegistrationService;

    private final AssistantService assistantService;

    private final AssistantDao assistantDao;


    @PostMapping(path = "/registerAssistant")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public DataResult<Assistant> registerAssistant(@RequestBody @Valid RegistrationRequest request) {
        if (request.getRole().toString().equals("[ROLE_ASSISTANT]")) {
            return new SuccessDataResult<>(assistantRegistrationService.register(request));
        }
        return new ErrorDataResult("You only can Register ASSISTANT via this link");

    }

    @GetMapping(path = "/getAllAssistants")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESSOR')")
    public DataResult<List<AssistantDTO>> getAllAssistants() {
        return (this.assistantService.getAllActiveUsers());
    }


    @GetMapping(path = "/getByAssistantID")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESSOR')")
    public DataResult<AssistantDTO> getByUsername(@RequestParam int id) {
        return (assistantService.getByUserID(id));
    }

    @GetMapping(path = "/getByAssistantNotAttendedToGivenLecture")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESSOR')")
    public List<AssistantDTO> getByUsername(@RequestParam Integer lessonCode) {
        return (assistantService.getAssistantsNotLessonsAttendedToGivenLessonCode(lessonCode));
    }

    @GetMapping(path = "/describeMeAssistant")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public DataResult<AssistantDescribeMe> describeMeAssistant(@RequestParam Integer userID) {
        return (assistantService.describeMe(userID));
    }



}

