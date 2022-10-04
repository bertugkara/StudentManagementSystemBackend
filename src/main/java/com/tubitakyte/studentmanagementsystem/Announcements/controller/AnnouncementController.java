package com.tubitakyte.studentmanagementsystem.Announcements.controller;


import com.tubitakyte.studentmanagementsystem.Announcements.DTO.AnnouncementDTO;
import com.tubitakyte.studentmanagementsystem.Announcements.DataAccess.AnnouncementDao;
import com.tubitakyte.studentmanagementsystem.Announcements.Services.AnnouncementService;
import com.tubitakyte.studentmanagementsystem.common.requests.CreateAnnouncementRequest;
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
@RequestMapping(path = "api/announcement")
public class AnnouncementController {

    private final AnnouncementService announcementService;


    @PostMapping("/createAnnouncement")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public DataResult<AnnouncementDTO> createAnnouncement(@RequestBody @Valid CreateAnnouncementRequest request){
        return announcementService.addAnnouncement(request);
    }

    @GetMapping("/getOneAnnouncement")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<AnnouncementDTO> getOneAnnouncement(@RequestParam @NotBlank Integer id){
        return announcementService.getOneAnnouncement(id);
    }

    @GetMapping("/getAllAnnouncements")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<List<AnnouncementDTO>> getAllAnnouncements(){
        return announcementService.getAllAnnouncements();
    }

    @PostMapping("/deleteAnnouncement")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Result deleteAnnouncement(@RequestParam Integer id){
        return announcementService.deleteById(id);
    }

}
