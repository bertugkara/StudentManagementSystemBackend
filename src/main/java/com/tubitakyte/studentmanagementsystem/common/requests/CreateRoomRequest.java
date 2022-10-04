package com.tubitakyte.studentmanagementsystem.common.requests;

import com.tubitakyte.studentmanagementsystem.Rooms.entity.Room;

import javax.validation.constraints.NotBlank;

public record CreateRoomRequest(
     @NotBlank
     boolean isProjectionMachineExist,

     @NotBlank
     boolean isComputerExists,

     @NotBlank
     boolean isAC_Exists,

     @NotBlank
     boolean isWindowExist,

     @NotBlank
     Integer HumanCapacity
)
{
    public Room toEntity(){
        return new Room(isProjectionMachineExist,
                isComputerExists,
                isAC_Exists,
                isWindowExist,
                HumanCapacity );

    }
}
