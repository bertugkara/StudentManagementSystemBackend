package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.TimeConversions;

import com.tubitakyte.studentmanagementsystem.TimeTable.Entity.TimeTable;
import com.tubitakyte.studentmanagementsystem.TimeTable.TimeDTO.TimeDTO;

public record ConvertTimeToTimeDTO(
        TimeDTO timeDTO
) {
    public static ConvertTimeToTimeDTO convert(TimeTable timeTable){
        return new ConvertTimeToTimeDTO(
                new TimeDTO(
                        timeTable.getId(),
                        timeTable.getStartDate(),
                        timeTable.getEndDate(),
                        timeTable.getDays(),
                        timeTable.getIsInUse()
                )
        );
    }
}
