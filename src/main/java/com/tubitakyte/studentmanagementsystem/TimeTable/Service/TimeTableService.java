package com.tubitakyte.studentmanagementsystem.TimeTable.Service;

import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import com.tubitakyte.studentmanagementsystem.TimeTable.TimeDTO.TimeDTO;
import com.tubitakyte.studentmanagementsystem.common.requests.CreateTimeRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;

import java.util.List;

public interface TimeTableService {

    DataResult<TimeDTO> createTime(CreateTimeRequest request);

    DataResult<TimeDTO> getOneTime(Integer id);

    DataResult<List<TimeDTO>> getAllTimes();

    DataResult<List<TimeDTO>> getAllNonUsedTimesOrLessonId(int id);

    void updateTimeTableAsUsed(Lessons lessons);

    void setTimeTableFree(List<Integer> freeList);

    DataResult<List<TimeDTO>> getAllNonUsedTimes();

}
