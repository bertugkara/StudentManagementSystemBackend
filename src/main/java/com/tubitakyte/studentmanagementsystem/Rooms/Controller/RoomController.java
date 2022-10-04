package com.tubitakyte.studentmanagementsystem.Rooms.Controller;


import com.tubitakyte.studentmanagementsystem.Rooms.DataAccess.RoomDao;
import com.tubitakyte.studentmanagementsystem.Rooms.RoomDTO.RoomDTO;
import com.tubitakyte.studentmanagementsystem.Rooms.Service.RoomService;
import com.tubitakyte.studentmanagementsystem.common.requests.CreateRoomRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.Result;
import com.tubitakyte.studentmanagementsystem.utilities.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(path = "api/room")
public class RoomController {
    private final RoomService roomService;
    private final RoomDao roomDao;

    @GetMapping("/getOneRoom")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<RoomDTO> getOneRoom(@RequestParam @NotBlank int id){
        return (roomService.getOneRoom(id));
    }

    @GetMapping("/getAllRooms")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<List<RoomDTO>> getAllRooms(){
        return (roomService.getAllRooms());
    }

    @PostMapping("/createRoom")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public DataResult<RoomDTO> createRoom(@RequestBody @Valid CreateRoomRequest createRoomRequest){
        return (roomService.createRoom(createRoomRequest));
    }

    @DeleteMapping("/deleteRoom")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Result createRoom(@RequestParam @NotBlank int id){
        roomDao.deleteById(id);
        return new SuccessResult("Delete Success!");
    }


}
