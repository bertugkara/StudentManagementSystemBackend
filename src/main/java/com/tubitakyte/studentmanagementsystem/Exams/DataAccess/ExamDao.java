package com.tubitakyte.studentmanagementsystem.Exams.DataAccess;

import com.tubitakyte.studentmanagementsystem.Exams.entity.Exams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamDao extends JpaRepository<Exams,Integer> {

    List<Exams> findAllByCreator_Id(Integer id);
    List<Exams> findAllByLesson_Id(Integer id);
}
