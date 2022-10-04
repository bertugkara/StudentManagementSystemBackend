package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.TimeConversions;

import com.tubitakyte.studentmanagementsystem.TimeTable.Entity.TimeTable;
import com.tubitakyte.studentmanagementsystem.TimeTable.TimeDTO.TimeDTO;

import java.util.ArrayList;
import java.util.List;

public record ConvertListTimeToTimeDTOList(
        List<TimeDTO> timeDTOList
) {
    public static ConvertListTimeToTimeDTOList convert(List<TimeTable> timeTableList){
        List<TimeDTO> timeDTOList=new ArrayList<>();
        timeTableList.forEach((tempTime)-> {
            TimeDTO timeDTO = new TimeDTO(
                    tempTime.getId(),
                    tempTime.getStartDate(),
                    tempTime.getEndDate(),
                    tempTime.getDays(),
                    tempTime.getIsInUse()
            );
            timeDTOList.add(timeDTO);
        });
        return new ConvertListTimeToTimeDTOList(timeDTOList);
    }
}
