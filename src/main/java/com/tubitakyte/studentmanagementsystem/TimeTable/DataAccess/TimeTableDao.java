package com.tubitakyte.studentmanagementsystem.TimeTable.DataAccess;

import com.tubitakyte.studentmanagementsystem.TimeTable.Entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableDao extends JpaRepository<TimeTable,Integer> {

    List<TimeTable> findByIsInUseFalseOrOwnerLesson_IdOrderById(int id);

    List<TimeTable> findByIsInUseFalseOrderById();

}
