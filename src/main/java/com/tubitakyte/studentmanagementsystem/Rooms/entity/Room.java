package com.tubitakyte.studentmanagementsystem.Rooms.entity;

import com.tubitakyte.studentmanagementsystem.common.BaseEntity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@Table(name = "rooms")
@NoArgsConstructor
public class Room extends BaseEntity {

    @NotBlank
    private Boolean isProjectionMachineExist;

    @NotBlank
    private Boolean isComputerExists;

    @NotBlank
    private Boolean isAC_Exists;

    @NotBlank
    private Boolean isWindowExist;

    @NotBlank
    private Integer humanCapacity;

    public Room(boolean isProjectionMachineExist, boolean isComputerExists, boolean isAC_Exists, boolean isWindowExist, Integer humanCapacity) {
        this.isProjectionMachineExist = isProjectionMachineExist;
        this.isComputerExists = isComputerExists;
        this.isAC_Exists = isAC_Exists;
        this.isWindowExist = isWindowExist;
        this.humanCapacity = humanCapacity;
    }


}
