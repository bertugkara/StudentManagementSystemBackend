package com.tubitakyte.studentmanagementsystem.User.controllers;

import com.tubitakyte.studentmanagementsystem.RegisterAndAuthControl.Services.RegistrationService;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.ProfessorDao;
import com.tubitakyte.studentmanagementsystem.User.Services.ProfessorService;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe.AssistantDescribeMe;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe.ProfessorDescribeMe;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.ProfessorDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Professor;
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
@RequestMapping(path = "api/professor")
public class ProfessorController {

    private final RegistrationService<Professor> professorRegistrationService;
    private final ProfessorDao professorDao;

    private final ProfessorService professorService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESSOR')")
    @PostMapping(path = "/registerProfessor")
    public DataResult<Professor> registerProfessor(@RequestBody @Valid RegistrationRequest request) {

        if(request.getRole().toString().equals("[ROLE_PROFESSOR]")) {
            return new SuccessDataResult<>(professorRegistrationService.register(request),"Successfully created Professor");
        }
        else{
            return new ErrorDataResult<>("Error occured while creating Professor");
        }

    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESSOR')")
    @GetMapping(path = "/getAllProfessors")
    public DataResult<List<ProfessorDTO>> getAllProfs() {
        return (this.professorService.getAllActiveUsers());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESSOR')")
    @GetMapping(path = "/getByUsernameProfessor")
    public User getByUsername(@RequestParam String username) {
        return (this.professorDao.findByUsernameQuery(username));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESSOR')")
    @GetMapping(path = "/getByProfessorNotAttendedToGivenLecture")
    public DataResult<List<ProfessorDTO>> getByProfessorNotAttendedToGivenLecture(@RequestParam Integer id) {

        return new SuccessDataResult<>(professorService.getProfessorsNotLessonsAttendedToGivenLessonCode(id),"Successfully listed");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESSOR')")
    @GetMapping(path = "/getByID")
    public DataResult<ProfessorDTO> getByID(@RequestParam int id) {
        return (this.professorService.getByUserID(id));
    }


    @GetMapping(path = "/describeMeProfessor")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESSOR')")
    public DataResult<ProfessorDescribeMe> describeMeProfessor(@RequestParam Integer userID) {
        return (professorService.describeMe(userID));
    }
}
