package com.tubitakyte.studentmanagementsystem.User.controllers;

import com.tubitakyte.studentmanagementsystem.RegisterAndAuthControl.Services.RegistrationService;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.AdminDao;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.UserDao;
import com.tubitakyte.studentmanagementsystem.User.ServiceImpl.UserServiceImpl;
import com.tubitakyte.studentmanagementsystem.User.Services.UserDTOservice;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.UserDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Admin;
import com.tubitakyte.studentmanagementsystem.common.requests.AuthRequests.RegistrationRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.ErrorDataResult;
import com.tubitakyte.studentmanagementsystem.utilities.Result;
import com.tubitakyte.studentmanagementsystem.utilities.SuccessDataResult;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(path = "api/admin")
public class AdminController {

    //AUTHORIZATION IN THE WEBSECURITYCONFIG
    private final RegistrationService<Admin> adminRegistrationService ;
    private final AdminDao adminDao;

    private final UserDao<User> userDao;

    private final UserServiceImpl userServiceImpl;

    private final UserDTOservice<UserDTO> userDTOService;


    @PostMapping(path = "/registerAdmin")
    public DataResult<Admin> registerAdmin(@RequestBody @Valid RegistrationRequest request) {
        if(request.getRole().toString().equals("[ROLE_ADMIN]")) {
            return new SuccessDataResult<>(adminRegistrationService.register(request));
        }
        return new ErrorDataResult<>("You only can Register ADMIN via this link");

    }

    @GetMapping(path = "/getAllAdmin")
    public List<Admin> getAllAdmin() {
        return (this.adminDao.findAll());
    }

    @GetMapping(path = "/getAllActiveUsers")
    public DataResult<List<UserDTO>> getAllActiveUsers() {
        return (this.userDTOService.getAllActiveUsers());
    }

    @GetMapping(path = "/getAllDeactiveUsers")
    public DataResult<List<UserDTO>> getAllDeactiveUsers() {
        return (this.userDTOService.getAllDeactiveUsers());
    }

    @PostMapping(  "/makeStudentActive")
    public Result makeStudentActive(@RequestParam int id){
        return userServiceImpl.makeUserActive(id);
    }
    @PostMapping(  "/makeStudentDeactive")
    public Result makeStudentPassive(@RequestParam int id){
        return userServiceImpl.makeUserPassive(id);
    }

    @GetMapping(path = "/getOneUserWithID")
    public DataResult<UserDTO> getOneUser(@RequestParam int userID) {
        return (this.userDTOService.getByUserID(userID));
    }

    @GetMapping(path = "/getByUsernameOfAdmin")
    public User getByUsername(@RequestParam String username) {
        return (this.adminDao.findByUsernameQuery(username));
    }

}
