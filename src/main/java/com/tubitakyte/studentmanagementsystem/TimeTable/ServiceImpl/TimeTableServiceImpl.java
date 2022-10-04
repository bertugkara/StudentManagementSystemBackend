package com.tubitakyte.studentmanagementsystem.TimeTable.ServiceImpl;

import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.TimeConversions.ConvertListTimeToTimeDTOList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.TimeConversions.ConvertTimeToTimeDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.DataAccess.LessonDao;
import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import com.tubitakyte.studentmanagementsystem.TimeTable.DataAccess.TimeTableDao;
import com.tubitakyte.studentmanagementsystem.TimeTable.Entity.TimeTable;
import com.tubitakyte.studentmanagementsystem.TimeTable.Service.TimeTableService;
import com.tubitakyte.studentmanagementsystem.TimeTable.TimeDTO.TimeDTO;
import com.tubitakyte.studentmanagementsystem.TimeTable.enums.Days;
import com.tubitakyte.studentmanagementsystem.common.requests.CreateTimeRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {

    private final TimeTableDao timeTableDao;

    private final LessonDao lessonDao;

    @Override
    public DataResult<TimeDTO> createTime(CreateTimeRequest request) {

        Days day;
        switch (request.getDay()) {
            case "MONDAY" -> day = Days.MONDAY;
            case "TUESDAY" -> day = Days.TUESDAY;
            case "WEDNESDAY" -> day = Days.WEDNESDAY;
            case "THURSDAY" -> day = Days.THURSDAY;
            case "FRIDAY" -> day = Days.FRIDAY;
            default -> throw new IllegalStateException("Unexpected value: " + request.getDay());
        }
        TimeTable timeTable = new TimeTable(
                LocalTime.parse(request.getStartDate()),
                LocalTime.parse(request.getEndDate()),
                day
        );
        timeTableDao.save(timeTable);
        return new SuccessDataResult<>(getOneTime(timeTable.getId()).getData(), "Successfully created");
    }

    @Override
    public DataResult<TimeDTO> getOneTime(Integer id) {

        Optional<TimeTable> timeTable = timeTableDao.findById(id);
        if (!timeTable.isPresent()) {
            throw new EntityNotFoundException("TimeTablo could not found!!");
        }

        return new SuccessDataResult<>(ConvertTimeToTimeDTO.convert(timeTable.get()).timeDTO(), "Successfully Fetched");

    }

    @Override
    public DataResult<List<TimeDTO>> getAllTimes() {

        List<TimeTable> timeTableList = timeTableDao.findAll();
        if (timeTableList.isEmpty()) {
            throw new EntityNotFoundException("No Time Has been Found");
        }

        return new SuccessDataResult<>(ConvertListTimeToTimeDTOList.convert(timeTableList).timeDTOList(), "Succesfully fetched");

    }

    @Override
    public DataResult<List<TimeDTO>> getAllNonUsedTimesOrLessonId(int id) {
        List<TimeTable> combinedList=new ArrayList<>();
        List<TimeTable> timeTableList = timeTableDao.findByIsInUseFalseOrOwnerLesson_IdOrderById(id);
        List<TimeTable> lessonTableList=lessonDao.findById(id).get().getTimeTable();
        combinedList.addAll(lessonTableList);
        combinedList.addAll(timeTableList);
        if (timeTableList.isEmpty()) {
            throw new EntityNotFoundException("No Time Has been Found");
        }

        return new SuccessDataResult<>(ConvertListTimeToTimeDTOList.convert(combinedList).timeDTOList(), "Succesfully fetched");

    }

    public void updateTimeTableAsUsed(Lessons lesson) {
        lesson.getTimeTable().forEach(tempTable -> {
            Optional<TimeTable> timeTable = timeTableDao.findById(tempTable.getId());
            if (!timeTable.isPresent()) {
                throw new EntityNotFoundException("No timeTable has been founded with associated ID list");
            }
            if (timeTable.get().getIsInUse() != true) {
                timeTable.get().updateIsInUse(true);
                timeTableDao.save(timeTable.get());
            }
        });
    }

    public void setTimeTableFree(List<Integer> freeList) {
        freeList.forEach(tempTable -> {
            Optional<TimeTable> timeTable = timeTableDao.findById(tempTable);
            if (!timeTable.isPresent()) {
                throw new EntityNotFoundException("No timeTable has been founded with associated ID list");
            }
            timeTable.get().setTimeTableFree();
            timeTableDao.save(timeTable.get());
        });
    }

    @Override
    public DataResult<List<TimeDTO>> getAllNonUsedTimes() {
        List<TimeTable> timeTableList = timeTableDao.findByIsInUseFalseOrderById();
        if (timeTableList.isEmpty()) {
            throw new EntityNotFoundException("No Time Has been Found");
        }

        return new SuccessDataResult<>(ConvertListTimeToTimeDTOList.convert(timeTableList).timeDTOList(), "Succesfully fetched");

    }
}
