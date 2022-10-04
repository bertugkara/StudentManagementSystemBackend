package com.tubitakyte.studentmanagementsystem.User.controllers;

import com.tubitakyte.studentmanagementsystem.RegisterAndAuthControl.Services.RegistrationService;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.StudentDao;
import com.tubitakyte.studentmanagementsystem.User.Services.StudentService;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe.StudentDescribeMe;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.StudentDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;
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
@RequestMapping(path = "api/student")
public class StudentController {

    private final RegistrationService<Student> studentRegistrationService;
    private final StudentDao studentDao;

    private final StudentService studentService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping(path = "/registerStudent")
    public DataResult<Student> registerStudent(@RequestBody @Valid RegistrationRequest request) {

        if (request.getRole().toString().equals("[ROLE_STUDENT]")) {
            return new SuccessDataResult<>(studentRegistrationService.register(request), "Successfully Created Student");
        } else {
            return new ErrorDataResult<>("You only can Register STUDENT via this link");
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    @GetMapping(path = "/getAllStudents")
    public DataResult<List<StudentDTO>> getAllStudent() {
        return (this.studentService.getAllActiveUsers());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(path = "/getByUsernameStudent")
    public User getByUsername(@RequestParam String username) {
        return (this.studentDao.findByUsernameQuery(username));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    @GetMapping(path = "/getByStudentId")
    public DataResult<StudentDTO> getByStudentId(@RequestParam int id) {
        return (this.studentService.getByUserID(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    @GetMapping(path = "/getByEmail")
    public DataResult<StudentDTO> getByStudentId(@RequestParam String email) {
        return (this.studentService.getByEmail(email));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    @GetMapping(path = "/getByStudentsNotAttendedToGivenLecture")
    public List<StudentDTO> getByStudentsNotAttendedToGivenLecture(@RequestParam Integer lessoncode) {

        return (studentService.getStudentsNotLessonsAttendedToGivenLessonCode(lessoncode));
    }

    @GetMapping(path = "/describeMeStudent")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT')")
    public DataResult<StudentDescribeMe> describeMeStudent(@RequestParam Integer userID) {
        return (studentService.describeMe(userID));
    }
}
