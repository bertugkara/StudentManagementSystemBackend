package com.tubitakyte.studentmanagementsystem.TimeTable.Controller;


import com.tubitakyte.studentmanagementsystem.TimeTable.Service.TimeTableService;
import com.tubitakyte.studentmanagementsystem.TimeTable.TimeDTO.TimeDTO;
import com.tubitakyte.studentmanagementsystem.common.requests.CreateTimeRequest;
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
@RequestMapping(path = "api/timetable")
public class TimeTableController {

    private final TimeTableService timeTableService;

    @PostMapping("/createTime")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public DataResult<TimeDTO> createTime(@RequestBody @Valid CreateTimeRequest request){
        return timeTableService.createTime(request);
    }

    @GetMapping("/getOneTime")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<TimeDTO> getOneTime(@RequestParam @NotBlank Integer id){
        return timeTableService.getOneTime(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    @GetMapping("/getAllTimes")
    public DataResult<List<TimeDTO>> getAllTimes(){
        return timeTableService.getAllTimes();
    }

    @GetMapping("/getAllNonUsedTimesOrLessonId")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<List<TimeDTO>> getAllNonUsedTimesOrLessonId(@RequestParam @NotBlank int id){
        return timeTableService.getAllNonUsedTimesOrLessonId(id);
    }

    @GetMapping("/getAllNonUsedTimes")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<List<TimeDTO>> getAllTimesNonUsed(){
        return timeTableService.getAllNonUsedTimes();
    }


}
