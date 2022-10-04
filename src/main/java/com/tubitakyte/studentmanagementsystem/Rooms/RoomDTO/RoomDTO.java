package com.tubitakyte.studentmanagementsystem.Rooms.RoomDTO;

import com.tubitakyte.studentmanagementsystem.Rooms.entity.Room;

import javax.validation.constraints.NotBlank;

public record RoomDTO(
        @NotBlank
        Integer Id,

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

) {
    public static RoomDTO fromEntity(Room room) {

        return new RoomDTO(
                room.getId(),
                room.getIsProjectionMachineExist(),
                room.getIsComputerExists(),
                room.getIsAC_Exists(),
                room.getIsWindowExist(),
                room.getHumanCapacity()
        );
    }
}
